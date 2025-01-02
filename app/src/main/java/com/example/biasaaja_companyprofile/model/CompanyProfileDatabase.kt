package com.example.biasaaja_companyprofile.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.studentproject.util.DB_NAME

@Database(entities = arrayOf(User::class, Apply::class, Team::class, Schedule::class, Achievement::class, Member::class, Game::class), version = 3)
abstract class CompanyProfileDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun applyDao(): ApplyDao
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile private var instance: CompanyProfileDatabase ?= null //@Volatile agar instance bisa diakses dari thread lain
        private val LOCK = Any() //agar tidak ada 2 thread yang mengakses database yang sama

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CompanyProfileDatabase::class.java,
                DB_NAME)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

        operator fun invoke(context: Context)
        {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the "applies" table (no need to worry about Team here)
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS `applies` ( `username` TEXT NOT NULL, `team` INTEGER NOT NULL, `reason` TEXT NOT NULL, `status` TEXT NOT NULL, PRIMARY KEY(`username`, `team`))"
        )
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
         //Create the new tables
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS games ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, imageUrl TEXT, description TEXT)"
        )
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS achievements (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name TEXT, game_name TEXT,team_name TEXT, year INTEGER)"
        )
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS schedules ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, imageUrl TEXT, date TEXT, month TEXT, time TEXT, location TEXT, description TEXT,game_name TEXT, team_name TEXT)"
        )
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS teams ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, game_id INTEGER)"
        )
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS members ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, team_id INTEGER, role TEXT, imageUrl TEXT)"
        )
    }
}
