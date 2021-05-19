package com.zhanglei.readhubnew.net


import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * ClassName Api
 * Description
 * Create by zhang
 * Date 9/28/20 11:41 PM
 */
class Api {
    private object Hodler{
        var INSTANCE = Api()
    }
    companion object{
        var instance = Hodler.INSTANCE
    }

    fun <T> initRetrfit(apiInterface: Class<T>): T {
        var okHttpClient = OkHttpClient().newBuilder().apply {
            readTimeout(10000,TimeUnit.SECONDS)
            connectTimeout(10000,TimeUnit.SECONDS)
            writeTimeout(10000,TimeUnit.SECONDS)
        }.build()
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://readhub.cn/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(apiInterface)
    }

}