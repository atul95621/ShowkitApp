package com.kit.showkitapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.activity_welcome_back.*

class WelcomeBackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        tvLoginAs.setOnClickListener()
        {
            var intent = Intent(this, PasswrdReturningActivity::class.java)
            startActivity(intent)
        }


    }
}