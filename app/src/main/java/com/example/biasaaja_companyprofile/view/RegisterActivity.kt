package com.example.biasaaja_companyprofile.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.app.viewmodel.UserViewModel
import com.example.biasaaja_companyprofile.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), RegisterClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.listener = this

        // Disable submit button initially
        binding.btnSubmit.isEnabled = false

        // Enable button only if "I agree" is checked
        binding.chkAgree.setOnCheckedChangeListener { _, isChecked ->
            binding.btnSubmit.isEnabled = isChecked
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Observe registration status
        viewModel.registerStatus.observe(this) { status ->
            when {
                status == "Registration successful!" -> {
                    // Navigate to login screen
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRegisterClick(v: View) {
        val firstname = binding.txtFirstName.text.toString().trim()
        val lastname = binding.txtLastName.text.toString().trim()
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString()
        val confirmPassword = binding.txtRepeatPassword.text.toString()

        if (password == confirmPassword) {
            viewModel.register(firstname, lastname, username, password, confirmPassword)
        } else {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
        }
    }
}