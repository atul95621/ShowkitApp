package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kit.showkitapp.model.SocialLoginModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.launch

class LoginVM() : ViewModel() {

    var loginLivedata: MutableLiveData<Resource<SocialLoginModel>> = MutableLiveData()

    fun loginApi(
        email: String,
        type: String,
        s2: String,
        s3: String,
        s4: String,
        from_mobile: String
    ) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            loginLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                loginLivedata.value = Resource.success(
                    data = api.socialLoginApi(
                        email, type, s2, s3, s4, from_mobile
                    )
                )

            } catch (exception: Exception) {
                loginLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }
}