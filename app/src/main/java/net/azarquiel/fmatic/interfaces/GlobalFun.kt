package net.azarquiel.fmatic.interfaces

import android.content.Context
import androidx.appcompat.app.AlertDialog

// Almacenará todas las funciones que se utilizarán en las distintas activities y fragmentos
interface GlobalFun {
    //Funcion para mostrar alert dialog en funcion de los parametros indicados
    fun showMessage(context: Context, titulo:String, message: String) =
         AlertDialog.Builder(context)
             .setTitle(titulo)
             .setMessage(message)
             .setNeutralButton("OK", null)
             .show()


}

