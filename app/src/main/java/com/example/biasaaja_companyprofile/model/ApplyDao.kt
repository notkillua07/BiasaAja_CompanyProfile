package com.example.biasaaja_companyprofile.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ApplyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg applies: Apply)

    @Query("SELECT * FROM applies")
    fun selectAllApply(): List<Apply>

    @Query("SELECT * FROM applies WHERE username = :username AND team = :team")
    fun selectApply(username: String, team: Int): Apply

    @Update
    fun update(apply: Apply)
}
