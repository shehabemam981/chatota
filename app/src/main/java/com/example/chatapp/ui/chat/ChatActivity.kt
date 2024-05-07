package com.example.chatapp.ui.chat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.myDateBase.MessageData
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.chat.adapter.messageAdapter
import com.example.chatapp.ui.home.adapter.RoomsAdapter

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {
   lateinit var room:Room
   lateinit var adapter: messageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      room = initGetData()
        ViewModel()
        viewModel.fetchMessage(room)
        viewModel.listenMessages(room)
        initRv()
        observeData()
        backRooms()

    }

    private fun backRooms() {
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeData() {
        viewModel.state.observe(this){
                state->
            when(state){
                is  ChatState.fetchDataRv->{
                    adapter.getData(state.mssages)
                    Log.e("dd", state.mssages.toString())
                }
                is ChatState.listenDataRv->{
                    adapter.listenData(state.messages)
                }
            }
        }
    }

    private fun initRv() {
        val emptyList  = listOf<MessageData>()

        adapter = messageAdapter(emptyList.toMutableList())
        binding.messagesRv.adapter = adapter
    }


    private fun ViewModel() {
        binding.vm = viewModel
        binding.room = room

        binding.lifecycleOwner = this
    }

    private fun initGetData():Room {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(constants.passedRoom,Room::class.java)?:Room()
        }else{
            intent.getParcelableExtra(constants.passedRoom)?:Room()
        }
    }

    override fun getLayoutId(): Int=R.layout.activity_chat

    override fun initViewModel(): ChatViewModel =ViewModelProvider(this)[ChatViewModel::class.java]
}