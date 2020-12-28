package com.kit.showkitapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.activity_otp_screen.*

class OtpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_screen)


        tvContinue.setOnClickListener()
        {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }
}