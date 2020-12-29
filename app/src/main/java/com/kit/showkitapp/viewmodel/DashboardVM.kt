package com.kit.showkitapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kit.showkitapp.model.CategoryIntrestModel
import com.kit.showkitapp.model.LanguageModel
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.launch

class DashboardVM() : ViewModel() {

    var languageLivedata: MutableLiveData<Resource<LanguageModel>> = MutableLiveData()
    var categoryLivedata: MutableLiveData<Resource<CategoryIntrestModel>> = MutableLiveData()


    fun getLanguageApi() {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            languageLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                languageLivedata.value = Resource.success(
                    data = api.getLanguageApi(""
                    )
                )

            } catch (exception: Exception) {
                languageLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }


    fun getCategoryApi() {
        Log.e("resp send otp:", "hitting")
        viewModelScope.launch {
            categoryLivedata.value = Resource.loading(data = null)
            try {
                var api = RetrofitBuilder.apiService
                categoryLivedata.value = Resource.success(
                    data = api.getCategoryApi(""
                    )
                )

            } catch (exception: Exception) {
                categoryLivedata.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }
}