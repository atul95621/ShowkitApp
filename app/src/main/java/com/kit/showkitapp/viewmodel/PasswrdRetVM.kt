package com.kit.showkitapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.showkitapp.model.SocialLoginModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.launch

class PasswrdRetVM : ViewModel() {


    var verifyPassLivedata: MutableLiveData<Resource<SocialLoginModel>> = MutableLiveData()

/*    fun veryPasscode(

    ) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            verifyPassLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                verifyPassLivedata.value = Resource.success(
                    data = api.socialLoginApi(
                        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZWI2MDEwNWNkNzc0NzQ0NjEwMjljNiIsImFsZ29yaXRobSI6IkhTMjU2IiwiaWF0IjoxNjA5NDA4NTI3LCJleHAiOjE2MTAyNzI1Mjd9.chNMEJK-MAnm9O1-ovZCzhp7OHj-0pvBSzmVugrtt90",

                        )
                )

            } catch (exception: Exception) {
                verifyPassLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }*/
}