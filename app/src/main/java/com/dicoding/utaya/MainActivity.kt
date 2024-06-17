package com.dicoding.utaya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.dicoding.utaya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.apply {
//            signInButton.setOnClickListener {
//                startActivity(Intent(this@MainActivity, BottomActivity::class.java))
//                finish()
//            }
//            signUpLogin.setOnClickListener {
//                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
//                finish()
//            }
//
//        }

    }

    private fun postLogin(){


    }

}

