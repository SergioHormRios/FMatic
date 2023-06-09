package net.azarquiel.fmatic.interfaces

import kotlinx.coroutines.Deferred
import net.azarquiel.fmatic.model.Request

import retrofit2.Response
import retrofit2.http.*

interface FOneService {
    @GET("drivers")
    fun getAllDrivers(): Deferred<Response<Request>>

    @GET("teams")
    fun getAllTeams(): Deferred<Response<Request>>

    @GET("drivers/hall-of-fame")
    fun getHallFame(): Deferred<Response<Request>>

    @GET("schedule/race-schedule")
    fun getRaceCalendar(): Deferred<Response<Request>>
    fun getNextRounds(): Deferred<Response<Request>>
}