package com.example.aplikasigithubuser.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.data.FavoriteRepository
import com.example.aplikasigithubuser.data.api.ApiConfig
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getDetailUser(username: String) {

        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)

        client.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _user.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun create(favorite: Favorite) {
        return favoriteRepository.create(favorite)
    }

    fun delete(id: Long?) {
        return favoriteRepository.delete(id)
    }

    fun findById(username: String?) : LiveData<List<Favorite>>  {
        return favoriteRepository.findById(username)
    }


    companion object {
        private const val TAG = "DetailUserViewModel"
    }
}