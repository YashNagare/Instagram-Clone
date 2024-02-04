package com.practice.instagramclone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.practice.instagramclone.databinding.ActivitySignUpBinding
import com.practice.instagramclone.models.User
import com.practice.instagramclone.utils.USER_NODE
import com.practice.instagramclone.utils.USER_PROFILE_FOLDER
import com.practice.instagramclone.utils.uploadImage

class SignUpActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    lateinit var user: User
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) {
                if (it == null) {

                } else {
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        user = User()
        binding.signUpBtn.setOnClickListener {
            if (binding.name.editText?.text.toString().equals("") or
                binding.email.editText?.text.toString().equals("") or
                binding.password.editText?.text.toString().equals("")
            ) {
                Toast.makeText(this, "Please fill all information", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.email.editText?.text.toString(),
                    binding.password.editText?.text.toString()
                ).addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        user.name = binding.name.editText?.text.toString()
                        user.password = binding.password.editText?.text.toString()
                        user.email = binding.email.editText?.text.toString()
                        Firebase.firestore.collection(USER_NODE)
                            .document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                startActivity(Intent(this, HomeActivity::class.java))
                                finish()
                            }
                    } else {
                        Toast.makeText(this, result.exception?.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        binding.addImage.setOnClickListener {
            launcher.launch("image/*")
        }

    }
}