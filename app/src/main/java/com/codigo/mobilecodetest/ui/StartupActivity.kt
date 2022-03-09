package com.codigo.mobilecodetest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codigo.mobilecodetest.R
import com.google.android.material.button.MaterialButton

class StartupActivity : AppCompatActivity() {

    lateinit var btnCreateAccount: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)
        btnCreateAccount = findViewById(R.id.btnCreateNewAccount)
        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}