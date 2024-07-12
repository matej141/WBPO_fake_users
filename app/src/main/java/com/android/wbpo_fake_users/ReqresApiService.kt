package com.android.wbpo_fake_users

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(val email: String, val password: String)
data class RegisterResponse(val id: Int, val token: String)
data class ErrorResponse(val error: String)

interface ReqresApiService {
    @POST("register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>
}