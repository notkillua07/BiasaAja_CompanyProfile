package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biasaaja_companyprofile.databinding.FragmentApplyTeamBinding
import com.example.biasaaja_companyprofile.viewmodel.ApplyTeamViewModel
import com.example.biasaaja_companyprofile.model.Apply
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.viewmodel.GameViewModel
import com.example.biasaaja_companyprofile.viewmodel.TeamViewModel

class ApplyTeamFragment : Fragment() {

    private lateinit var binding: FragmentApplyTeamBinding
    private lateinit var viewModel: ApplyTeamViewModel
    private lateinit var teamViewModel: TeamViewModel
    private val applyListAdapter by lazy { ApplyListAdapter(arrayListOf(), teamViewModel) }
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            username = ApplyTeamFragmentArgs.fromBundle(it).username
        }

        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)
        viewModel.refresh(username ?: "")

        teamViewModel = ViewModelProvider(this).get(TeamViewModel::class.java)


        // Set up RecyclerView
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = applyListAdapter

        // Observe ViewModel
        observeViewModel()

        // Button to navigate
        binding.btnAdd.setOnClickListener {
            val action = ApplyTeamFragmentDirections.actionApplyTeamToApplyTeamNewFragment(username ?: "")
            Navigation.findNavController(it).navigate(action)
        }

        // Swipe Refresh
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh(username ?: "")
        }
    }

    private fun observeViewModel() {
        viewModel.applyLD.observe(viewLifecycleOwner, Observer {
            applyListAdapter.updateApplyList(it)
            binding.refreshLayout.isRefreshing = false  // Stop the refresh indicator
            if (it.isEmpty()) {
                binding.recView.visibility = View.GONE
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "No applications available"
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.applyLoadErrorLD.observe(viewLifecycleOwner, Observer { hasError ->
            binding.txtError.visibility = if (hasError) View.VISIBLE else View.GONE
        })
    }


}
