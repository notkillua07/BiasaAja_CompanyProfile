package com.example.biasaaja_companyprofile.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg schedule: Schedule)

    @Query("SELECT * FROM schedules")
    fun selectAllSchedule(): List<Schedule>

    @Query("SELECT * FROM schedules WHERE game_name=:game_name and team_name=:team_name")
    fun selectSchedule(game_name: String, team_name:String): Schedule

    @Delete
    fun deleteSchedule(schedule: Schedule)

    @Update
    fun update(schedule: Schedule)
}