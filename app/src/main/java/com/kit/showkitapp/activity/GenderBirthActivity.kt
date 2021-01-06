package com.kit.showkitapp.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.kit.showkitapp.R
import com.kit.showkitapp.viewmodel.GenderVM
import com.mindorks.retrofit.coroutines.utils.Status
import kotlinx.android.synthetic.main.activity_gender_birth.*
import kotlinx.android.synthetic.main.activity_gender_birth.imgNext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.kit.showkitapp.model.SignUpModel
import com.legal.smart.util.ProgressBarClass
import com.legal.smart.util.SessionManager
import com.shokh.sample.BaseActivity

class GenderBirthActivity : BaseActivity() {

    lateinit var genderVM: GenderVM
    var mobile_no = ""
    var country_code = ""
    var userName = ""
    var showkitId = ""
    var gender = ""
    var hideDOB = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender_birth)

        mobile_no = intent.getStringExtra("MOBILE_NO").toString()
        country_code = intent.getStringExtra("COUNTRY_CODE").toString()
        userName = intent.getStringExtra("USER_NAME").toString()
        showkitId = intent.getStringExtra("SHOWKIT_ID").toString()

        genderVM = ViewModelProviders.of(this).get(GenderVM::class.java)

        clickListners()

        //observer
        getUserSignupDataObserver()
    }

    private fun clickListners() {

        linMale.setOnClickListener()
        {
            imgMaleWrite.visibility = View.VISIBLE
            imgFemaleWrite.visibility = View.GONE
            imgOthersWrite.visibility = View.GONE
            gender = "Male"
        }

        linFemale.setOnClickListener()
        {
            imgMaleWrite.visibility = View.GONE
            imgFemaleWrite.visibility = View.VISIBLE
            imgOthersWrite.visibility = View.GONE
            gender = "Female"
        }

        linOthers.setOnClickListener()
        {
            imgMaleWrite.visibility = View.GONE
            imgFemaleWrite.visibility = View.GONE
            imgOthersWrite.visibility = View.VISIBLE
            gender = "Others"
        }

        tvDate.setOnClickListener()
        {
            openDatePicker()
        }

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        switchDOB?.setOnCheckedChangeListener { _, isChecked ->
            hideDOB = if (isChecked) "true" else "false"

        }

        imgNext.setOnClickListener()
        {

            if (gender.toString().trim().isNullOrEmpty()) {
                showToast(this, "Please choose gender")
            } else if (tvDate.text.toString().trim().equals("DD/MM/YYYY")) {
                showToast(this, "Please enter valid date")

            } else {
                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
//            signupApi()
            }

        }
    }

    fun signupApi() {
        lifecycle.coroutineScope.launch {
            var access_token = sessionManager.getData(SessionManager.ACCESS_TOKEN)
            genderVM.postSignup(
                access_token.toString(),
                userName,
                userName,
                showkitId,
                gender,
                tvDate.text.toString(),
                hideDOB,
                "dd41f54d54fdfklfde4r6541w 56456456e4r",
                "", "", ""

            )
        }
    }

    fun getUserSignupDataObserver() {

        genderVM.signUpLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as SignUpModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            var intent = Intent(this, HomeActivity::class.java)
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

    private fun openDatePicker() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                tvDate.setText("" + dayOfMonth + "/" + monthOfYear.plus(1) + "/" + year)

            },
            year,
            month,
            day
        )
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        dpd.show()
    }
}