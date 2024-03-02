package com.practice.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.instagramclone.R
import com.practice.instagramclone.databinding.ReelDgBinding
import com.practice.instagramclone.models.Reel
import com.squareup.picasso.Picasso

class ReelAdapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<ReelAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ReelDgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelAdapter.ViewHolder {
        var binding = ReelDgBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReelAdapter.ViewHolder, position: Int) {
        Picasso.get().load(reelList.get(position).profileLink).placeholder(R.drawable.user)
            .into(holder.binding.profileImage)
        holder.binding.caption.setText(reelList.get(position).caption)
        holder.binding.videoView.setVideoPath(reelList.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener {
            holder.binding.progressBar.visibility = View.GONE
            holder.binding.videoView.start()
        }
    }

    override fun getItemCount(): Int {
        return reelList.size
    }
}