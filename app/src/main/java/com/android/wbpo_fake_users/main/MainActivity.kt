package com.android.wbpo_fake_users.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.wbpo_fake_users.register.RegisterActivity
import com.android.wbpo_fake_users.users.UsersListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences: SharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val isRegistered = sharedPreferences.getBoolean("is_registered", false)

        if (isRegistered) {
            startActivity(Intent(this, UsersListActivity::class.java))
        } else {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        finish()
    }
}
