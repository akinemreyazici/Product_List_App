package com.works.odev6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.works.odev6.configs.ApiClient
import com.works.odev6.models.JWTData
import com.works.odev6.models.JWTUser
import com.works.odev6.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var editTxtUsername: EditText
    lateinit var editTxtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var dummyService1: DummyService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        dummyService1 = ApiClient.getClient().create(DummyService::class.java)

        editTxtUsername = findViewById(R.id.editTxtUsername)
        editTxtPassword = findViewById(R.id.editTxtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(btnLoginClick)

    }

    val btnLoginClick = View.OnClickListener {
        val username = editTxtUsername.text.toString()
        val password = editTxtPassword.text.toString()
        val jwtUser = JWTUser(username, password)
        if (jwtUser.username == "" || jwtUser.password == "") {
            Toast.makeText(
                this,
                "Username veya Password'u boş bırakmışsınız kontrol ediniz",
                Toast.LENGTH_LONG
            ).show()
        }
        dummyService1.login(jwtUser).enqueue(object : Callback<JWTData> {
            override fun onResponse(call: Call<JWTData>, response: Response<JWTData>) {
                val user = response.body()
                Log.d("status", response.code().toString())
                if (user != null) {
                    val intent = Intent(this@LoginActivity, ProductListActivity::class.java)
                    startActivity(intent)
                    finish()
                }


            }

            override fun onFailure(call: Call<JWTData>, t: Throwable) {
                Log.e("Error", t.toString())
                Toast.makeText(this@LoginActivity, "Internet veya Server Hatası", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }
}