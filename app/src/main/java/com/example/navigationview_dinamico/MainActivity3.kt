package com.example.navigationview_dinamico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity3 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {
    var drawerLayout: DrawerLayout? = null
    var  toolbar: Toolbar? = null

    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        toolbar = findViewById<Toolbar>(R.id.toolbar2);
        toolbar!!.title="CLIENTE"
        setSupportActionBar(toolbar);

        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.iconmenu)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        navView = findViewById(R.id.nav_view2)
        navView.setNavigationItemSelectedListener(this);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout2)
                drawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.menu_seccion_1 -> {

            }
            R.id.menu_seccion_2 -> {

            }
            R.id.menu_seccion_3 -> {

            }
        }

        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame2, fragment)
                .commit()

            item.setChecked(true)
            getSupportActionBar()?.setTitle(item.getTitle());
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout2)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true


    }
}