package net.azarquiel.fmatic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.fmatic.api.MainRepository
import net.azarquiel.fmatic.model.Drivers

class MainViewModel: ViewModel() {
    private var repository: MainRepository = MainRepository()

    fun getAllDrivers(): MutableLiveData<List<Drivers>> {
        val drivers = MutableLiveData<List<Drivers>>()
        GlobalScope.launch(Dispatchers.Main) {
            drivers.value = repository.getAllDrivers()
        }
        return drivers
    }

}