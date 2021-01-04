package com.kit.showkitapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kit.showkitapp.R
import com.kit.showkitapp.model.ContinueMobDataModel
import com.kit.showkitapp.model.VerifyOtpDataModel
import com.kit.showkitapp.viewmodel.VerifyOtpVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_continue_mob.*
import kotlinx.android.synthetic.main.activity_verify_otp.*


class VerifyOtpActivity : BaseActivity() {


    lateinit var verifyOtpVM: VerifyOtpVM

    var mobileNo = ""
    var countryCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        statusBarColorChanger()

        verifyOtpVM = ViewModelProviders.of(this).get(VerifyOtpVM::class.java)

        getIntentss()

        setColorText()

        clickListners()

        //observers
        getVerifyOtpDataObserver()
        getSendOtpDataObserver()
    }


    private fun clickListners() {
        imgNext.setOnClickListener()
        {
            if (edt1.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please fill otp correctly")
            } else if (edt2.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please fill otp correctly")
            } else if (edt3.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please fill otp correctly")
            } else if (edt4.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please fill otp correctly")
            } else {
                var intent = Intent(this, EnterPasscodeActivity::class.java)
                intent.putExtra("MOBILE_NO", mobileNo);
                intent.putExtra("COUNTRY_CODE", countryCode);

                startActivity(intent)
                /* verifyOtpVM.verifyOTP(
                     mobileNo,
                     "919058",
                     "",
                     "",
                     "32456",
                     "sfsfsf",
                     "sdfsdf"
                 )*/
            }
        }

        tvSendAgain.setOnClickListener()
        {
            verifyOtpVM.sendOTP(
                mobileNo,
                countryCode,
                "true",
                "register"
            )
        }

        edt1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (edt1.getText().toString().length == 1) //size as per your requirement
                {
                    edt2.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        edt2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (edt2.getText().toString().length == 1) //size as per your requirement
                {
                    edt3.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        edt3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (edt3.getText().toString().length == 1) //size as per your requirement
                {
                    edt4.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun setColorText() {
        var text = "Please enter your registered number +91-"
        var text2 = text + mobileNo
        val spannable = SpannableString(text2)
        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            35, 50,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvText.text = spannable

        var textA = "Didn't recieve code? Send again"

        val spannab = SpannableString(textA)
        spannab.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.marron_full)),
            20, 31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvSendAgain.text = spannab

    }

    private fun getIntentss() {
        mobileNo = intent.getStringExtra("MOBILE_NO").toString()
        countryCode = intent.getStringExtra("COUNTRY_CODE").toString()
    }


    fun getVerifyOtpDataObserver() {
        verifyOtpVM.verifyOTPLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as VerifyOtpDataModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            var intent = Intent(this, EnterPasscodeActivity::class.java)
                            intent.putExtra("MOBILE_NO", mobileNo);
                            intent.putExtra("COUNTRY_CODE", countryCode);

                            startActivity(intent)
                            Toast.makeText(this, modelObj.message, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, modelObj.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        ProgressBarClass.dialog.dismiss()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.e("resperror", it.message.toString())

                    }
                    Status.LOADING -> {
                        ProgressBarClass.progressBarCalling(this)
                    }
                }
            }
        })

    }

    fun getSendOtpDataObserver() {

        verifyOtpVM.sendOTPLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as ContinueMobDataModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            Toast.makeText(this, modelObj.message, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, modelObj.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        ProgressBarClass.dialog.dismiss()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.e("resperror", it.message.toString())

                    }
                    Status.LOADING -> {
                        ProgressBarClass.progressBarCalling(this)
                    }
                }
            }
        })
    }
}
