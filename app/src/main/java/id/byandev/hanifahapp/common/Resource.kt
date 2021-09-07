package id.byandev.hanifahapp.common


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
