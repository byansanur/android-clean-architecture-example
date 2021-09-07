package id.byandev.hanifahapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WahhaabApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: WahhaabApplication
        private set
        fun hasNetwork() : Boolean {
            val connectivityManager = instance.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                return when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
            else{
                connectivityManager.activeNetworkInfo?.run {
                    return when(type){
                        ConnectivityManager.TYPE_WIFI -> return true
                        ConnectivityManager.TYPE_MOBILE -> return true
                        ConnectivityManager.TYPE_ETHERNET -> return true
                        else -> false
                    }
                }
            }
            return false
        }
    }
}