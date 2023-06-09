package net.azarquiel.fmatic.api

import net.azarquiel.fmatic.model.*

class MainRepository {

    val service = WebAccess.fOneService
    //GETS
    suspend fun getAllDrivers(): List<Drivers> {
        val webResponse = service.getAllDrivers().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.drivers
        }
        return emptyList()
    }

    suspend fun getAllTeams(): List<Teams> {
        val webResponse = service.getAllTeams().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.teams
        }
        return emptyList()
    }

    suspend fun getHallOfFame(): List<HallOfFames> {
        val webResponse = service.getHallFame().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.hallOfFames
        }
        return emptyList()
    }

    suspend fun getRaceCalendar(): List<RaceCalendarEvents> {
        val webResponse = service.getRaceCalendar().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.raceCalendarEvents
        }
        return emptyList()
    }

    suspend fun getNextRounds(): List<NextRoundEvents> {
        val webResponse = service.getNextRounds().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.nextRoundEvents
        }
        return emptyList()
    }

}