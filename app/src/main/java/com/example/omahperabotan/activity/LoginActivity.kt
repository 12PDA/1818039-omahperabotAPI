package com.example.omahperabotan.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.omahperabotan.MainActivity
import com.example.omahperabotan.R
import com.example.omahperabotan.app.ApiConfig
import com.example.omahperabotan.helper.SharedPref
import com.example.omahperabotan.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s:SharedPref
    lateinit var btn_prosesLogin : Button
    lateinit var edt_email : EditText
    lateinit var edt_pass  : EditText
    lateinit var pb  : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //deklarasi
        btn_prosesLogin = findViewById(R.id.btn_login)
        s = SharedPref(this)
        edt_email = findViewById(R.id.edit_email)
        edt_pass = findViewById(R.id.edit_pass)
        pb = findViewById(R.id.pb)

        btn_prosesLogin.setOnClickListener{
            login()
        }

    }

    fun login(){
        if (edt_email.text.isEmpty()){
            edt_email.error = "Pastikan email sesuai"
            edt_email.requestFocus()
            return
        } else if (edt_pass.text.isEmpty()){
            edt_pass.error = "Harap isikan password"
            edt_pass.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(
            edt_email.text.toString(),
            edt_pass.text.toString()
        ).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon =response.body()!!
                if(respon.success == 1){
                    s.setStatusLogin(true)
                    s.setUser(respon.user)
//                    s.setString(s.nama, respon.user.name)
//                    s.setString(s.phone, respon.user.phone)
//                    s.setString(s.email, respon.user.email)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()

                    Toast.makeText(this@LoginActivity, "Hai! Selamat Datang "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@LoginActivity, "Error "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error "+t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}