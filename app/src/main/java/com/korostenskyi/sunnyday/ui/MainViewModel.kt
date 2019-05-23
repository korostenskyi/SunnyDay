package com.korostenskyi.sunnyday.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.korostenskyi.sunnyday.data.entity.DayInfo
import com.korostenskyi.sunnyday.data.network.api.util.DataWrapper
import com.korostenskyi.sunnyday.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val dayInfo = MutableLiveData<DataWrapper<DayInfo>>()

    init {
        repository.fetchedDayInfo.observeForever { serverResponse ->
            dayInfo.value = serverResponse
        }
    }

    suspend fun fetchDayInfoByCoordinates(latitude: Double, longitude: Double) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.fetchDayInfoByCoordinated(latitude, longitude)
        }
    }
}