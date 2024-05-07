package com.example.chatapp.myDateBase


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.firebase.Timestamp

import java.util.Date
import java.util.Locale

@Parcelize
data class MessageData (
    val messageId:String?=null,
    var content:String? = null,
    val timeStamp:Timestamp? = null,
    val senderId:String? = null,

    ):Parcelable{
    fun getFormattedTime(): String {
        val date = Date(timeStamp?.toDate()?.time!!)
        val timeFormatter = java.text.SimpleDateFormat("hh:mm a", Locale.US)
        return timeFormatter.format(date)
    }
}