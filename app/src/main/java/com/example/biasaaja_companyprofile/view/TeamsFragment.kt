package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.biasaaja_companyprofile.databinding.FragmentTeamsBinding
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Member
import com.example.biasaaja_companyprofile.model.Team

/**
 * A simple [Fragment] subclass.
 * Use the [TeamsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamsFragment : Fragment() {
    private lateinit var binding: FragmentTeamsBinding
    private lateinit var game:Game
    // Hardcoded teams and members data
    val games = listOf(
        Game(1, "Mobile Legends", "https://picsum.photos/id/237/200/300", "A multiplayer online battle arena (MOBA) game for mobile devices, where two teams battle to destroy each other's base."),
        Game(2, "Valorant", "https://example.com/images/valorant.jpg", "A tactical first-person shooter (FPS) game where teams of five compete in objective-based rounds."),
        Game(3, "Dota 2", "https://example.com/images/dota2.jpg", "A MOBA game where two teams of five players attempt to destroy the enemy's ancient."),
        Game(4, "Counter-Strike: Global Offensive", "https://example.com/images/csgo.jpg", "A competitive FPS game where teams of terrorists and counter-terrorists face off in various game modes."),
        Game(5, "League of Legends", "https://example.com/images/league_of_legends.jpg", "A popular MOBA game where teams battle to destroy the opposing team's Nexus.")
    )

    val teams = listOf(
        // Mobile Legends - 2 teams
        Team(
            id = 1,
            name = "Mobile Legends Warriors",
            game = games[0],
            members = arrayListOf(
                Member(id = 1, name = "Player1", imageUrl = "https://picsum.photos/id/237/200/300"),
                Member(id = 2, name = "Player2", imageUrl = "https://picsum.photos/id/237/200/300"),
                Member(id = 3, name = "Player3", imageUrl = "https://picsum.photos/id/237/200/300"),
            )
        ),
        Team(
            id = 2,
            name = "Mobile Legends Champions",
            game = games[0],
            members = arrayListOf(
            )
        ),

        // Valorant - 1 team
        Team(
            id = 3,
            name = "Valorant Sharpshooters",
            game = games[1],
            members = arrayListOf(

            )
        ),

        // Dota 2 - 3 teams
        Team(
            id = 4,
            name = "Dota 2 Titans",
            game = games[2],
            members = arrayListOf(

            )
        ),
        Team(
            id = 5,
            name = "Dota 2 Destroyers",
            game = games[2],
            members = arrayListOf(

            )
        ),
        Team(
            id = 6,
            name = "Dota 2 Legends",
            game = games[2],
            members = arrayListOf(

            )
        ),

        // CS:GO - 2 teams
        Team(
            id = 7,
            name = "CS:GO Elite",
            game = games[3],
            members = arrayListOf(

            )
        ),
        Team(
            id = 8,
            name = "CS:GO Snipers",
            game = games[3],
            members = arrayListOf(

            )
        ),

        // League of Legends - 1 team
        Team(
            id = 9,
            name = "LoL Champions",
            game = games[4],
            members = arrayListOf(

            )
        )
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
             game = TeamsFragmentArgs.fromBundle(requireArguments()).game
        }

    }

}