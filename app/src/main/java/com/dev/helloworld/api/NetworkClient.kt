package com.dev.helloworld.api

import com.dev.helloworld.model.response.BaseResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object NetworkClient {

    private const val BASE_URL = "http://13.209.144.115:3333"

    private val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val commonNetworkInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val newRequest = chain.request().newBuilder()
                .build()

            Timber.e("${newRequest.method} - ${newRequest.url} \n{\n\t${newRequest.body?.toString()}\n}\n" )

            val response = chain.proceed(newRequest)

            val rawJson = response.body?.string() ?: "{}"

            Timber.e(rawJson)

            val type = object : TypeToken<BaseResponse<*>>() {}.type
            val res = try {
                val r = gson.fromJson<BaseResponse<*>>(rawJson, type) ?: throw JsonSyntaxException("Parse Fail")

                if(!r.success)
                    BaseResponse<Any>(-999, false, "Server Logic Fail : ${r.message}", null)
                else
                    r

            } catch (e: JsonSyntaxException) {
                BaseResponse<Any>(-999, false, "json parsing fail : $e", null)
            } catch (t: Throwable) {
                BaseResponse<Any>(-999, false, "unknown error : $t", null)
            }

            val dataJson = gson.toJson(res.data)

            return response.newBuilder()
                .message(res.message)
                .body(dataJson.toResponseBody())
                .build()
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(commonNetworkInterceptor)
        .build()

    private val gson = GsonBuilder()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    /**
     * Services
     */
    val apiService: NetworkInterface = retrofit.create(NetworkInterface::class.java)
}