package com.kit.showkitapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.kit.showkitapp.R
import com.kit.showkitapp.model.SetPasscodeModel
import com.kit.showkitapp.utils.SessionManager
import com.kit.showkitapp.viewmodel.EnterPassVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_enter_passcode.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class EnterPasscodeActivity : BaseActivity() {

    lateinit var enterPassVM: EnterPassVM

    var mobile_no = ""
    var country_code = ""
    var showkt_id = ""
    var is_profile = ""
    var eyeFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_passcode)

        mobile_no = intent.getStringExtra("MOBILE_NO").toString()
        country_code = intent.getStringExtra("COUNTRY_CODE").toString()
        showkt_id = intent.getStringExtra("SHOWKT_ID_FLAG").toString()
        is_profile = intent.getStringExtra("IS_PROFILE_FLAG").toString()

        enterPassVM = ViewModelProviders.of(this).get(EnterPassVM::class.java)


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        imgEye.setOnClickListener()
        {
            if (eyeFlag == false) {
                imgEye.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_eye));
                eyeFlag = true
                edtPasscode.setTransformationMethod(PasswordTransformationMethod())
                edtPasscode.setSelection(edtPasscode.getText().length);

            } else {
                imgEye.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.eye));

                eyeFlag = false
                edtPasscode.setTransformationMethod(null)
                edtPasscode.setSelection(edtPasscode.getText().length);

            }

        }

        imgNextScreen.setOnClickListener()
        {
            if (edtPasscode.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please set your passcode")
            } else {
/*
                var intent = Intent(this, ProfileAddActivity::class.java)
                intent.putExtra("MOBILE_NO", mobile_no);
                intent.putExtra("COUNTRY_CODE", country_code);
                intent.putExtra("SHOWKT_ID_FLAG", showkt_id);
                startActivity(intent)*/


                lifecycleScope.launch(Dispatchers.IO)
                {
                    var token = sessionManager.getData(SessionManager.ACCESS_TOKEN)
                    token.collect { value ->
                        Log.e("token", "" + value)
                        enterPassVM.setPasscode(
                            value,
                            edtPasscode.text.toString().trim()
                        )
                    }
                }


            }
        }

        getAddPasscodeDataObserver()
    }

    fun getAddPasscodeDataObserver() {

        enterPassVM.passcodeLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as SetPasscodeModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            if (showkt_id == "false") {
                                var intent = Intent(this, ProfileAddActivity::class.java)
                                intent.putExtra("MOBILE_NO", mobile_no);
                                intent.putExtra("COUNTRY_CODE", country_code);
                                intent.putExtra("SHOWKT_ID_FLAG", showkt_id);
                                intent.putExtra("IS_PROFILE_FLAG", is_profile);
                                startActivity(intent)
                            }

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