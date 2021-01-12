package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.kit.showkitapp.model.SignUpModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class GenderVM() : ViewModel() {

    var signUpLivedata: MutableLiveData<Resource<SignUpModel>> = MutableLiveData()

/*    fun postSignup(
        user_fullname: String,
        user_phone: String,
        showkt_id: String,
        user_gender: String,
        user_dob: String,
        is_hide_dob: String,
        device_token: String,
        from_mobile: String,
        user_email: String,
        country_code: String,
        user_curent_location: String
    ) {
        
        signUpLivedata = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                var api = RetrofitBuilder.apiService
                emit(
                    Resource.success(
                        data = api.signUpUser(
                            user_fullname,
                            user_phone,
                            showkt_id,
                            user_gender,
                            user_dob,
                            is_hide_dob,
                            device_token,
                            from_mobile,
                            user_email,
                            country_code,
                            user_curent_location
                        )
                    )
                )

            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }*/


    fun postSignup(
        auth: String,
        user_fullname: RequestBody,
        showkt_id: RequestBody,
        user_gender: RequestBody,
        user_dob: RequestBody,
        is_hide_dob: RequestBody,
        device_token: RequestBody,
        device_id: RequestBody,
        device_type: RequestBody,
        from_mobile: RequestBody,
        current_location: RequestBody,
        imagePart: MultipartBody.Part,
    ) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch(Dispatchers.Main) {
            signUpLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                var response = api.updateUserProfile(
                    auth,
                    user_fullname,
                    showkt_id,
                    user_gender,
                    user_dob,
                    is_hide_dob,
                    device_token,
                    device_id,
                    device_type,
                    from_mobile,
                    current_location,
                    imagePart
                )

                Log.e(
                    "RFit2.0 gson => ",
                    "" + Gson().toJson(response) + "\n status code:" + response
                )

                signUpLivedata.value = Resource.success(
                    data = response
                )

            } catch (exception: Exception) {
                signUpLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")

                Log.e(
                    "error23",
                    "" + exception.stackTraceToString() + "\n" + exception.localizedMessage + "\n" + exception.printStackTrace()
                )
            }

        }
    }
}