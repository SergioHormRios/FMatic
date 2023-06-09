package net.azarquiel.fmatic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.fmatic.api.MainRepository
import net.azarquiel.fmatic.model.Drivers
import net.azarquiel.fmatic.model.HallOfFames
import net.azarquiel.fmatic.model.Teams

class MainViewModel: ViewModel() {
    private var repository: MainRepository = MainRepository()

    fun getAllDrivers(): MutableLiveData<List<Drivers>> {
        val drivers = MutableLiveData<List<Drivers>>()
        GlobalScope.launch(Dispatchers.Main) {
            drivers.value = repository.getAllDrivers()
        }
        return drivers
    }
    fun getAllTeams(): MutableLiveData<List<Teams>> {
        val teams = MutableLiveData<List<Teams>>()
        GlobalScope.launch(Dispatchers.Main) {
            teams.value = repository.getAllTeams()
        }
        return teams
    }
    fun getHallFame(): MutableLiveData<List<HallOfFames>> {
        val hallFame = MutableLiveData<List<HallOfFames>>()
        GlobalScope.launch(Dispatchers.Main) {
            hallFame.value = repository.getHallFame()
        }
        return hallFame
    }
}