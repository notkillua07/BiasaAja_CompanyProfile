package com.example.biasaaja_companyprofile.util

import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Member
import com.example.biasaaja_companyprofile.model.Team

object TeamDataProvider {
    val teams = arrayListOf(
        Team(
            id = 1,
            name = "Team A",
            game = Game(
                1,
                "Mobile Legends",
                "https://picsum.photos/id/237/200/300",
                "A multiplayer online battle arena (MOBA) game for mobile devices, where two teams battle to destroy each other's base."
            ),
            members = arrayListOf(
                Member(1, "Alice","Jungler", "https://picsum.photos/200/300"),
                Member(2, "Bob","Roamer", "https://picsum.photos/200/301")
            )
        ),
        Team(
            id = 2,
            name = "Team B",
            game = Game(
                1,
                "Mobile Legends",
                "https://picsum.photos/id/237/200/300",
                "A multiplayer online battle arena (MOBA) game for mobile devices, where two teams battle to destroy each other's base."
            ),
            members = arrayListOf(
                Member(3, "Charlie","Jungler", "https://picsum.photos/200/302"),
                Member(4, "Dave","Gold Laner", "https://picsum.photos/200/303"),
                Member(5, "Eve","Top Laner", "https://picsum.photos/200/304")
            )
        ),
        Team(
            id = 3,
            name = "Team A",
            game = Game(
                2,
                "Valorant",
                "https://example.com/images/valorant.jpg",
                "A tactical first-person shooter (FPS) game where teams of five compete in objective-based rounds."
            ),
            members = arrayListOf(
                Member(6, "Zack","Duelist","https://picsum.photos/200/305"),
                Member(7, "Tyson", "Controller", "https://picsum.photos/200/306"),
                Member(21, "Gustavo", "Initiator","https://picsum.photos/200/305"),
                Member(22, "Jordan", "Sentinel", "https://picsum.photos/200/306"),
                Member(23, "John","IGL", "https://picsum.photos/200/305")
            )
        ),
        Team(
            id = 4,
            name = "Team B",
            game = Game(
                2,
                "Valorant",
                "https://example.com/images/valorant.jpg",
                "A tactical first-person shooter (FPS) game where teams of five compete in objective-based rounds."
            ),
            members = arrayListOf(
                Member(8, "Charlie","Soloist", "https://picsum.photos/200/307")
            )
        ),
        Team(
            id = 5,
            name = "Team A",
            game = Game(
                3,
                "Dota 2",
                "https://example.com/images/dota2.jpg",
                "A MOBA game where two teams of five players attempt to destroy the enemy's ancient."
            ),
            members = arrayListOf(
                Member(9, "Alice","Gold Laner", "https://picsum.photos/200/308"),
                Member(10, "Bob", "Jungler", "https://picsum.photos/200/309"),
                Member(11, "Charlie","Top Laner", "https://picsum.photos/200/310")
            )
        ),
        Team(
            id = 6,
            name = "Team B",
            game = Game(
                3,
                "Dota 2",
                "https://example.com/images/dota2.jpg",
                "A MOBA game where two teams of five players attempt to destroy the enemy's ancient."
            ),
            members = arrayListOf(
                Member(12, "Dave","Jungler", "https://picsum.photos/200/311"),
                Member(13, "Eve", "Roamer","https://picsum.photos/200/312")
            )
        ),
        Team(
            id = 7,
            name = "Team A",
            game = Game(
                4,
                "Counter-Strike: Global Offensive",
                "https://example.com/images/csgo.jpg",
                "A competitive FPS game where teams of terrorists and counter-terrorists face off in various game modes."
            ),
            members = arrayListOf(
                Member(14, "Alice", "AWP-er","https://picsum.photos/200/313"),
                Member(15, "Bob", "Support","https://picsum.photos/200/314"),
                Member(16, "Charlie","Flank", "https://picsum.photos/200/315")
            )
        ),
        Team(
            id = 8,
            name = "Team A",
            game = Game(
                5,
                "League of Legends",
                "https://example.com/images/league_of_legends.jpg",
                "A popular MOBA game where teams battle to destroy the opposing team's Nexus."
            ),
            members = arrayListOf(
                Member(17, "Dave", "Jungler","https://picsum.photos/200/316"),
                Member(18, "Eve", "Roamer","https://picsum.photos/200/317")
            )
        ),
        Team(
            id = 9,
            name = "Team B",
            game = Game(
                5,
                "League of Legends",
                "https://example.com/images/league_of_legends.jpg",
                "A popular MOBA game where teams battle to destroy the opposing team's Nexus."
            ),
            members = arrayListOf(
                Member(19, "Alice", "Jungler","https://picsum.photos/200/318"),
                Member(20, "Bob", "Roamer","https://picsum.photos/200/319")
            )
        )
    )
}