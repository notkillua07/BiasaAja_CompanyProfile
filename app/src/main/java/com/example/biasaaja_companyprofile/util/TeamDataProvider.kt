package com.example.biasaaja_companyprofile.util

import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Member
import com.example.biasaaja_companyprofile.model.Member2
import com.example.biasaaja_companyprofile.model.Team
import com.example.biasaaja_companyprofile.model.Team2

object TeamDataProvider {
    val teams = arrayListOf(
        Team2(
            id = 1,
            name = "Team ML A",
            game = Game(
                1,
                "Mobile Legends",
                "https://assets-prd.ignimgs.com/2023/09/30/mobilelegends-1696089976653.jpg",
                "A multiplayer online battle arena (MOBA) game for mobile devices, where two teams battle to destroy each other's base."
            ),
            members = arrayListOf(
                Member2(1, "Alice","Jungler", "https://avatar.iran.liara.run/public/girl"),
                Member2(2, "Bob","Roamer", "https://avatar.iran.liara.run/public/boy")
            )
        ),
        Team2(
            id = 2,
            name = "Team ML B",
            game = Game(
                1,
                "Mobile Legends",
                "https://assets-prd.ignimgs.com/2023/09/30/mobilelegends-1696089976653.jpg",
                "A multiplayer online battle arena (MOBA) game for mobile devices, where two teams battle to destroy each other's base."
            ),
            members = arrayListOf(
                Member2(3, "Charlie","Jungler", "https://avatar.iran.liara.run/public/boy"),
                Member2(4, "Dave","Gold Laner", "https://avatar.iran.liara.run/public/boy"),
                Member2(5, "Eve","Top Laner", "https://avatar.iran.liara.run/public/girl")
            )
        ),
        Team2(
            id = 3,
            name = "Team V A",
            game = Game(
                2,
                "Valorant",
                "https://www.riotgames.com/darkroom/1440/d0807e131a84f2e42c7a303bda672789:3d02afa7e0bfb75f645d97467765b24c/valorant-offwhitelaunch-keyart.jpg",
                "A tactical first-person shooter (FPS) game where teams of five compete in objective-based rounds."
            ),
            members = arrayListOf(
                Member2(6, "Zachary","Duelist","https://cloud.papercrowns.com/image/upload/c_fill,g_face,w_768,h_768,q_auto/v1/sentinels/member/zekken.jpg?v=65af19b0cdc94"),
                Member2(7, "Tyson", "Controller", "https://cloud.papercrowns.com/image/upload/c_fill,g_face,w_768,h_768,q_auto/v1/sentinels/member/tenz.jpg?v=65af19ceef35d"),
                Member2(21, "Gustavo", "Initiator","https://cloud.papercrowns.com/image/upload/c_fill,g_face,w_768,h_768,q_auto/v1/sentinels/member/sacy.jpg"),
                Member2(22, "Jordan", "Sentinel", "https://cloud.papercrowns.com/image/upload/c_fill,g_face,w_768,h_768,q_auto/v1/sentinels/member/zellsis.jpg?v=65af18c2aa8e6"),
                Member2(23, "John","IGL", "https://cloud.papercrowns.com/image/upload/c_fill,g_face,w_768,h_768,q_auto/v1/sentinels/member/johnqt.jpg?v=65af1970ad174")
            )
        ),
        Team2(
            id = 4,
            name = "Team V B",
            game = Game(
                2,
                "Valorant",
                "https://www.riotgames.com/darkroom/1440/d0807e131a84f2e42c7a303bda672789:3d02afa7e0bfb75f645d97467765b24c/valorant-offwhitelaunch-keyart.jpg",
                "A tactical first-person shooter (FPS) game where teams of five compete in objective-based rounds."
            ),
            members = arrayListOf(
                Member2(8, "Charlie","Soloist", "https://avatar.iran.liara.run/public/boy")
            )
        ),
        Team2(
            id = 5,
            name = "Team A",
            game = Game(
                3,
                "Dota 2",
                "https://cdn.akamai.steamstatic.com/apps/dota2/images/dota2_social.jpg",
                "A MOBA game where two teams of five players attempt to destroy the enemy's ancient."
            ),
            members = arrayListOf(
                Member2(9, "Alice","Gold Laner", "https://avatar.iran.liara.run/public/girl"),
                Member2(10, "Bob", "Jungler", "https://avatar.iran.liara.run/public/boy"),
                Member2(11, "Charlie","Top Laner", "https://avatar.iran.liara.run/public/boy")
            )
        ),
        Team2(
            id = 6,
            name = "Team B",
            game = Game(
                3,
                "Dota 2",
                "https://cdn.akamai.steamstatic.com/apps/dota2/images/dota2_social.jpg",
                "A MOBA game where two teams of five players attempt to destroy the enemy's ancient."
            ),
            members = arrayListOf(
                Member2(12, "Dave","Jungler", "https://avatar.iran.liara.run/public/boy"),
                Member2(13, "Eve", "Roamer","https://avatar.iran.liara.run/public/girl")
            )
        ),
        Team2(
            id = 7,
            name = "Team A",
            game = Game(
                4,
                "Counter-Strike: Global Offensive",
                "https://cdn.akamai.steamstatic.com/steam/apps/730/header.jpg",
                "A competitive FPS game where teams of terrorists and counter-terrorists face off in various game modes."
            ),
            members = arrayListOf(
                Member2(14, "Alice", "AWP-er","https://avatar.iran.liara.run/public/girl"),
                Member2(15, "Bob", "Support","https://avatar.iran.liara.run/public/boy"),
                Member2(16, "Charlie","Flank", "https://avatar.iran.liara.run/public/boy")
            )
        ),
        Team2(
            id = 8,
            name = "Team A",
            game = Game(
                5,
                "League of Legends",
                "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ahri_0.jpg",
                "A popular MOBA game where teams battle to destroy the opposing team's Nexus."
            ),
            members = arrayListOf(
                Member2(17, "Dave", "Jungler","https://avatar.iran.liara.run/public/boy"),
                Member2(18, "Eve", "Roamer","https://avatar.iran.liara.run/public/girl")
            )
        ),
        Team2(
            id = 9,
            name = "Team B",
            game = Game(
                5,
                "League of Legends",
                "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ahri_0.jpg",
                "A popular MOBA game where teams battle to destroy the opposing team's Nexus."
            ),
            members = arrayListOf(
                Member2(19, "Alice", "Jungler","https://avatar.iran.liara.run/public/girl"),
                Member2(20, "Bob", "Roamer","https://avatar.iran.liara.run/public/boy")
            )
        )
    )
}