package com.example.chatapp.myDateBase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val uid:String?=null,
    val roomName:String?=null,
    val description:String?=null,
    val ownerId:String?=null,
    val ownerListId:List<String>?=null,
    var categoryImageId: Int?=null
):Parcelable{
    fun membersCount(): Int {
        return ownerListId?.size?:0
    }
    companion object{
        const val ownerListIdField = "ownerListId"
        const val uidField = "uid"
        const val ownerIdField = "ownerId"
    }
}
