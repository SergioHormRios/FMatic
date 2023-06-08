package net.azarquiel.fmatic.api

import net.azarquiel.fmatic.model.Drivers

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

}