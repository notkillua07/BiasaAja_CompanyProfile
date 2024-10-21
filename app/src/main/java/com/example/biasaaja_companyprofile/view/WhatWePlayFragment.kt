package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biasaaja_companyprofile.databinding.FragmentWhatWePlayBinding
import com.example.biasaaja_companyprofile.viewmodel.GameViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [WhatWePlayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WhatWePlayFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private val gamesListAdapter = GamesListAdapter(arrayListOf())
    private lateinit var binding: FragmentWhatWePlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatWePlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = gamesListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            gamesListAdapter.updateStudentList(it)
        })

        viewModel.gamesLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

    }

}