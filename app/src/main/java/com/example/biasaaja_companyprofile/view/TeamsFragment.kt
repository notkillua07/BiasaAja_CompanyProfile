package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biasaaja_companyprofile.databinding.FragmentTeamsBinding
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Member
import com.example.biasaaja_companyprofile.model.Team
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.stream.Collectors.toCollection


class TeamsFragment : Fragment() {
    private lateinit var binding: FragmentTeamsBinding
//    private lateinit var game: Game
//    private lateinit var adapter: TeamListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
//            game = TeamsFragmentArgs.fromBundle(requireArguments()).game
//            context?.let { ctx ->
//                val picasso = Picasso.Builder(ctx)
//                picasso.listener { _, _, exception ->
//                    exception.printStackTrace()
//                }
//                picasso.build().load(game.imageUrl).into(binding.gameImg)
//            }

//            val picasso = Picasso.Builder(view.context)
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//            picasso.build().load(game.imageUrl).into(binding.gameImg)
//            object: Callback {
//                override fun onSuccess() {
//                    binding.loadingBar.visibility = View.INVISIBLE
//                    binding.gameImg.visibility = View.VISIBLE
//
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//
//            }
//
//            Log.d("TeamsFragment", "Game ID: ${game.id}")
        }
//        val teamsList = TeamDataProvider.teams.filter { it.game!!.id == game.id }
//        val arrayList = ArrayList<Team>()
//        val teamsArrayList = teamsList.toCollection(arrayList)
//        Log.d("TeamsFragment", "Teams List: ${teamsArrayList.toString()}")
//        val teamListAdapter = TeamListAdapter(teamsArrayList)
//        binding.recsView.adapter = teamListAdapter
//        binding.recsView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }
}

