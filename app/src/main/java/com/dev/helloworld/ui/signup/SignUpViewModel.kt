package com.dev.helloworld.ui.signup

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.helloworld.api.NetworkRepository
import com.dev.helloworld.model.request.SignUpRequest
import com.dev.helloworld.util.longToast
import com.dev.helloworld.util.shortToast
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    val repository: NetworkRepository
) : ViewModel() {

    val _id = MutableLiveData<String>()
    val _pw = MutableLiveData<String>()
    val _name = MutableLiveData<String>()
    val _email = MutableLiveData<String>()
    val _phone = MutableLiveData<String>()

    val id : LiveData<String> = _id
    val pw : LiveData<String> = _pw
    val name : LiveData<String> = _name
    val email : LiveData<String> = _email
    val phone : LiveData<String> = _phone

    fun postSignUp(v: View) {
        if (id.value == null || pw.value == null || name.value == null ||
                email.value == null || phone.value == null) return

        val context = v.context

        repository.postSignUp(
            SignUpRequest(id.value!!, pw.value!!, name.value!!, email.value!!, phone.value!!),
            onSuccess = {
                val intent = Intent().apply {
                    putExtra("id", id.value!!)
                    putExtra("pw", pw.value!!)
                }
                (context as Activity).setResult(RESULT_OK, intent)
                context.finish()
            },
            onFailure = { msg -> context.shortToast(msg) },
            onError = { context.longToast("네트워크 연결 실패")}
        )
    }

}