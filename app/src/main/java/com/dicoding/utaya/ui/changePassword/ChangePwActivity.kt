package com.dicoding.utaya.ui.changePassword

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.dicoding.utaya.data.Result
import com.dicoding.utaya.data.response.changePw.ResponseChangePw
import com.dicoding.utaya.databinding.ActivityChangePasswordBinding
import com.dicoding.utaya.ui.Bottom.profile.ProfileFragment
import com.dicoding.utaya.ui.ViewModelFactory

class ChangePwActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding

    private val changePwViewModel: ChangePwViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btChangePw.setOnClickListener {
            val password = binding.etPasswordOld.text.toString()
            val newPassword = binding.etPasswordNew.text.toString()
            val confirmNewPassword = binding.etConfirmPasswordNew.text.toString()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            binding.pbChangePw.visibility = View.VISIBLE

            changePwViewModel.changePw(password, newPassword, confirmNewPassword).observe(this@ChangePwActivity) { result ->
                binding.pbChangePw.visibility = View.GONE
                if (result != null) {
                    when(result) {
                        is Result.Loading -> {
                            binding.pbChangePw.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            Log.d("ChangePwActivity", "Change Password result: ${result.data}")
                            processChange(result.data)
                        }
                        is Result.Error -> {
                            Log.e("ChangePwActivity", "Change Password error: ${result.error}")
                            handleError(result.error)
                        }
                    }
                }
            }
        }
    }

    private fun processChange(data: ResponseChangePw) {
        Toast.makeText(this, "Password Berhasil Diganti", Toast.LENGTH_LONG).show()
        navigateToProfileFragment()
    }

    private fun navigateToProfileFragment() {
        val profileFragment = ProfileFragment()
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, profileFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()
    }

    private fun handleError(error: String) {
        when (error) {
            "HTTP 403" -> {
                Toast.makeText(this, "Akses ditolak. Periksa izin Anda.", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(this, "Terjadi kesalahan: $error", Toast.LENGTH_LONG).show()
            }
        }
    }
}
