package id.byandev.hanifahapp.di

import androidx.annotation.WorkerThread
import androidx.viewbinding.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.byandev.hanifahapp.common.Constants.BASE_URL
import id.byandev.hanifahapp.data.remote.ApiCoinService
import id.byandev.hanifahapp.data.repository.CoinRepositoryImpl
import id.byandev.hanifahapp.domain.repository.CoinRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    @WorkerThread
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            //handle request time out
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .build()
                val response = chain.proceed(request)
                response
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ) : ApiCoinService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiCoinService::class.java)
    }

    @Provides
    @Singleton
    fun provideInteractApiService(api: ApiCoinService) : CoinRepository {
        return CoinRepositoryImpl(api)
    }

}