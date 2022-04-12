package com.example.aplikasigithubuser.data.api

import com.example.aplikasigithubuser.BuildConfig
import com.example.aplikasigithubuser.model.User
import com.example.aplikasigithubuser.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token $mySuperSecretKey", "UserResponse-Agent: request")
    fun getUserBySearch(@Query("q") username: String): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $mySuperSecretKey", "UserResponse-Agent: request")
    fun getDetailUser(@Path("username") username: String): Call<User>

//    @GET("users/{username}")
//    @Headers("Authorization: token $mySuperSecretKey", "UserResponse-Agent: request")
//    fun getFavoriteUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $mySuperSecretKey", "UserResponse-Agent: request")
    fun getListFollowers(@Path("username") username: String): Call<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $mySuperSecretKey", "UserResponse-Agent: request")
    fun getListFollowing(@Path("username") username: String): Call<List<User>>

    companion object{
        private const val mySuperSecretKey = BuildConfig.KEY
    }
}