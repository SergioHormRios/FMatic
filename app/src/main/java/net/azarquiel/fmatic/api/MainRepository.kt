package net.azarquiel.fmatic.api

import net.azarquiel.fmatic.model.Drivers
import net.azarquiel.fmatic.model.HallOfFames
import net.azarquiel.fmatic.model.Teams

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
    suspend fun getHallFame(): List<HallOfFames> {
        val webResponse = service.getHallFame().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.hallOfFame
        }
        return emptyList()
    }

}