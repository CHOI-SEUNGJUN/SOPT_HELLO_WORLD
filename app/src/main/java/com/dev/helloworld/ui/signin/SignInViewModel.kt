package com.dev.helloworld.ui.signin

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.helloworld.api.NetworkRepository
import com.dev.helloworld.model.request.SignInRequest
import com.dev.helloworld.ui.some.SomeActivity
import com.dev.helloworld.util.shortToast
import javax.inject.Inject

class SignInViewModel @Inject constructor(
                      val repository : NetworkRepository)
    : ViewModel() {

    val _id = MutableLiveData<String>()
    val _pw = MutableLiveData<String>()

    val id : LiveData<String> = _id
    val pw : LiveData<String> = _pw

    fun postSignIn(v: View) {
        if (id.value == null || pw.value == null) return

        val context = v.context

        repository.postSignIn(
            request = SignInRequest(id.value!!, pw.value!!),
            onSuccess = {
                Intent(context, SomeActivity::class.java)
                    .apply { addFlags(FLAG_ACTIVITY_NEW_TASK) }
                    .run { context.startActivity(this) }
                (context as Activity).finish()
            },
            onFailure = { msg ->
                context.shortToast(msg)
            },
            onError = {
                context.shortToast("네트워크 연결 실패")
            }
        )
    }

    fun getInfoFromSignUp(id: String, pw: String) {
        _id.value = id
        _pw.value = pw
    }

}