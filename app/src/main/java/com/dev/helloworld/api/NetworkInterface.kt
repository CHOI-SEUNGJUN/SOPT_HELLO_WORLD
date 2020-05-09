package com.dev.helloworld.api

import com.dev.helloworld.model.request.SignInRequest
import com.dev.helloworld.model.request.SignUpRequest
import com.dev.helloworld.model.response.BaseResponse
import com.dev.helloworld.model.response.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkInterface {

    @POST(Endpoints.signIn)
    suspend fun postSignIn(@Body request: SignInRequest) : SignInResponse?

    @POST(Endpoints.signUp)
    suspend fun postSignUp(@Body request: SignUpRequest)

}