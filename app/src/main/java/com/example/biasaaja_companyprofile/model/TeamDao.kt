package com.example.biasaaja_companyprofile.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg team: Team)

    @Query("SELECT * FROM teams")
    fun selectAllTeam(): List<Team>

    @Query("SELECT * FROM teams WHERE game_id=:game_id")
    fun selectTeam(game_id: String): Team

    @Delete
    fun deleteTeam(team: Team)

    @Update
    fun update(team: Team)
}