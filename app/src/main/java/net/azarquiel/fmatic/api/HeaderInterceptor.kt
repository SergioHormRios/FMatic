package net.azarquiel.fmatic.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("X-RapidAPI-Key", "09f05e99bdmsha0d010ec4cc31a5p1fdf37jsn155d2759741d")
            .header("X-RapidAPI-Host", "fia-formula-1-championship-statistics.p.rapidapi.com")
            .build()
        return chain.proceed(modifiedRequest)
    }
}