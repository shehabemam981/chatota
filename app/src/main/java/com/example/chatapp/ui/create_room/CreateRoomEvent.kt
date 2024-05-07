package com.example.chatapp.ui.create_room

import com.example.chatapp.myDateBase.Room

sealed class CreateRoomEvent {
data class navigateToChatEvent(var room: Room):CreateRoomEvent(){}
}