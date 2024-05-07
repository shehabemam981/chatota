package com.example.chatapp.ui.chat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.databinding.RecieveMessageBinding
import com.example.chatapp.databinding.SendMessageBinding
import com.example.chatapp.myDateBase.MessageData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class messageAdapter(var messages: MutableList<MessageData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class sendViewHolder(val binding: SendMessageBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(message: MessageData){
            binding.message = message
        }

    }
    class recieveViewHolder(val binding: RecieveMessageBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(message: MessageData){
            binding.message =message
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      when(viewType){
          choiseMessage.sendMessage.i->{
              val binding = SendMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
              return  sendViewHolder(binding)
          }else->{
              val binding = RecieveMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
          return  recieveViewHolder(binding)
          }
      }
    }

    override fun getItemCount(): Int =messages.size
    override fun getItemViewType(position: Int): Int {
        val userId = Firebase.auth.currentUser?.uid
        val senderId = messages[position].senderId
        if (senderId==userId)return choiseMessage.sendMessage.i else return choiseMessage.recieveMessage.i
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
      when(holder){
        is sendViewHolder ->{
            holder.bind(message)
        }  is recieveViewHolder->{
            holder.bind(message)
      }
      }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(messagesList: List<MessageData>) {
    this.messages = messagesList.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun listenData(messages: MessageData) {
        this.messages.add(messages)
        notifyDataSetChanged()
    }

}