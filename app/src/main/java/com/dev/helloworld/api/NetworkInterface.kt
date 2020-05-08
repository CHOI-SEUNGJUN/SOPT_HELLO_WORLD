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
    fun postSignIn(@Body request: SignInRequest) : Call<BaseResponse<SignInResponse>>

    @POST(Endpoints.signUp)
    fun postSignUp(@Body request: SignUpRequest) : Call<BaseResponse<Any?>>

}