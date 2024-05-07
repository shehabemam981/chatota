package com.example.chatapp.ui.auth.fragment.login

import com.example.chatapp.myDateBase.Users

sealed class LoginViewEvent {
data class navidgateToHome(val users: Users):LoginViewEvent()
}