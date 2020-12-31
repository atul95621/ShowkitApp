package com.kit.showkitapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kit.showkitapp.R
import com.kit.showkitapp.model.SetPasscodeModel
import com.kit.showkitapp.viewmodel.EnterPassVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_enter_passcode.*

class EnterPasscodeActivity : BaseActivity() {

    lateinit var enterPassVM: EnterPassVM
    var mobile_no = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_passcode)

        mobile_no = intent.getStringExtra("MOBILE_NO").toString()
        enterPassVM = ViewModelProviders.of(this).get(EnterPassVM::class.java)


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        imgNextScreen.setOnClickListener()
        {
            if (edtPasscode.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please set your passcode")
            } else {
                enterPassVM.setPasscode(edtPasscode.text.toString().trim())
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
                            var intent = Intent(this, ProfileAddActivity::class.java)
                            intent.putExtra("MOBILE_NO", mobile_no);
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