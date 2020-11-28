package com.kit.showkitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        linOtp.setOnClickListener()
        {
            var intent = Intent(this, ContinueMobActivity::class.java)
            startActivity(intent)
        }

        linGoogle.setOnClickListener()
        {
            var intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}