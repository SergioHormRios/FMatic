package net.azarquiel.fmatic.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.databinding.ActivityMainBinding
import net.azarquiel.fmatic.interfaces.GlobalInterface
import net.azarquiel.fmatic.model.Drivers
import net.azarquiel.fmatic.model.Teams
import net.azarquiel.fmatic.ui.*
import net.azarquiel.fmatic.viewModel.MainViewModel
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener, GlobalInterface {

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    /*Firebase - Auth*/
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    /* Sharepreference*/
    private lateinit var perfs : SharedPreferences

    /*Retrofit*/
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout

        navView = binding.navView
        navView.setNavigationItemSelectedListener(this)

        //Genera el icono de las tres rayas que abre el cajon
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        setInitialFragment()

        //Api provider
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //SetUp
        val email = intent.getStringExtra("email") as String

        setUp(email)
    }

    private fun setUp(email: String) {
        navView.getHeaderView(0).findViewById<TextView>(R.id.tvMail).apply {
            text = email
        }

        perfs = getSharedPreferences("profile",Context.MODE_PRIVATE)
        getUserSH() //Obtendremos el usuario del SharePreference (si existe)

    }
    private fun getUserSH() {
        val nickTXT = perfs.getString("nick", null)
        val mailTXT = perfs.getString("mail", null)

        if (nickTXT != null && mailTXT != null){
          //  nickTXT = Gson().toJson(nickTXT, )
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //Con este metodo al arrancar la aplicacion, nos aparecerá el fragmento que indiquemos
    private fun setInitialFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.Frame, NextRoundFragment())
        fragmentTransaction.commit()
    }


    //Nos permitirá cambiar de fragmentos
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.Frame, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment:Fragment? = null
        when (item.itemId) {
            R.id.nav_drivers -> fragment = DriversFragment()
            R.id.nav_teams -> fragment = TeamsFragment()
            R.id.nav_hall -> fragment = HallOfFameFragment()
            R.id.nav_seasons -> fragment = CalendarFragment()
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this,"Se ha cerrado correctamente la session", Toast.LENGTH_SHORT).show()
            }
        }
        if (item.itemId != R.id.nav_logout) replaceFragment(fragment!!)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*
        Cuando pulsemos el botón de regreso mientras tenemos el drawerLayout desplegado,
         se cerrara en vez de salir de la Activity
    */
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START)
        else onBackPressedDispatcher.onBackPressed()
    }

    //OnClick Views
    fun onClickDriver(v: View){
        val firtname = (v.tag as Drivers).firstname
        val lastname = (v.tag as Drivers).lastname

        val driver = firtname + lastname

        val bundle = Bundle()
        bundle.putString("driver", driver.toLowerCase()) // Puedes agregar varios pares clave-valor si quieres

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Frame,DriverDetailFragment().apply {
                arguments = bundle
            })
            .commit()
    }

    fun onClickTeam(v: View){
        val teamName = (v.tag as Teams).teamName

        val bundle = Bundle()
        bundle.putString("team", setApiRefTeams(teamName)) // Puedes agregar varios pares clave-valor si quieres

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Frame,TeamDetailFragment().apply {
                arguments = bundle
            })
            .commit()
    }
}


