package com.kit.showkitapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.activity_enter_passcode.*

class EnterPasscodeActivity : AppCompatActivity() {

    var mobile_no = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_passcode)

        mobile_no = intent.getStringExtra("MOBILE_NO").toString()


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        imgNextScreen.setOnClickListener()
        {
            var intent = Intent(this, ProfileAddActivity::class.java)
            startActivity(intent)
        }
    }
}