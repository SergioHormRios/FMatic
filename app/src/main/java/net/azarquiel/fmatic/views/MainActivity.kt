package net.azarquiel.fmatic.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.databinding.ActivityMainBinding
import net.azarquiel.fmatic.ui.CalendarFragment
import net.azarquiel.fmatic.ui.LoginFragment
import net.azarquiel.fmatic.ui.PilotsFragment

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //Con este metodo al arrancar la aplicacion, nos aparecerá el fragmento que indiquemos
    private fun setInitialFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.Frame, CalendarFragment())
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
            R.id.nav_pilotos -> fragment = PilotsFragment()
            R.id.nav_login -> fragment = LoginFragment()

            R.id.nav_circuitos -> {

            }
            R.id.nav_temporadas -> {

            }
        }
        replaceFragment(fragment!!)
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

}