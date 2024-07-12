package com.android.wbpo_fake_users.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.android.wbpo_fake_users.RegisterRequest
import com.android.wbpo_fake_users.RegisterResponse
import com.android.wbpo_fake_users.ReqresApiInstance
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _registerResponse = MutableLiveData<RegisterResponse?>()
    val registerResponse: LiveData<RegisterResponse?> get() = _registerResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun register(email: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            val response: Response<RegisterResponse> = ReqresApiInstance.api.registerUser(
                RegisterRequest(email, password)
            )
            if (response.isSuccessful) {
                _registerResponse.value = response.body()
            } else {
                _error.value = response.errorBody()?.string()
            }
            _loading.value = false
        }
    }
}
