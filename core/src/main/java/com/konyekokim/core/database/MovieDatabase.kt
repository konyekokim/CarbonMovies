package com.konyekokim.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.konyekokim.core.BuildConfig
import com.konyekokim.core.data.entities.Movie

@Database(
    entities = [Movie::class],
    exportSchema = false,
    version = BuildConfig.MOVIE_DATABASE_VERSION
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}