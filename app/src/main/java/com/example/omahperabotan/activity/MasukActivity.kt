package com.example.omahperabotan.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.omahperabotan.R
import com.example.omahperabotan.helper.SharedPref

class MasukActivity : AppCompatActivity() {

    lateinit var s:SharedPref
    lateinit var btn_prosesLogin : Button
    lateinit var btn_register : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)
        btn_prosesLogin = findViewById(R.id.btn_prosesLogin)
        btn_register = findViewById(R.id.btn_prosesRegister)
        s = SharedPref(this)

        mainButton()
    }

    private fun mainButton(){
        btn_prosesLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}