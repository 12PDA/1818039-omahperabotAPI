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
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var btn_register : Button
    lateinit var edt_nama  : EditText
    lateinit var edt_email : EditText
    lateinit var edt_phone : EditText
    lateinit var edt_pass  : EditText
    lateinit var pb  : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //deklarasi
        btn_register = findViewById(R.id.btn_prosesRegister)
        s = SharedPref(this)
        edt_nama = findViewById(R.id.edit_nama)
        edt_email = findViewById(R.id.edit_email)
        edt_phone = findViewById(R.id.edit_phone)
        edt_pass = findViewById(R.id.edit_pass)
        pb = findViewById(R.id.pb)

        btn_register.setOnClickListener{
            register()
        }
    }

    fun register(){
        if (edt_nama.text.isEmpty()){
            edt_nama.error = "Harap isikan nama"
            edt_nama.requestFocus()
            return
        } else if (edt_email.text.isEmpty()){
            edt_email.error = "Pastikan email sesuai"
            edt_email.requestFocus()
            return
        } else if (edt_phone.text.isEmpty()){
            edt_phone.error = "Harap isikan No Telepon"
            edt_phone.requestFocus()
            return
        } else if (edt_pass.text.isEmpty()){
            edt_pass.error = "Harap isikan password"
            edt_pass.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(
            edt_nama.text.toString(),
            edt_email.text.toString(),
            edt_phone.text.toString(),
            edt_pass.text.toString()
        ).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon =response.body()!!
                if(respon.success == 1){
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()

                    Toast.makeText(this@RegisterActivity, "Hai! Selamat Datang "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@RegisterActivity, "Error "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, "Error "+t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}