package com.android.wbpo_fake_users

import com.android.wbpo_fake_users.users.retrofit.models.UsersListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class RegisterRequest(val email: String, val password: String)
data class RegisterResponse(val id: Int, val token: String)

interface ReqresApiService {
    @POST("register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UsersListResponse>
}