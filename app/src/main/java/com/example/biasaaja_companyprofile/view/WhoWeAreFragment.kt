package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.biasaaja_companyprofile.databinding.FragmentWhoWeAreBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WhoWeAreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WhoWeAreFragment : Fragment() {
    private lateinit var binding: FragmentWhoWeAreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhoWeAreBinding.inflate(inflater, container, false)
        return binding.root
    }

}