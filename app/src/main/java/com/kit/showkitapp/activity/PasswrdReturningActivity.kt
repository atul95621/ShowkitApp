package com.kit.showkitapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.activity_passwrd_returning.*
import kotlinx.android.synthetic.main.activity_welcome_back.*

class PasswrdReturningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passwrd_returning)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

    /*    linPass.setOnClickListener()
        {
            var intent = Intent(this, OtpScreen::class.java)
            startActivity(intent)
        }*/
        edtPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!edtPass.text.isNullOrEmpty() && edtPass.text.length==4)
                {
                    var intent = Intent(this@PasswrdReturningActivity, OtpScreen::class.java)
                    startActivity(intent)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }
}