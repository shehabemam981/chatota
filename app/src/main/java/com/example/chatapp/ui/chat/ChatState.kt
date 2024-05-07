package com.example.chatapp.ui.chat

import com.example.chatapp.myDateBase.MessageData

sealed class ChatState {
    class fetchDataRv(val mssages:MutableList<MessageData>) :ChatState(){}
    class listenDataRv(val messages: MessageData):ChatState()
}