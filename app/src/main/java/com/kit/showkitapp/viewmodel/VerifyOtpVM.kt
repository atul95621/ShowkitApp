package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kit.showkitapp.model.ContinueMobDataModel
import com.kit.showkitapp.model.VerifyOtpDataModel
import com.kit.showkitapp.model.SignUpModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Field

class VerifyOtpVM() : ViewModel() {

    var verifyOTPLivedata: MutableLiveData<Resource<VerifyOtpDataModel>> = MutableLiveData()
    var sendOTPLivedata: MutableLiveData<Resource<ContinueMobDataModel>> = MutableLiveData()

    fun verifyOTP(
        mobile: String,
        otp: String,
        device_id: String,
        device_type: String,
        device_token: String,
        user_curent_location: String,
        from_mobile: String


    ) {

        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            verifyOTPLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                verifyOTPLivedata.value = Resource.success(
                    data = api.verifyOTP(
                        mobile,
                        otp,
                        device_id,
                        device_type,
                        device_token,
                        user_curent_location,
                        from_mobile
                    )
                )

            } catch (exception: Exception) {
                verifyOTPLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }


    fun sendOTP(
        mobile: String,
        country_code: String,
        from_mobile: String,
        type: String

    ) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            sendOTPLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                sendOTPLivedata.value = Resource.success(
                    data = api.sendOTP(
                        mobile,
                        country_code,
                        from_mobile,
                        type
                    )
                )

            } catch (exception: Exception) {
                sendOTPLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }
}