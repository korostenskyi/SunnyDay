package com.korostenskyi.sunnyday.data.network.api.util

sealed class Failure {

    class ServerError(val message: String): Failure()
    class ConnectionError: Failure()

    abstract class FetchingFailure: Failure()
}