package com.example.biasaaja_companyprofile.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg game: Game)

    @Query("SELECT * FROM games")
    fun selectAllGame(): List<Game>

    @Query("SELECT * FROM games WHERE name=:game_name")
    fun selectGameName(game_name:String): Game

    @Query("SELECT * FROM games WHERE id=:game_id")
    fun selectGameId(game_id:String): Game

    @Delete
    fun deleteGame(game: Game)

    @Update
    fun update(game: Game)
}
