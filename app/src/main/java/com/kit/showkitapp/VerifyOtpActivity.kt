package com.kit.showkitapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import kotlinx.android.synthetic.main.activity_verify_otp.*

class VerifyOtpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        var text = "Please enter your registered number +91-2345434589"

        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            35, 50,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvText.text = spannable

        var textA = "Didn't recieve code? Send again"

        val spannab = SpannableString(textA)
        spannab.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.red)),
            20, 31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvSendAgain.text = spannab
    }
}