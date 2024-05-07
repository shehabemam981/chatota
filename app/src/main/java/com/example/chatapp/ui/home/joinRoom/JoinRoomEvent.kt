package com.example.chatapp.ui.home.joinRoom

import com.example.chatapp.myDateBase.Room

sealed class JoinRoomEvent {
class navigateToroom (room: Room):JoinRoomEvent()
    class navigateToHome:JoinRoomEvent()
}