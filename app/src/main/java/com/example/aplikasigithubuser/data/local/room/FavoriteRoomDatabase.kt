package com.example.aplikasigithubuser.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aplikasigithubuser.data.local.entity.Favorite
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: FavoriteRoomDatabase? = null

        fun getInstance(context: Context): FavoriteRoomDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRoomDatabase::class.java, "favorites.db"
                ).build()
            }
    }
}