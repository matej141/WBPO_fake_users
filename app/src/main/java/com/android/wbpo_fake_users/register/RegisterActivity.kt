package com.android.wbpo_fake_users.register

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.wbpo_fake_users.databinding.ActivityRegisterBinding
import com.android.wbpo_fake_users.users.UsersListActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRegisterButtonOnClickListener()

        observeRegisterResponse()
        observeLoadingValue()
        observeErrorValue()
    }

    private fun setRegisterButtonOnClickListener() {
        binding.registerButton.setOnClickListener {
            val email = binding.registerUsernameEditText.text.toString()
            val password = binding.registerPasswordEditText.text.toString()
            registerViewModel.register(email, password)
        }
    }

    private fun startUsersListActivity() {
        val intent = Intent(this, UsersListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observeRegisterResponse() {
        registerViewModel.registerResponse.observe(this) { response ->
            response?.let {
                Toast.makeText(
                    this,
                    "Registration successful! Token: ${it.token}",
                    Toast.LENGTH_LONG
                ).show()
                saveDataIntoSharedPreferences()
                // startUsersListActivity()
            }
        }
    }

    private fun saveDataIntoSharedPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("is_registered", true).apply()
    }

    private fun observeLoadingValue() {
        registerViewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun observeErrorValue() {
        registerViewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Error: $it", Toast.LENGTH_LONG).show()
            }
        }
    }
}