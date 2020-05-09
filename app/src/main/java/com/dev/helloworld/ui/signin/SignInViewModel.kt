package com.dev.helloworld.ui.signin

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.helloworld.api.NetworkClient
import com.dev.helloworld.model.request.SignInRequest
import com.dev.helloworld.ui.some.SomeActivity
import com.dev.helloworld.util.shortToast
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SignInViewModel @Inject constructor()
    : ViewModel() {

    val _id: MutableLiveData<String> = MutableLiveData()
    val id: LiveData<String> = _id

    val _pw: MutableLiveData<String> = MutableLiveData()
    val pw: LiveData<String> = _pw

    private val _isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun postSignIn() {
        if (id.value == null || pw.value == null) return

        viewModelScope.launch {
            kotlin.runCatching {
                NetworkClient.apiService.postSignIn(
                    request = SignInRequest(id.value!!, pw.value!!)
                )
            }.onSuccess {
                _isSuccess.value = true
            }.onFailure {
                Timber.e(it)
                _isSuccess.value = false
            }
        }





        /*repository.postSignIn(
            request = SignInRequest(id.value!!, pw.value!!),
            onSuccess = {

            },
            onFailure = { msg ->
                context.shortToast(msg)
            },
            onError = {
                context.shortToast("네트워크 연결 실패")
            }
        )*/
    }

    fun getInfoFromSignUp(id: String, pw: String) {
        _id.value = id
        _pw.value = pw
    }

}