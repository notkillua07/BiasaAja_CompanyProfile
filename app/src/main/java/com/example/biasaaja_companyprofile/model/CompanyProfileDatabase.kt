package com.example.biasaaja_companyprofile.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.studentproject.util.DB_NAME

@Database(entities = [User::class, Apply::class], version = 2)
abstract class CompanyProfileDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun applyDao(): ApplyDao

    companion object {
        @Volatile
        private var INSTANCE: CompanyProfileDatabase? = null

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CompanyProfileDatabase::class.java,
                DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .build()

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the "applies" table (no need to worry about Team here)
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `applies` (
                        `username` TEXT NOT NULL,
                        `team` INTEGER NOT NULL,
                        `reason` TEXT NOT NULL,
                        `status` TEXT NOT NULL,
                        PRIMARY KEY(`username`, `team`)
                    )
                    """
                )
            }
        }
    }
}
