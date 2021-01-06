package com.kit.showkitapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kit.showkitapp.R
import com.kit.showkitapp.model.SignUpModel
import com.kit.showkitapp.model.ValidateIDModel
import com.kit.showkitapp.viewmodel.ProfileAddVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_profile_add.*

class ProfileAddActivity : BaseActivity() {

    var mobile_no = ""
    var country_code = ""
    var userName = ""
    var is_profile = ""
    var showkt_id = ""

    var isValidated = false
    lateinit var profileAddVM: ProfileAddVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_add)

        mobile_no = intent.getStringExtra("MOBILE_NO").toString()
        country_code = intent.getStringExtra("COUNTRY_CODE").toString()
        is_profile = intent.getStringExtra("IS_PROFILE_FLAG").toString()
        showkt_id = intent.getStringExtra("SHOWKT_ID_FLAG").toString()


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        profileAddVM = ViewModelProviders.of(this).get(ProfileAddVM::class.java)

        getValidateShokitObserver()

        validateId.setOnClickListener()
        {
            if (edtId.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please enter valid ID")
            } else {
                profileAddVM.validateId(edtId.text.toString().trim())
            }
        }

        imgNext.setOnClickListener()
        {

            if (edtName.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please enter username")
            } else if (edtId.text.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please enter valid ID")
            } else if (isValidated == false) {
                showToast(this, "Please validate your ID")
            } else {
                if (is_profile == "false") {
                    var intent = Intent(this, GenderBirthActivity::class.java)
                    intent.putExtra("MOBILE_NO", mobile_no);
                    intent.putExtra("COUNTRY_CODE", country_code);
                    intent.putExtra("USER_NAME", userName);
                    intent.putExtra("SHOWKT_ID_FLAG", showkt_id);

                    startActivity(intent)
                }
            }
        }
    }

    fun getValidateShokitObserver() {

        profileAddVM.validateIDLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as ValidateIDModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            isValidated = true

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