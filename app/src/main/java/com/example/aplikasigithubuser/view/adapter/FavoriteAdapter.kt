package com.example.aplikasigithubuser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.databinding.ItemUserBinding
import com.example.aplikasigithubuser.model.User

class FavoriteAdapter(private val listFavorites: List<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = listFavorites[position]
        val user = User(
            favorite.username,
            favorite.id,
            favorite.name,
            favorite.followers,
            favorite.following,
            favorite.followersUrl,
            favorite.followingUrl,
            favorite.repository,
            favorite.location,
            favorite.company,
            favorite.avatarUrl
        )

        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(favorite.avatarUrl)
                .placeholder(R.drawable.ic_block)
                .into(imgItemPhoto)

            tvItemUsername.text = favorite.username
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
        }
    }

    override fun getItemCount() = listFavorites.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class FavoriteViewHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}