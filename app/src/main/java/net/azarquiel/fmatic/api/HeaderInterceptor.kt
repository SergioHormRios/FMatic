package net.azarquiel.fmatic.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("X-RapidAPI-Key", "8e182c78cbmsh7227a4c08863c27p180bc9jsn244bd6f75d2c")
            .header("X-RapidAPI-Host", "fia-formula-1-championship-statistics.p.rapidapi.com")
            .build()
        return chain.proceed(modifiedRequest)
    }
}