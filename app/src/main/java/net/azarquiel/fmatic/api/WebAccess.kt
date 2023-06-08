package net.azarquiel.fmatic.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import net.azarquiel.fmatic.interfaces.FOneService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebAccess {
    val fOneService: FOneService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://fia-formula-1-championship-statistics.p.rapidapi.com/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HeaderInterceptor())
                    .build()
            )
            .build()

        return@lazy retrofit.create(FOneService::class.java)
    }
}