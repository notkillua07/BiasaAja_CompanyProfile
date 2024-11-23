package com.example.biasaaja_companyprofile.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.app.viewmodel.UserViewModel
import com.example.biasaaja_companyprofile.databinding.ActivityLoginBinding
import com.example.biasaaja_companyprofile.util.SessionManager

class LoginActivity : AppCompatActivity(), LoginClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sessionManager = SessionManager(this)
        binding.listener = this

        if (sessionManager.isLoggedIn()) {
            // If logged in, navigate to HomeFragment directly
            navigateToHome()
        }

        // Observe login status from ViewModel
        viewModel.loginStatus.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                navigateToHome()
            } else {
                // Show error message if login fails
                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to RegisterActivity
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToHome() {
        // Navigate to the home screen (replace with your actual home activity)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish() // Finish the login activity
    }

    override fun onLoginClick(v: View) {
        val username = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()
        viewModel.login(username, password)
    }
}
