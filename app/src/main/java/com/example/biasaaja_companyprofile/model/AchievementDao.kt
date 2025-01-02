package com.example.biasaaja_companyprofile.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AchievementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg achievement: Achievement)

    @Query("SELECT * FROM achievements")
    fun selectAllUser(): List<Achievement>

    @Query("SELECT * FROM achievements WHERE name=:name")
    fun selectGameName(name:String): Achievement

    @Delete
    fun deleteAchievement(achievement: Achievement)

    @Update
    fun update(achievement: Achievement)
}