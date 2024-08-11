package com.capstone.suitmediatestmsib

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.suitmediatestmsib.response.ApiResponse
import com.capstone.suitmediatestmsib.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdViewModel(application: Application) : ViewModel() {
    companion object {
        private const val place = "ThirdViewModel"
    }

    private val usrrespons = MutableLiveData<ApiResponse>()
    private val showLoading = MutableLiveData<Boolean>()

    val userResponse: LiveData<ApiResponse> = usrrespons
    val Loadingld: LiveData<Boolean> = showLoading

    init {
        getUser()
    }

    private fun getUser() {
        showLoading.value = true
        val client = ApiConfig.getApiService().getApi()
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                showLoading.value = false
                if (response.isSuccessful) {
                    usrrespons.value = response.body()?.copy()
                } else {
                    Log.e(place, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                showLoading.value = false
                Log.e(place, "onFailure : ${t.message}")
            }
        })
    }
}