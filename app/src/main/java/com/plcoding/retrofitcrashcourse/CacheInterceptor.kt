package com.plcoding.retrofitcrashcourse

import android.content.Context
import android.util.Log
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class CacheInterceptor(context: Context) {

    val cacheSize = (5 * 1024 * 1024).toLong()

    private  val BIG_CACHE_PATH = "Todo-api-path"


    val HEADER_CACHE_CONTROL = "Cache-Control"
    val HEADER_PRAGMA = "Pragma"


    val okHttpClient = offlineInterceptor(context)?.let {
        OkHttpClient.Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .cache(createCache(MyApplication.getInstance()))
        .addInterceptor(ResponseCacheInterceptor())
        .addInterceptor(it)
        .build()
    }
    /**
     * Interceptor to cache data and maintain it for four weeks.
     *
     *Max stale is must for ofline intercepto
     * If the device is offline, stale (at most four weeks old)
     * response is fetched from the cache.
     */
    private fun offlineInterceptor(context: Context): Interceptor? {
        return Interceptor { chain ->
            Log.d("Tag", "offline interceptor: called.")
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!NetworkUtils.hasNetwork()) {
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    val apiCache: TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TodoApi::class.java)
    }
    private fun createCache(context: Context): Cache? {
        return Cache(File(context.applicationContext.cacheDir, BIG_CACHE_PATH), cacheSize)
    }
}

class ResponseCacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val cacheControl = originalResponse.header("Cache-Control")
        return if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains(
                "no-cache") ||
            cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
        ) {
            originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + 5000)
                .build()
        } else {
            originalResponse
        }
    }
}



