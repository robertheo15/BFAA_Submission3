package com.example.aplikasigithubuser.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.FavoriteRepository
import com.example.aplikasigithubuser.data.local.entity.Favorite

class FavoriteViewModel(application: Application) : ViewModel() {

    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun findAll() = favoriteRepository.findALl()

    fun create(favorite: Favorite) {
        favoriteRepository.create(favorite)
    }

    fun findById(username: String?) {
        favoriteRepository.findById(username)
    }

    fun delete(id: Long?) {
        favoriteRepository.delete(id)
    }
}