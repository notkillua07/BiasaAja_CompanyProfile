package com.example.biasaaja_companyprofile.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Game(
    val id:Int?,
    val name:String?,
    val imageUrl:String?,
    val description:String?
):Serializable

data class Achievement(
    val id:Int?
):Serializable

data class Schedule(
    val id:Int?
):Serializable