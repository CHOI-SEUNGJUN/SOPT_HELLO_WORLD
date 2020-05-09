package com.dev.helloworld.ui.signup

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.helloworld.api.NetworkClient
import com.dev.helloworld.model.request.SignUpRequest
import com.dev.helloworld.util.shortToast
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : ViewModel() {

    val _name = MutableLiveData<String>()
    val _email = MutableLiveData<String>()
    val _phone = MutableLiveData<String>()

    val _id: MutableLiveData<String> = MutableLiveData()
    val id: LiveData<String> = _id

    val _pw: MutableLiveData<String> = MutableLiveData()
    val pw: LiveData<String> = _pw

    private val _isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun postSignUp() {
        if (_id.value == null || _pw.value == null || _name.value == null ||
            _email.value == null || _phone.value == null) return

        viewModelScope.launch {
            kotlin.runCatching {
                NetworkClient.apiService.postSignUp(
                    SignUpRequest(id.value!!, pw.value!!, _name.value!!, _email.value!!, _phone.value!!)
                )
            }.onSuccess {
                _isSuccess.value = true
            }.onFailure {
                Timber.e(it.toString())
                _isSuccess.value = false
            }
        }
    }

}