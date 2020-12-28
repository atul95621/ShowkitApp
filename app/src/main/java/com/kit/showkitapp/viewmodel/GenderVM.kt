package com.kit.showkitapp.viewmodel

import androidx.lifecycle.*
import com.kit.showkitapp.model.SignUpModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.http.Field

class GenderVM() : ViewModel() {

    var signUpLivedata: LiveData<Resource<SignUpModel>> = MutableLiveData()

    fun postSignup(
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
    }
}