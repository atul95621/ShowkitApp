package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.showkitapp.model.SignUpModel
import com.kit.showkitapp.model.ValidateIDModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.launch

class ProfileAddVM() : ViewModel() {

    var validateIDLivedata: MutableLiveData<Resource<ValidateIDModel>> = MutableLiveData()

    fun validateId(
        showkitId: String
    ) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            validateIDLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                validateIDLivedata.value = Resource.success(
                    data = api.validateID(
                        showkitId
                    )
                )

            } catch (exception: Exception) {
                validateIDLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }
}