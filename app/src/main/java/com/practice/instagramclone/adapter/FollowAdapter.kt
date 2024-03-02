package com.practice.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.instagramclone.R
import com.practice.instagramclone.databinding.FollowRvBinding
import com.practice.instagramclone.models.User

class FollowAdapter(var context: Context, var followList: ArrayList<User>) :
    RecyclerView.Adapter<FollowAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: FollowRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowAdapter.ViewHolder {
        var binding = FollowRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(followList.get(position).image).placeholder(R.drawable.user)
            .into(holder.binding.profileImage)
        holder.binding.name.text = followList.get(position).name
    }

    override fun getItemCount(): Int {
        return followList.size
    }
}