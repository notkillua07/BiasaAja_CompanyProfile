package com.example.biasaaja_companyprofile.model

import java.io.Serializable

data class Game(
    val id:Int?,
    val name:String?,
    val imageUrl:String?,
    val description:String?
):Serializable

data class Achievement(
    val id:Int?,
    val name:String?,
    val game_name:String?,
    val team_name:String?,
    val year:Int?
):Serializable

data class Schedule(
    val id:Int?,
    val name:String?,
    val imageUrl:String?,
    val date:String?,
    val month:String?,
    val time:String?,
    val location:String?,
    val description:String?,
    val game_name:String?,
    val team_name:String?,
):Serializable

data class Team(
    val id:Int?,
    val name:String?,
    val game:Game?,
    val members:ArrayList<Member>?
):Serializable

data class Member(
    val id:Int?,
    val name:String?,
//    val bod:String?,
//    val phone:String?,
    val imageUrl:String?
):Serializable