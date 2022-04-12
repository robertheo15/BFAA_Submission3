package com.example.aplikasigithubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aplikasigithubuser.data.local.entity.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun create(user: Favorite)

    @Query("SELECT * FROM favorites ORDER BY id")
    fun findAll(): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE favorites.username = :username ")
    fun findById(username: String?): LiveData<List<Favorite>>

    @Query("DELETE FROM favorites WHERE favorites.id = :id")
    fun delete(id: Long?)
}