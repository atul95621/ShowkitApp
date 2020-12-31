package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.showkitapp.model.SetPasscodeModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.launch

class EnterPassVM : ViewModel() {

    var passcodeLivedata: MutableLiveData<Resource<SetPasscodeModel>> = MutableLiveData()

    fun setPasscode(passcode: String) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            passcodeLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                passcodeLivedata.value = Resource.success(
                    data = api.addPasscode(
                        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZWI2MDEwNWNkNzc0NzQ0NjEwMjljNiIsImFsZ29yaXRobSI6IkhTMjU2IiwiaWF0IjoxNjA5NDA4NTI3LCJleHAiOjE2MTAyNzI1Mjd9.chNMEJK-MAnm9O1-ovZCzhp7OHj-0pvBSzmVugrtt90",
                        passcode
                    )
                )

            } catch (exception: Exception) {
                passcodeLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }
}