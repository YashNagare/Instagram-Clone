package com.practice.instagramclone.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.instagramclone.R
import com.practice.instagramclone.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}