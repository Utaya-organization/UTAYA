package com.dicoding.utaya.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.utaya.BottomActivity
import com.dicoding.utaya.data.Result
import com.dicoding.utaya.data.response.login.ResponseLogin
import com.dicoding.utaya.data.utils.Preference
import com.dicoding.utaya.databinding.ActivityLoginBinding
import com.dicoding.utaya.ui.ViewModelFactory
import com.dicoding.utaya.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btToRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
        }

        binding.btLogin.setOnClickListener {
            val username = binding.usernameInputField.text.toString()
            val password = binding.passwordInputField.text.toString()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            loginViewModel.login(username, password).observe(this) { result ->
                if (result != null) {
                    when(result) {
                        is Result.Loading -> {
                            // showLoading(true)
                        }
                        is Result.Success -> {
                            Log.d("LoginActivity", "Login result: ${result.data}")
                            processLogin(result.data)
                            // showLoading(false)
                        }
                        is Result.Error -> {
                            // showLoading(false)
                            Log.e("LoginActivity", "Login error: ${result.error}")
                            Toast.makeText(this, result.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

        val isFromSignUp: Boolean = intent.getBooleanExtra("is_from_sign_up", false)
        if (isFromSignUp) {
            handleOnBackPressed()
        }
    }

    private fun processLogin(data: ResponseLogin) {
        Log.d("LoginActivity", "Processing login with data: $data")
        val dataLogin = data.dataLogin
        if (dataLogin != null) {
            Log.d("LoginActivity", "Login successful, saving token and navigating to BottomActivity")
            Preference.saveToken(dataLogin.token, this)
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, BottomActivity::class.java))
            finish()
        } else {
            Log.e("LoginActivity", "Login failed: dataLogin is null")
            Toast.makeText(this, "Login failed: ${data.message}", Toast.LENGTH_LONG).show()
        }
    }



    private fun handleOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}
