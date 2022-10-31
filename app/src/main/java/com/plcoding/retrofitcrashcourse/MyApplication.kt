package com.plcoding.retrofitcrashcourse

import android.app.Application
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MyApplication : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
        if (instance == null) {
            instance = this
        }

    }

    fun hasNetwork(): Boolean {
        return instance.isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

    companion object {
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication {
            return instance
        }
    }
}
