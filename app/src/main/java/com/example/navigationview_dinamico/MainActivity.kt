package com.example.navigationview_dinamico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(v:View){
        val intent = Intent(this, MainActivity2::class.java);
        val intent2 = Intent(this, MainActivity3::class.java);
        val  usuario = findViewById<TextView>(R.id.usuario);
        val  password = findViewById<TextView>(R.id.password);
        val user=usuario.text.toString();
        if(usuario.text!="" && password.text!=""){
            if (user == "administrador"){
                startActivity(intent);
            }else if (user == "cliente"){
                startActivity(intent2);
            }
        }


    }
}