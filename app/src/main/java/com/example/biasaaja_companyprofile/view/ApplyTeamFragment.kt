package com.example.biasaaja_companyprofile.view

import android.os.Bundle
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

class ApplyTeamFragment : Fragment() {

    private lateinit var binding: FragmentApplyTeamBinding
    private lateinit var viewModel: ApplyTeamViewModel
    private val applyListAdapter = ApplyListAdapter(arrayListOf()) { apply ->
        navigateToApplyTeamNewFragment(apply)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)


        // Set up RecyclerView
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = applyListAdapter

        // Observe ViewModel
        observeViewModel()

        // Button to navigate
        binding.btnAdd.setOnClickListener {
            val action = ApplyTeamFragmentDirections.actionApplyTeamToApplyTeamNewFragment("")
            Navigation.findNavController(it).navigate(action)
        }

        // Swipe Refresh
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
        // Observe the list of apply data
        viewModel.applyLD.observe(viewLifecycleOwner, Observer { applyList ->
            applyListAdapter.updateApplyList(applyList)
            if (applyList.isEmpty()) {
                binding.recView.visibility = View.GONE
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "No applications available"
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.txtError.visibility = View.GONE
            }
        })

        // Observe loading state to manage progress bar visibility
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        // Observe error state to show/hide error text
        viewModel.applyLoadErrorLD.observe(viewLifecycleOwner, Observer { hasError ->
            binding.txtError.visibility = if (hasError) View.VISIBLE else View.GONE
        })
    }

    private fun navigateToApplyTeamNewFragment(apply: Apply) {
        val action = ApplyTeamFragmentDirections.actionApplyTeamToApplyTeamNewFragment(apply.username)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }
}
