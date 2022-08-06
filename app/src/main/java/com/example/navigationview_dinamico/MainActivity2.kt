package com.example.navigationview_dinamico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var drawerLayout: DrawerLayout? = null
    var  toolbar: Toolbar? = null

    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        toolbar = findViewById<Toolbar>(R.id.toolbar2);
        val bundle = this.getIntent().getExtras();
        navView = findViewById(R.id.nav_view2)
        var nav_header = navView.getHeaderView(0);
        var fondo_header=nav_header.findViewById<ImageView>(R.id.fondo_cabecera);
        fondo_header.setImageResource(R.drawable.navheader)
        var nombre_user=nav_header.findViewById<TextView>(R.id.nombre);
        var imagen=nav_header.findViewById<CircleImageView>(R.id.profile_image)
        var menu=navView.getMenu();
        var iniciar=false;
        //consumir servicio
        val queue = Volley.newRequestQueue(this)
        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, "https://apimocha.com/123112312313123123/users", null,
            { response ->
                for (i in 0 until response.length()) {
                    var objeto=response.getJSONArray((i+1).toString()).getJSONObject(0)
                    if(bundle?.getString("user")==objeto.getString("user") && bundle?.getString("password")==objeto.getString("password")){
                        iniciar=true;
                        toolbar!!.title=objeto.getString("user").toUpperCase()
                        setSupportActionBar(toolbar);
                        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.iconmenu)
                        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
                        nombre_user.setText(objeto.getString("user"));
                        Picasso.get().load(objeto.getString("imagen")).into(imagen);
                        var menu_inf="";
                        if(bundle?.getString("user")=="administrador"){
                            menu_inf="menu_admin"
                        }else if(bundle?.getString("user")=="cliente"){
                            menu_inf="menu_cliente"
                        }
                        val cola = Volley.newRequestQueue(this)
                        val jsonObjectRequest = JsonObjectRequest(
                            Request.Method.GET, "https://apimocha.com/123112312313123123/menu", null,
                            { response ->
                                var item=response.getJSONArray(menu_inf)
                                var objeto=item.getJSONObject(0)
                                for (i in 0 until objeto.length()){
                                    var item=objeto.getString("item"+(i+1))
                                    menu.add(item)
                                }
                            },
                            { error ->

                            }
                        )
                        cola.add(jsonObjectRequest);
                    }
                }
                if(iniciar==false){
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent);
                }
            },
            { error ->

            }
        )
        queue.add(jsonObjectRequest);
        navView.setNavigationItemSelectedListener(this);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
                drawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.title) {
            "Mi Cuenta" -> {

            }
            "R.id.menu_seccion_2" -> {

            }
            "R.id.menu_seccion_3" -> {

            }
            "Cerrar Sesión" ->{
                val intent = Intent(this, MainActivity::class.java);
                startActivity(intent);
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

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true


    }
}