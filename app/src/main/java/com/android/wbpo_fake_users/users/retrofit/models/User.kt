package com.android.wbpo_fake_users.users.retrofit.models

data class User(
    var id: Int = 0,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var avatar: String = ""
)