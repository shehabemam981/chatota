package com.example.chatapp.ui.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.MessageData
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatViewModel : BaseViewModel() {
    val messageLiveData = MutableLiveData<String?>()
val state = MutableLiveData<ChatState>()
    fun sentMessage(room: Room) {
        val user = Firebase.auth.currentUser
        val message = MessageData(
            content = messageLiveData.value,
            senderId = user!!.uid,
            timeStamp = Timestamp.now()
        )
        if (message.content != null) {

            Firebase.firestore.collection(constants.room).document(room.uid!!)
                .collection(constants.messages).add(message).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        messageLiveData.value = null
                    } else {
                        viewMessage.value = task.exception?.localizedMessage
                    }
                }
        }
    }

    fun fetchMessage(room: Room) {

        Firebase.firestore.collection(constants.room).document(room.uid!!)
            .collection(constants.messages).get().addOnCompleteListener {
                task->
                if(task.isSuccessful){
                    val messages = task.result.toObjects(MessageData::class.java)
                    state.value = ChatState.fetchDataRv(messages)

                }else{
                    viewMessage.value = task.exception?.localizedMessage
                }
            }
    }
   fun listenMessages(room:Room){
        Firebase.firestore.collection(constants.room).document(room.uid!!)
            .collection(constants.messages).addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("error", "listen:error", e)
                    return@addSnapshotListener
                }

                for (document in snapshots!!.documentChanges) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> state.value = ChatState.listenDataRv(document.document.toObject(MessageData::class.java))
                        DocumentChange.Type.MODIFIED ->{}
                        DocumentChange.Type.REMOVED ->{

                        }

                    }
                }
            }
    }
}
