package com.example.chatapp.myDateBase

import android.os.Parcelable
import android.widget.ImageView
import com.example.chatapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSpinner(
    val idImage :Int?=null,
    val imageView: Int?=null,
    val text:String?=null,
    ):Parcelable{
    companion object{
        val  listSpinner: List<DataSpinner> = listOf(
            DataSpinner(0,R.drawable.image_movies_cat,"movies"),
            DataSpinner(1,R.drawable.image_sports_cat,"sports"),
            DataSpinner(2,R.drawable.image_music_cat,"music"),
        )

    }

}

