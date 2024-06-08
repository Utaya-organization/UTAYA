package com.dicoding.utaya

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.dicoding.utaya.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    private lateinit var signInButton: Button

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signInButton = findViewById(R.id.signInButton)

        signInButton.setOnClickListener {
            val intent = Intent(this, BottomActivity::class.java)
            startActivity(intent)
        }





    }

}

