package com.android.wbpo_fake_users.users

import android.content.Context
import android.content.SharedPreferences

class FollowStatusRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun isUserFollowing(userId: Int): Boolean {
        return sharedPreferences.getBoolean(userId.toString(), false)
    }

    fun toggleFollowStatus(userId: Int) {
        val editor = sharedPreferences.edit()
        val isFollowing = isUserFollowing(userId)
        if (isFollowing) {
            editor.remove(userId.toString())
        } else {
            editor.putBoolean(userId.toString(), true)
        }
        editor.apply()
    }
}
