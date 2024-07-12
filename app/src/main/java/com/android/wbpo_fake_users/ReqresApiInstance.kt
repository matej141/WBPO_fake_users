package com.android.wbpo_fake_users

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReqresApiInstance {
    private const val BASE_URL = "https://reqres.in/api/"

    val api: ReqresApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReqresApiService::class.java)
    }
}
