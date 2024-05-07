package com.example.chatapp.ui.home.joinRoom

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentJoinRoomBinding
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.chat.ChatActivity


class JoinRoomFragment : BaseFragment<FragmentJoinRoomBinding ,JoinRoomViewModel>() {
    override fun initViewModel(): JoinRoomViewModel  =ViewModelProvider(this)[JoinRoomViewModel::class.java]
    override fun getLayoutId(): Int =R.layout.fragment_join_room

     lateinit var roomNull:Room
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       roomNull = GetData()!!
       initBindVariables()
        observeData()
        viewModel.navigateToBack()
    }

    private fun observeData() {
        viewModel.event.observe(viewLifecycleOwner){ event->
            when (event){
                is  JoinRoomEvent.navigateToHome->{
                    binding.arrowBack.setOnClickListener {
                        fragmentManager?.popBackStackImmediate()


                    }
                }
               is JoinRoomEvent.navigateToroom->{
                  startActivity(Intent(requireActivity(),ChatActivity::class.java).putExtra(constants.passedRoom,roomNull))
                }
            }

        }


    }

    private fun initBindVariables() {
        binding.room = roomNull
       val image = roomNull.categoryImageId
        when(image){
            0->{
                binding.imageCat.setImageResource( R.drawable.image_movies_cat)
            }
            1 ->{
                binding.imageCat.setImageResource( R.drawable.image_sports_cat)
            }
            2->{
                binding.imageCat.setImageResource( R.drawable.image_music_cat)
            }
        }


        binding.vm = viewModel
        binding.lifecycleOwner=this
    }

    private fun GetData():Room? {
        val room:Room?= arguments?.getParcelable(constants.passedRoom)
       return room
    }
}


