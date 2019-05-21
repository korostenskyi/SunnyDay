package com.korostenskyi.sunnyday.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korostenskyi.sunnyday.data.entity.DayInfo
import com.korostenskyi.sunnyday.data.network.api.ApiService
import com.korostenskyi.sunnyday.data.network.api.util.DataWrapper
import com.korostenskyi.sunnyday.data.network.api.util.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SunriseSunsetDataSourceImpl(private val apiService: ApiService): SunriseSunsetDataSource {

    private val _fetchedDayInfo = MutableLiveData<DataWrapper<DayInfo>>()

    override val fetchedDayInfo: LiveData<DataWrapper<DayInfo>>
        get() = _fetchedDayInfo

    override suspend fun fetchDayInfoByCoordinates(latitude: Double, longitude: Double) {
        GlobalScope.launch(Dispatchers.IO) {

            try {

                val dayInfoResponse = apiService.fetchDayInfoByCoordinates(latitude, longitude).await()

                if (dayInfoResponse.isSuccessful && dayInfoResponse.body()?.status.equals("OK")) {
                    _fetchedDayInfo.postValue(DataWrapper(dayInfoResponse.body()!!.data))
                } else {
                    _fetchedDayInfo.postValue(DataWrapper(error = Failure.ServerError(dayInfoResponse.message())))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _fetchedDayInfo.postValue(DataWrapper(error = Failure.ConnectionError()))
            }
        }
    }
}