package com.example.chatapp.ui.auth.signUp

import com.example.chatapp.myDateBase.Users

sealed class SignUpViewEvent {
 data class navigateSignUptoHome (var users: Users):SignUpViewEvent(){}
    class navigateToSignUp:SignUpViewEvent(){}
}