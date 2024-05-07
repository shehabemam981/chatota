package com.example.chatapp.myDateBase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore

object MyDataBase {
    fun setDatainFireBase(user:Users,onComplete: OnCompleteListener<Void>){
    Firebase.firestore.collection("users").document(user.uid?:"").set(user).addOnCompleteListener(onComplete)
    }
    fun getDataFromFireBase (uid: String,onComplete:OnCompleteListener<DocumentSnapshot>){
        Firebase.firestore.collection("users").document(uid).get().addOnCompleteListener(onComplete)
    }
}