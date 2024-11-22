package com.example.biasaaja_companyprofile.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.app.viewmodel.UserViewModel
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.FragmentLoginBinding
import com.example.biasaaja_companyprofile.util.SessionManager

class LoginFragment : Fragment(), LoginClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sessionManager = SessionManager(requireContext())
        binding.listener = this

        if (sessionManager.isLoggedIn()) {
            // If logged in, navigate to HomeFragment directly
            val action = LoginFragmentDirections.actionLoginToWhatWePlayFragment()
            Navigation.findNavController(view).navigate(action)
        }

        // Observe login status from ViewModel
        viewModel.loginStatus.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                // Save user session to SharedPreferences using SessionManager
                val action = LoginFragmentDirections.actionLoginToWhatWePlayFragment()
                Navigation.findNavController(view).navigate(action)
            } else {
                // Show error message if login fails
            }
        }

        binding.btnRegister.setOnClickListener {
            val action =LoginFragmentDirections.actionLoginToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onLoginClick(v: View) {
        val username = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()
        viewModel.login(username, password)
    }
}