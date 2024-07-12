package com.android.wbpo_fake_users.users.retrofit.models

data class UsersListResponse(
    var page: Int = 0,
    var perPage: Int = 0,
    var total: Int = 0,
    var totalPages: Int = 0,
    var data: List<User> = listOf(),
    var support: Support = Support()
)