package com.example.aplikasigithubuser.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.data.local.room.FavoriteDao
import com.example.aplikasigithubuser.data.local.room.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {

    private val favoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getInstance(application)
        favoriteDao = db.favoriteDao()
    }

    fun findALl(): LiveData<List<Favorite>> {
        return favoriteDao.findAll()
    }

    fun findById(username: String?): LiveData<List<Favorite>>  {
        return favoriteDao.findById(username)
    }

    fun create(favorite: Favorite) {
        executorService.execute {
            favoriteDao.create(favorite)
        }
    }

    fun delete(id: Long?) {
        executorService.execute { favoriteDao.delete(id) }
    }
}