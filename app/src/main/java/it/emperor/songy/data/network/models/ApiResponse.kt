package it.emperor.songy.data.network.models

sealed class ApiResponse<out T> {
    object Loading : ApiResponse<Nothing>()
    data class Error(val throwable: Throwable?) : ApiResponse<Nothing>()
    data class Success<T>(val value: T) : ApiResponse<T>()
}

suspend fun <T> wrapInApiResponse(function: suspend () -> T?): ApiResponse<T> = try {
    val response = function.invoke()
    if (response != null) {
        ApiResponse.Success(response)
    } else {
        ApiResponse.Error(null)
    }
} catch (e: Exception) {
    ApiResponse.Error(e)
}