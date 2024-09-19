package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.biasaaja_companyprofile.databinding.FragmentSchDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SchDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SchDetailFragment : Fragment() {
    private lateinit var binding: FragmentSchDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}