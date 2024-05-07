package com.example.chatapp.ui.home.joinRoom

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JoinRoomViewModel : BaseViewModel() {
    val event = MutableLiveData<JoinRoomEvent>()

    fun JoinYourRoom(room: Room) {
        val user = Firebase.auth.currentUser ?: return
        Firebase.firestore.collection(constants.room)
            .document(room.uid!!)
            .get()
            .addOnCompleteListener { task ->
               val room =  task.result.toObject(Room::class.java)
                val ownerListId = room?.ownerListId as? MutableList<String>
               ownerListId?.add(user.uid)
                    Firebase.firestore.collection(constants.room)
                        .document(room?.uid!!)
                        .update(Room.ownerListIdField, ownerListId)
                        .addOnSuccessListener {
                            viewMessage.value = "hello in ${room.roomName}"
                            event.value = JoinRoomEvent.navigateToroom(room)
                        }
                        .addOnFailureListener { exception ->
                            viewMessage.value = exception.localizedMessage
                        }

            }
    }

    fun navigateToBack() {
        event.value = JoinRoomEvent.navigateToHome()
    }
}
