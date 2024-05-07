package com.example.chatapp.ui.home.myRooms

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyRoomsViewModel:BaseViewModel() {
  val myRoomsLiveData = MutableLiveData<List<Room>>()
    fun getMyRooms(){
        val user =  Firebase.auth.currentUser?:return
        Firebase.firestore.collection(constants.room).whereArrayContains(
            Room.ownerListIdField,user.uid).get().addOnCompleteListener {
                task->
                if(task.isSuccessful){
                  myRoomsLiveData.value = task.result.toObjects(Room::class.java)
                }else{
                 viewMessage.value = task.exception?.localizedMessage
                }
        }
    }


}
