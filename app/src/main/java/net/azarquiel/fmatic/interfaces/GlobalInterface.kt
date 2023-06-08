package net.azarquiel.fmatic.interfaces

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import net.azarquiel.fmatic.viewModel.MainViewModel

// Almacenará todas las funciones que se utilizarán en las distintas activities y fragmentos
interface GlobalInterface {

    //Funcion para mostrar alert dialog en funcion de los parametros indicados
    fun showMessage(context: Context, titulo:String, message: String) =
         AlertDialog.Builder(context)
             .setTitle(titulo)
             .setMessage(message)
             .setNeutralButton("OK", null)
             .show()


}

