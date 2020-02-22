package com.davidhsu.githubproject.di

import com.davidhsu.githubproject.api.AppApi
import com.davidhsu.githubproject.appConst.PrefsConst
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single(named(NetworkModuleClass::class.java.simpleName)) {
        provideNetworkModule()
    }

    single(named(AppApi::class.java.simpleName)) {
        (get(named(NetworkModuleClass::class.java.simpleName)) as NetworkModuleClass).appApi
    }

}

private fun provideNetworkModule() = NetworkModuleClass()

interface NetworkModuleClassInterface {
    val apiOkHttpClient: OkHttpClient
    val apiRetrofit: Retrofit
    val appApi: AppApi
}

class NetworkModuleClass : NetworkModuleClassInterface {

    override val apiOkHttpClient: OkHttpClient
        get() {
            val dispatcher = Dispatcher()
            dispatcher.maxRequests = PrefsConst.ApiConst.MAX_PARALLEL_REQUESTS
            dispatcher.maxRequestsPerHost = PrefsConst.ApiConst.MAX_PARALLEL_REQUESTS_PER_HOST

            return OkHttpClient.Builder()
                .readTimeout(PrefsConst.ApiConst.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(PrefsConst.ApiConst.CONN_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(PrefsConst.ApiConst.IDLE_CONNECTION_NUMBER, PrefsConst.ApiConst.KEEP_ALIVE_TIMEOUT, TimeUnit.SECONDS))
                .retryOnConnectionFailure(false)
                .dispatcher(dispatcher)
                .addInterceptor(HttpLoggingInterceptor()) // enable log
                .protocols(listOf(Protocol.HTTP_1_1))
                .build()

        }
    override val apiRetrofit: Retrofit
        get() {
            val githubBaseUrl = PrefsConst.ApiConst.BASE_URL
            return Retrofit.Builder()
                .baseUrl(githubBaseUrl)
                .client(apiOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        }
    override val appApi: AppApi
        get() = apiRetrofit.create(AppApi::class.java)

}

