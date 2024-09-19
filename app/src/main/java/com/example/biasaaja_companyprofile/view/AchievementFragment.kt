package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.biasaaja_companyprofile.databinding.FragmentAchievementBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AchievementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AchievementFragment : Fragment() {

    private lateinit var binding: FragmentAchievementBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

}