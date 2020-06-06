package com.demo.androidproficiencyexercise.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ashish Nair on 05/06/20.
 */
class ApiClient {

    companion object {
        private var retrofit: Retrofit? = null
        private var baseURL: String? =
            "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
        private var clientBuilder: OkHttpClient.Builder? = null
        private var client: OkHttpClient? = null

        fun getClient(): Retrofit? {

            clientBuilder = OkHttpClient.Builder()
            // Log Interceptor
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            // Request OrderDetailsResponse Interceptor
            val interceptor = Interceptor { chain ->
                val original = chain.request()

                val builder = original.newBuilder()
                val request = builder.method(original.method, original.body)
                    .build()


                val response = chain.proceed(request)
                /**
                 * Handle Error Codes
                 */
                if (response.code == 401) {
                    try {
                        client?.dispatcher?.cancelAll()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    return@Interceptor response
                }
                response
            }

            clientBuilder!!.addInterceptor(httpLoggingInterceptor)
            clientBuilder!!.addInterceptor(interceptor)
            client = clientBuilder!!.build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }

            return retrofit
        }

    }
}