package com.example.aplikasigithubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("items")
	val users: List<User>
) : Parcelable

