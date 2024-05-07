package com.example.chatapp.ui.home.allRooms

import com.example.chatapp.myDateBase.Room

sealed class AllRoomEvent {
    data class navigateToChat(val room:Room):AllRoomEvent(){}
data class navigateToJoinRoom(val room: Room) :AllRoomEvent(){}
}