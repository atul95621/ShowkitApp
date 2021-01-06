package com.kit.showkitapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.kit.showkitapp.R
import com.kit.showkitapp.model.SocialLoginModel
import com.kit.showkitapp.viewmodel.LoginVM
import com.legal.smart.util.ProgressBarClass
import com.legal.smart.util.SessionManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_continue_mob.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginActivity : BaseActivity() {

    lateinit var loginVM: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginVM = ViewModelProviders.of(this).get(LoginVM::class.java)

        linOtp.setOnClickListener()
        {
            var intent = Intent(this, ContinueMobActivity::class.java)
            startActivity(intent)
        }

        linGoogle.setOnClickListener()
        {
            loginVM.loginApi(
                "test01@test.com", "social", "", "", "", "true"
            )
        }

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //observer
        getSocialLoginDataObserver()
    }

    fun getSocialLoginDataObserver() {

        loginVM.loginLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as SocialLoginModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {

                            lifecycleScope.launch {
                                sessionManager.setData(
                                    SessionManager.ACCESS_TOKEN,
                                    modelObj.access_token
                                )
                                sessionManager.setData(
                                    SessionManager.FIRST_NAME,
                                    modelObj.data.firstName
                                )
                                sessionManager.setData(
                                    SessionManager.LAST_NAME,
                                    modelObj.data.lastName
                                )
                            }

                            var intent = Intent(this, EnterPasscodeActivity::class.java)
                            intent.putExtra("MOBILE_NO", "");
                            intent.putExtra("COUNTRY_CODE", "");
                            intent.putExtra("SHOWKT_ID_FLAG", "false");
                            intent.putExtra("IS_PROFILE_FLAG", "false");
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