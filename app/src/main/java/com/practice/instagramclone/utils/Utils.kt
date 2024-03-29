package com.practice.instagramclone.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit) {
    var imageURL: String? = null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageURL = it.toString()
                callback(imageURL)
            }
        }
}

fun uploadVideo(uri: Uri, folderName: String, progressDialog: ProgressDialog, callback: (String?) -> Unit) {
    var imageURL: String? = null
    progressDialog.setTitle("Uploading...")
    progressDialog.show()
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageURL = it.toString()
                progressDialog.dismiss()
                callback(imageURL)
            }
        }
        .addOnProgressListener {
            val uploadedValue: Long = it.bytesTransferred/it.totalByteCount
            progressDialog.setMessage("Uploaded $uploadedValue %")
        }
}