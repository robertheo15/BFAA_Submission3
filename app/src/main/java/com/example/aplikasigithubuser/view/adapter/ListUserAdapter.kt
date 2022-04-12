package com.example.aplikasigithubuser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.databinding.ItemUserBinding
import com.example.aplikasigithubuser.model.User

class ListUserAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = users[position]

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_block)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemUsername.text = user.username
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    override fun getItemCount(): Int = users.size

    class ListViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}