package com.dev.helloworld.api

import com.dev.helloworld.model.request.SignInRequest
import com.dev.helloworld.model.request.SignUpRequest
import com.dev.helloworld.model.response.SignInResponse
import com.dev.helloworld.util.customEnqueue
import javax.inject.Inject

class NetworkRepository @Inject constructor(val api: NetworkInterface) {

    fun postSignIn(
        request: SignInRequest,
        onSuccess: (SignInResponse) -> Unit = {},
        onFailure: (String) -> Unit = {},
        onError: () -> Unit = {}
    ) {
        api.postSignIn(request).customEnqueue(
            onSuccess = { it.data?.let { res -> onSuccess(res) } ?: onFailure(it.message) },
            onFailure = { onFailure(it.message()) },
            onError = { onError() }
        )
    }

    fun postSignUp(
        request: SignUpRequest,
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit = {},
        onError: () -> Unit = {}
    ) {
        api.postSignUp(request).customEnqueue(
            onSuccess = { if (it.success) onSuccess() else onFailure(it.message) },
            onFailure = { onFailure(it.message()) },
            onError = { onError() }
        )
    }

}