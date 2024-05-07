package com.example.chatapp.ui.home.allRooms

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.R
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllRoomsVIewModel:BaseViewModel() {
    val roomsLiveData  =MutableLiveData<List<Room>>()
    val  event = MutableLiveData<AllRoomEvent>()

    fun getAllRooms(){
        Firebase.firestore.collection(constants.room).get().addOnCompleteListener {task->
            if(task.isSuccessful){
                val rooms =   task.result.toObjects(Room::class.java)

                  roomsLiveData.value = rooms


            }else{
                viewMessage.value = task.exception?.localizedMessage
            }
        }
    }
    fun CheckUserInRoom(room:Room):Boolean{
        val user = Firebase.auth.currentUser?:null
        if(room.ownerListId!!.contains(user!!.uid)){
               event.value = AllRoomEvent.navigateToChat(room)
            return true
        }else{
            event.value = AllRoomEvent.navigateToJoinRoom(room)
        return false
        }
    }

}
