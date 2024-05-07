package com.example.chatapp.ui.create_room

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.DataSpinner
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class CreateRoomViewModel : BaseViewModel() {
    val roomNameLiveData = MutableLiveData<String>()
    val descriptionLiveData = MutableLiveData<String>()
    val errorRomNameLiveData = MutableLiveData<String>()
    val errorDescriptionLiveData = MutableLiveData<String>()
    val categoryLiveData = MutableLiveData<DataSpinner?>()
    val event = MutableLiveData<CreateRoomEvent>()
    fun createRoom() {
        if (!isValidate()) return
        storeRoomInDataBase()
    }
    fun setSelectedItem(item: DataSpinner?) {
        categoryLiveData.value = item
    }

    private fun storeRoomInDataBase() {
        val user = Firebase.auth.currentUser
        val room = Room(
            roomName = roomNameLiveData.value,
            description = descriptionLiveData.value,
            ownerId = user?.uid,
            ownerListId = listOf(user!!.uid),
             categoryImageId = categoryLiveData.value?.idImage
        )
        Firebase.firestore.collection(constants.room)
            .add(room).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateRoomIdInDataBase(task.result.id, room)
                } else {
                    viewMessage.value = "you have error in update token in firebase"
                }

            }
    }

    private fun updateRoomIdInDataBase(uid: String, room: Room) {
        Firebase.firestore.collection(constants.room).document(uid).update(Room.uidField, uid)
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    val updatedRoom = room.copy(uid)
                    event.value = CreateRoomEvent.navigateToChatEvent(updatedRoom)

                }
                else{
                 viewMessage.value = "you have error "
                }

            }
    }

    fun isValidate(): Boolean {
        var isValid = true
        if (roomNameLiveData.value.isNullOrBlank()) {
            errorRomNameLiveData.value = "please,enter your room name"
            isValid = false
        }
        if (descriptionLiveData.value.isNullOrBlank()) {
            errorDescriptionLiveData.value = "please,enter your description"
            isValid = false

        }
        return isValid
    }
}
