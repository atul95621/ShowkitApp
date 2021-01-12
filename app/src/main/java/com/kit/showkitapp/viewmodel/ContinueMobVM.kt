package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.kit.showkitapp.activity.ContinueMobActivity
import com.kit.showkitapp.model.ContinueMobDataModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpRetryException

class ContinueMobVM() : ViewModel() {

    var sendOTPLivedata: MutableLiveData<Resource<ContinueMobDataModel>> = MutableLiveData()

    fun sendOTP(
        mobile: String,
        country_code: String,
        from_mobile: String
    ) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch() {

            sendOTPLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                var response = api.sendOTP(
                    mobile,
                    country_code,
                    from_mobile
                )
                Log.e("RFit2.0 gson => ", "" + Gson().toJson(response))
                sendOTPLivedata.value = Resource.success(
                    data = response
                )

            } catch (exception: Exception) {
                sendOTPLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")

            }
        }
    }
}