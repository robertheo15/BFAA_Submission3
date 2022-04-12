package com.example.aplikasigithubuser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.databinding.ItemFollowBinding
import com.example.aplikasigithubuser.model.User

class FollowingAdapter(private val listUserFollowing: List<User>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding =
            ItemFollowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val user = listUserFollowing[position]

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_block)
            .into(holder.binding.userFollow)
        holder.binding.tvFollowUsername.text = user.username
    }

    override fun getItemCount() = listUserFollowing.size

    class FollowingViewHolder(var binding: ItemFollowBinding) :
        RecyclerView.ViewHolder(binding.root)
}