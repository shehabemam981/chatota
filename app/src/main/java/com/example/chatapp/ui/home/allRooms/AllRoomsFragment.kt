package com.example.chatapp.ui.home.allRooms

import android.content.Intent
import android.os.Bundle

import android.view.View
import androidx.fragment.app.add

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentAllRoomsBinding
import com.example.chatapp.myDateBase.Room
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.chat.ChatActivity
import com.example.chatapp.ui.home.adapter.RoomsAdapter
import com.example.chatapp.ui.home.joinRoom.JoinRoomFragment


class AllRoomsFragment : BaseFragment<FragmentAllRoomsBinding,AllRoomsVIewModel>() {

lateinit var adapter:RoomsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        ObserveData()
        viewModel.getAllRooms()
    }

    private fun initRecyclerView() {
         adapter = RoomsAdapter(emptyList())
         binding.allRoomRv.adapter =adapter
        adapter.SetOnClickListener{room->
            viewModel.CheckUserInRoom(room)
            viewModel.event.observe(viewLifecycleOwner){event->
                when(event){
                    is AllRoomEvent.navigateToJoinRoom->{
                        val joinRoomFragment = JoinRoomFragment()
                        val args = Bundle().apply {
                            putParcelable(constants.passedRoom,room)
                        }
                        joinRoomFragment.arguments =args
                        parentFragmentManager.beginTransaction().
                        replace(R.id.join_fragment,joinRoomFragment).addToBackStack(null).commit()


                    }is AllRoomEvent.navigateToChat->{
                    startActivity(Intent(requireActivity(),ChatActivity::class.java).putExtra(constants.passedRoom,room))

                }
                }
            }


        }
    }

    private fun ObserveData() {
        viewModel.roomsLiveData.observe(viewLifecycleOwner) { listRoom ->
            adapter.updateRoom(listRoom)

        }

    }


    override fun initViewModel(): AllRoomsVIewModel =ViewModelProvider(this)[AllRoomsVIewModel::class.java]
    override fun getLayoutId(): Int = R.layout.fragment_all_rooms




}