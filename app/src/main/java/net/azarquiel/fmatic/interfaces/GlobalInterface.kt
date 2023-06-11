package net.azarquiel.fmatic.interfaces

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import net.azarquiel.fmatic.R
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


    //Funcion para borrar un caracter dentro de un string
    fun deleteCharInString(string: String, deleteChar: Char ) : String = string.split(deleteChar).toString()

    //Funcion para elegir una foto en funicon del apellido del piloto
    fun setDriverRef (lastNameDriver: String):Int {
        @DrawableRes
        var resource = 0
        when (lastNameDriver.toLowerCase()) {
            "albon" -> resource = R.drawable.dr_albon
            "alonso" -> resource = R.drawable.dr_alonso
            "bottas" -> resource = R.drawable.dr_bottas
            "de vries" -> resource = R.drawable.dr_de_vries
            "gasly" -> resource = R.drawable.dr_gasly
            "hamilton" -> resource = R.drawable.dr_hamilton
            "hulkenberg" -> resource = R.drawable.dr_hulkenberg
            "leclerc" -> resource = R.drawable.dr_leclerc
            "magnussen" -> resource = R.drawable.dr_magnussen
            "norris" -> resource = R.drawable.dr_norris
            "ocon" -> resource = R.drawable.dr_ocon
            "perez" -> resource = R.drawable.dr_perez
            "piastri" -> resource = R.drawable.dr_piastri
            "russell" -> resource = R.drawable.dr_russell
            "sainz" -> resource = R.drawable.dr_sainz
            "sargeant" -> resource = R.drawable.dr_sargeant
            "stroll" -> resource = R.drawable.dr_stroll
            "tsunoda" -> resource = R.drawable.dr_tsunoda
            "verstappen" -> resource = R.drawable.dr_verstappen
            "zhou" -> resource = R.drawable.dr_zhou
            else -> resource = R.drawable.ic_avatar
        }
        return resource
    }

    //Funcion para elegir una foto en funicon del apellido del piloto
    fun setTeamRef(teamName: String): Int{
        @DrawableRes
        var resource = 0
        when(teamName.toLowerCase()){
            "red bull racing", "oracle red bull racing"-> resource = R.drawable.team_red_bull
            "mercedes", "mercedes-amg petronas f1 team" -> resource = R.drawable.team_mercedes
            "aston martin", "aston martin aramco cognizant f1 team" -> resource = R.drawable.team_aston_martin
            "alpine", "bwt alpine f1 team"-> resource = R.drawable.team_alpine
            "ferrari", "scuderia ferrari" -> resource = R.drawable.team_ferrari
            "mclaren", "mclaren f1 team"-> resource = R.drawable.team_mclaren
            "haas f1 team", "moneygram haas f1 team" -> resource = R.drawable.team_haas
            "alphatauri", "scuderia alphatauri" -> resource = R.drawable.team_alphatauri
            "alfa romeo", "alfa romeo f1 team stake"-> resource = R.drawable.team_alfa_romeo
            "williams", "williams racing" -> resource = R.drawable.team_williams
            else -> resource = R.drawable.ic_avatar
        }
        return resource
    }

    fun setApiRefTeams(teamName: String): String {
        var teamRef = ""

        when(teamName.toLowerCase()){
            "red bull racing" -> teamRef = "redbull"
            "mercedes" -> teamRef = "mercedes"
            "aston martin" -> teamRef = "aston"
            "alpine" -> teamRef = "alpine"
            "ferrari" -> teamRef = "ferrari"
            "mclaren" -> teamRef = "mclaren"
            "haas f1 team" -> teamRef = "haas"
            "alphatauri" -> teamRef = "alphatauri"
            "alfa romeo" -> teamRef = "alfa"
            "williams" -> teamRef = "williams"
        }
        return teamRef

    }
}

