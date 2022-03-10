package com.codigo.mobilecodetest.codivie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codigo.mobilecodetest.codivie.data.db.dao.MovieDao
import com.codigo.mobilecodetest.codivie.data.db.entities.Movie

const val DATABASE_NAME = "codivie_db"

@Database(entities = [Movie::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        // Create the database
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()

    }
}