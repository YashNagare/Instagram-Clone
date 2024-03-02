package com.practice.instagramclone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.practice.instagramclone.adapter.ReelAdapter
import com.practice.instagramclone.databinding.FragmentReelBinding
import com.practice.instagramclone.models.Reel
import com.practice.instagramclone.utils.REEL

class ReelFragment : Fragment() {

    private lateinit var binding: FragmentReelBinding
    lateinit var adater: ReelAdapter
    var reelList = ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReelBinding.inflate(inflater, container, false)
        adater = ReelAdapter(requireContext(), reelList)
        binding.viewPager.adapter = adater
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var tempList = ArrayList<Reel>()
            reelList.clear()
            for (i in it.documents) {
                var reel = i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            reelList.reversed()
            adater.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}