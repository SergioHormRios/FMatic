package net.azarquiel.fmatic.interfaces

import kotlinx.coroutines.Deferred
import net.azarquiel.fmatic.model.Request

import retrofit2.Response
import retrofit2.http.*

interface FOneService {
    // Trae todos los pilotos de las temporadas
    @GET("drivers")
    fun getAllDrivers(): Deferred<Response<Request>>


}