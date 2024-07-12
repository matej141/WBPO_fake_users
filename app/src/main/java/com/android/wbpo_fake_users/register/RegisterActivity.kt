package com.android.wbpo_fake_users.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.android.wbpo_fake_users.databinding.ActivityRegisterBinding
import com.android.wbpo_fake_users.users.UsersListActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener { register() }
    }

    private fun register() {
        startUsersListActivity()
    }

    private fun startUsersListActivity() {
        val intent = Intent(this, UsersListActivity::class.java)
        startActivity(intent)
        finish()
    }
}