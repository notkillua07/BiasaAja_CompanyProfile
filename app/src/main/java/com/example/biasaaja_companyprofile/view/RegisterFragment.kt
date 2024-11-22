package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.app.viewmodel.UserViewModel
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(), RegisterClickListener {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.listener = this

        // Disable submit button initially
        binding.btnSubmit.isEnabled = false

        // Enable button only if "I agree" is checked
        binding.chkAgree.setOnCheckedChangeListener { _, isChecked ->
            binding.btnSubmit.isEnabled = isChecked
        }

        binding.btnBack.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegsiterToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        // Observe registration status
        viewModel.registerStatus.observe(viewLifecycleOwner) { status ->
            when {
                status == "Registration successful!" -> {
                    // Navigate to login screen
                    val action = RegisterFragmentDirections.actionRegsiterToLoginFragment()
                    Navigation.findNavController(view).navigate(action)
                    Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Show error messages for username or password issues
                    Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
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
            Toast.makeText(requireContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show()
        }
    }

}