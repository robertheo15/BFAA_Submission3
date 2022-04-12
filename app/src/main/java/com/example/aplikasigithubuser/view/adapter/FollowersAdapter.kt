package com.example.aplikasigithubuser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.databinding.ItemFollowBinding
import com.example.aplikasigithubuser.model.User

class FollowersAdapter(private val listUserFollowers: List<User>) :
    RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding =
            ItemFollowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val user = listUserFollowers[position]

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_block)
            .into(holder.binding.userFollow)
        holder.binding.tvFollowUsername.text = user.username
    }

    override fun getItemCount() = listUserFollowers.size

    class FollowersViewHolder(var binding: ItemFollowBinding) :
        RecyclerView.ViewHolder(binding.root)
}