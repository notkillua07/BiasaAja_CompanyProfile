package com.example.biasaaja_companyprofile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val role:String?,
//    val bod:String?,
//    val phone:String?,
    val imageUrl:String?
):Serializable

@Entity(tableName = "users")
data class User(
    var firstname: String,
    var lastname: String,
    @PrimaryKey var username: String,
    var password: String
):Serializable

@Entity(tableName = "applies", primaryKeys = ["username", "team"])
data class Apply(
    val username: String,
    val team: Int,
    var reason: String,
    var status: String
): Serializable

