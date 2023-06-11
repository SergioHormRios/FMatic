package net.azarquiel.fmatic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.fmatic.api.MainRepository
import net.azarquiel.fmatic.model.*

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
    fun getHallOfFame(): MutableLiveData<List<HallOfFames>> {
        val hall = MutableLiveData<List<HallOfFames>>()
        GlobalScope.launch(Dispatchers.Main) {
            hall.value = repository.getHallOfFame()
        }
        return hall
    }

    fun getRaceCalendar(): MutableLiveData<List<RaceCalendarEvents>> {
        val raceCal = MutableLiveData<List<RaceCalendarEvents>>()
        GlobalScope.launch(Dispatchers.Main) {
            raceCal.value = repository.getRaceCalendar()
        }
        return raceCal
    }

    fun getNextRounds() : MutableLiveData<List<NextRoundEvents>>{
        val nextRound = MutableLiveData<List<NextRoundEvents>>()
        GlobalScope.launch(Dispatchers.Main){
            nextRound.value = repository.getNextRounds()
        }
        return nextRound
    }

    fun getDriverDetails(nameDriver:String): MutableLiveData<DriverDetail> {
        val driver = MutableLiveData<DriverDetail>()
        GlobalScope.launch(Dispatchers.Main) {
            driver.value = repository.getDriverDetails(nameDriver)
        }
        return driver
    }

    fun getTeamDetails(nameTeam:String): MutableLiveData<TeamDetail> {
        val team = MutableLiveData<TeamDetail>()
        GlobalScope.launch(Dispatchers.Main) {
            team.value = repository.getTeamDetails(nameTeam)
        }
        return team
    }
}
