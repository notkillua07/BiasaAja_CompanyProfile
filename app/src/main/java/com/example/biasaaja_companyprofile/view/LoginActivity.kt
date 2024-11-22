package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.app.viewmodel.UserViewModel
import com.example.biasaaja_companyprofile.databinding.ActivityLoginBinding
import com.example.biasaaja_companyprofile.util.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sessionManager = SessionManager(this)

        // Check if the user is already logged in
        if (sessionManager.isLoggedIn()) {
            navigateToHome()
        }

        // Observe login status
        viewModel.loginStatus.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                sessionManager.saveLoginState(binding.txtUsername.text.toString())
                navigateToHome()
            } else {
                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle login button click
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.login(username, password)
        }

        // Navigate to RegisterActivity
        binding.btnRegister.setOnClickListener {
            val action =LoginFragmentDirections.actionLoginToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun navigateToHome() {
        // Navigate to the home screen (replace with your actual home activity)
//        val action = LoginFragmentDirections.actionLoginToWhatWePlayFragment()
//        Navigation.findNavController(view).navigate(action)
        finish() // Finish the login activity
    }
}
