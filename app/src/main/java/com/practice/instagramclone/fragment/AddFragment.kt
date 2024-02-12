package com.practice.instagramclone.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.practice.instagramclone.R
import com.practice.instagramclone.databinding.FragmentAddBinding
import com.practice.instagramclone.post.PostActivity
import com.practice.instagramclone.post.ReelsActivity

class AddFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener {
            activity?.startActivity(Intent(requireContext(), PostActivity::class.java))
        }

        binding.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(), ReelsActivity::class.java))
        }

        return binding.root
    }

    companion object {

    }
}