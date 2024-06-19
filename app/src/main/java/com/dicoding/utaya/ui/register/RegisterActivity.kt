package com.dicoding.utaya.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.utaya.data.Result
import com.dicoding.utaya.data.response.register.ResponseRegister
import com.dicoding.utaya.databinding.ActivityRegisterBinding
import com.dicoding.utaya.ui.ViewModelFactory
import com.dicoding.utaya.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val registerViewModel: RegisterViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvToLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }

        binding.btRegister.setOnClickListener {
            val username = binding.etUsernameRegister.text.toString()
            val password = binding.etPasswordRegister.text.toString()
            val confirmPassword = binding.etConfirmPasswordRegister.text.toString()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            binding.pbRegister.visibility = View.VISIBLE

            registerViewModel.register(username, password, confirmPassword).observe(this@RegisterActivity) { result ->
                binding.pbRegister.visibility = View.GONE
                when (result) {
                    is Result.Loading -> {
                        binding.pbRegister.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        Log.d("RegisterActivity", "Register result: ${result.data}")
                        processRegister(result.data)
                    }
                    is Result.Error -> {
                        Log.e("RegisterActivity", "Register error: ${result.error}")
                        Toast.makeText(this@RegisterActivity, result.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun processRegister(data: ResponseRegister) {
        Toast.makeText(this, "Sign Up berhasil, silahkan login!", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
