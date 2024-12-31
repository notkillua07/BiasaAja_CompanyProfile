package com.example.biasaaja_companyprofile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "games")
data class Game(
    val id:Int = 0,
    var name:String?,
    var imageUrl:String?,
    var description:String?
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity(tableName = "achievements")
data class Achievement(
    val id:Int = 0,
    var name:String?,
    var game_name:String?,
    var team_name:String?,
    var year:Int?
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity(tableName = "schedules")
data class Schedule(
    val id:Int = 0,
    var name:String?,
    var imageUrl:String?,
    var date:String?,
    var month:String?,
    var time:String?,
    var location:String?,
    var description:String?,
    var game_name:String?,
    var team_name:String?,
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity(tableName = "teams")
data class Team(
    val id:Int = 0,
    var name:String?,
    var game_id:Int?
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity(tableName = "members")
data class Member(
    val id:Int = 0,
    var name:String?,
    var team_id:Int?,
    var role:String?,
    var imageUrl:String?
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity(tableName = "users")
data class User(
    var firstname: String,
    var lastname: String,
    @PrimaryKey var username: String,
    var password: String
):Serializable

@Entity(tableName = "applies", primaryKeys = ["username", "team"])
data class Apply(
    var username: String,
    var team: Int,
    var reason: String,
    var status: String
): Serializable