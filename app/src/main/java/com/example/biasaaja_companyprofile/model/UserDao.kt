package com.example.biasaaja_companyprofile.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user:User)

    @Query("SELECT * FROM users")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM users WHERE username= :username")
    fun selectUser(username:String): User

    @Delete
    fun deleteTodo(user:User)

    @Update
    fun update(user: User)
}