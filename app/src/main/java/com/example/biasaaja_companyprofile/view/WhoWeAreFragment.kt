package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.FragmentWhoWeAreBinding
import com.squareup.picasso.Picasso


class WhoWeAreFragment : Fragment() {
    private lateinit var binding: FragmentWhoWeAreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhoWeAreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBanner.setImageResource(R.drawable.pic)
        binding.imgBanner.visibility = View.VISIBLE
        binding.btnLike.setOnClickListener {
            val currentLikes = binding.btnLike.text.toString().toInt()
            binding.btnLike.text = (currentLikes + 1).toString()
        }
    }

}