package com.kit.showkitapp.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.coroutineScope
import com.kit.showkitapp.R
import com.kit.showkitapp.model.SignUpModel
import com.kit.showkitapp.utils.SessionManager
import com.kit.showkitapp.viewmodel.GenderVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_gender_birth.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class GenderBirthActivity() : BaseActivity() {

    lateinit var genderVM: GenderVM
    var mobile_no = ""
    var country_code = ""
    var userName = ""
    var showkitId = ""
    var gender = ""
    var hideDOB = ""
    var file: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender_birth)

        mobile_no = intent.getStringExtra("MOBILE_NO").toString()
        country_code = intent.getStringExtra("COUNTRY_CODE").toString()
        userName = intent.getStringExtra("USER_NAME").toString()
        showkitId = intent.getStringExtra("SHOWKIT_ID").toString()
        file = File(intent.getStringExtra("IMAGE_FILE"))


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
                /*var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)*/
                signupApi()
            }

        }
    }

    fun signupApi() {
        var imagePart: MultipartBody.Part? = null
        /*

        // making path for image
        val file = File(path)*/

        // Create a request body with file and image media type
        val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
        // Create MultipartBody.Part using file request-body,file name and part name
        //photo is the KEY that is to be sent over server
        imagePart = MultipartBody.Part.createFormData("file", file?.getName(), fileReqBody)

        lifecycle.coroutineScope.launch(Dispatchers.IO) {

            var access_token = sessionManager.getData(SessionManager.ACCESS_TOKEN)
            access_token.collect { value ->
                Log.e("token", "" + value)
                genderVM.postSignup(
                    value,
                    stringConvertToRequestBody(userName),
                    stringConvertToRequestBody(showkitId),
                    stringConvertToRequestBody(gender),
                    stringConvertToRequestBody(tvDate.text.toString()),
                    stringConvertToRequestBody(hideDOB),
                    stringConvertToRequestBody(""),
                    stringConvertToRequestBody(""),
                    stringConvertToRequestBody(""),
                    stringConvertToRequestBody(""),
                    stringConvertToRequestBody(""),
                    imagePart

                )
            }

        }
    }

    fun getUserSignupDataObserver() {

        genderVM.signUpLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("respgenderr", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        Log.e("respgender", "" + resource.data.toString())

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