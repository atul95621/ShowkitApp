package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.showkitapp.model.SetPasscodeModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EnterPassVM : ViewModel() {

    var passcodeLivedata: MutableLiveData<Resource<SetPasscodeModel>> = MutableLiveData()

    fun setPasscode(token: Flow<String>, passcode: String) {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            passcodeLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                passcodeLivedata.value = Resource.success(
                    data = api.addPasscode(
                        token.toString(),
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