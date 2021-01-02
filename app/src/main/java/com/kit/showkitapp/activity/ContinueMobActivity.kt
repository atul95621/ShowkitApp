package com.kit.showkitapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kit.showkitapp.R
import com.kit.showkitapp.model.ContinueMobDataModel
import com.kit.showkitapp.viewmodel.ContinueMobVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_continue_mob.*

class ContinueMobActivity : BaseActivity() {

    lateinit var continueMobVM: ContinueMobVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_mob)

        statusBarColorChanger()

        continueMobVM = ViewModelProviders.of(this).get(ContinueMobVM::class.java)

        clickListners()

        //observer
        getOtpDataObserver()
    }

    fun clickListners() {
        tvCont.setOnClickListener()
        {

            if (edtMobileNumber.text.toString().isNullOrEmpty()) {
                showToast(this, "Please enter the mobile number")
            } else if (edtMobileNumber.text.length != 10) {
                showToast(this, "Please enter the correct mobile number")
            } else {
                var intent = Intent(this, VerifyOtpActivity::class.java)
                intent.putExtra("MOBILE_NO", edtMobileNumber.text.toString());
                intent.putExtra("COUNTRY_CODE", ccp.defaultCountryCode.toString());

                startActivity(intent)
                /*  continueMobVM.sendOTP(
                      edtMobileNumber.text.toString().trim(),
                      ccp.defaultCountryCode.toString().trim(),
                      "true",
                      "register"
                  )*/
            }
        }
    }

    fun getOtpDataObserver() {

        continueMobVM.sendOTPLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as ContinueMobDataModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            var intent = Intent(this, VerifyOtpActivity::class.java)
                            intent.putExtra("MOBILE_NO", edtMobileNumber.text.toString());
                            intent.putExtra("COUNTRY_CODE", ccp.defaultCountryCode.toString());

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
}