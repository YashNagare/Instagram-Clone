package com.practice.instagramclone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.practice.instagramclone.adapter.SearchAdapter
import com.practice.instagramclone.databinding.FragmentSearchBinding
import com.practice.instagramclone.models.User
import com.practice.instagramclone.utils.USER_NODE

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    lateinit var adapter: SearchAdapter
    var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchAdapter(requireContext(), userList)
        binding.rv.adapter = adapter

        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var tempList = ArrayList<User>()
            userList.clear()
            for (i in it.documents) {
                if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())) {

                } else {
                    var user: User = i.toObject<User>()!!
                    tempList.add(user)
                }
            }
            userList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

        binding.searchButton.setOnClickListener {
            var text = binding.searchView.text.toString()
            Firebase.firestore.collection(USER_NODE).whereEqualTo("name", text).get()
                .addOnSuccessListener {
                    var tempList = ArrayList<User>()
                    userList.clear()
                    if (it.isEmpty) {

                    } else {
                        for (i in it.documents) {
                            if (i.id.toString()
                                    .equals(Firebase.auth.currentUser!!.uid.toString())
                            ) {

                            } else {
                                var user: User = i.toObject<User>()!!
                                tempList.add(user)
                            }
                        }
                        userList.addAll(tempList)
                        adapter.notifyDataSetChanged()
                    }
                }
        }
        return binding.root

    }

    companion object {
    }
}