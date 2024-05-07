package com.example.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel

class HomeViewModel:BaseViewModel() {
  val  event = MutableLiveData<HomeEvent>()
    fun onClickToCreateRoom(){
    event.value = HomeEvent.navigateToCreateRoom
}
}