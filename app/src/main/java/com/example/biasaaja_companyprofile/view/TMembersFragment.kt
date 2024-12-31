package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.FragmentTMembersBinding
import com.example.biasaaja_companyprofile.databinding.FragmentTeamsBinding
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Team
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class TMembersFragment : Fragment() {
    private lateinit var binding: FragmentTMembersBinding
//    private lateinit var team: Team
    private lateinit var adapter: MemberListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTMembersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
//            team = TMembersFragmentArgs.fromBundle(requireArguments()).team
//            context?.let { ctx ->
//                val picasso = Picasso.Builder(ctx)
//                picasso.listener { _, _, exception ->
//                    exception.printStackTrace()
//                }
//                picasso.build().load(team.game!!.imageUrl).into(binding.imgGame)
//            }
//
//            val picasso = Picasso.Builder(view.context)
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//            picasso.build().load(team.game!!.imageUrl).into(binding.imgGame)
//            object : Callback {
//                override fun onSuccess() {
//                    binding.progressBar3.visibility = View.INVISIBLE
//                    binding.imgGame.visibility = View.VISIBLE
//
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//            }
//            binding.txtName.text = team.name
//            val memberList = team.members
//            val memberListAdapter = MemberListAdapter(memberList!!)
//            binding.recView.adapter = memberListAdapter
//            binding.recView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}