package com.android.basicecommerce.di.application

import com.android.basicecommerce.BuildConfig
import com.android.data.api.BasicEcommerceService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule(
    private val baseUrl: String
) {

    companion object {
        const val CONNECT_TIMEOUT_IN_SECONDS = 10
        const val READ_TIMEOUT_IN_SECONDS = 30
        const val WRITE_TIMEOUT_IN_SECONDS = 30
    }

    /**
     * provides an interceptor object to enable logging http request/response
     * based on defined log level
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * provides a custom OkHTTP object to be used in retrofit client
     * it could be used as a standalone http client
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideBasicEcommerceService(retrofit: Retrofit): BasicEcommerceService {
        return retrofit.create(BasicEcommerceService::class.java)
    }

}