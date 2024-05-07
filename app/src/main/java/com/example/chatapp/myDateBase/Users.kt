package com.example.chatapp.myDateBase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val uid:String? =null ,
    val userName :String? = null,
    val email:String? = null,
):Parcelable
