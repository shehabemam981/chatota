package com.example.chatapp.ui.home.myRooms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentMyRoomsBinding
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.chat.ChatActivity
import com.example.chatapp.ui.home.adapter.RoomsAdapter
import com.example.chatapp.ui.home.joinRoom.JoinRoomFragment


class MyRoomsFragment : BaseFragment<FragmentMyRoomsBinding, MyRoomsViewModel>() {

    lateinit var adapter: RoomsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniRecyclerView()
        observeData()
        viewModel.getMyRooms()
    }
    private fun iniRecyclerView() {
        adapter = RoomsAdapter(emptyList())
        adapter.SetOnClickListener{ room->
           val intent =   Intent(requireActivity(),ChatActivity::class.java).putExtra(constants.passedRoom,room)
            startActivity(intent)
        }
        binding.myRoomRv.adapter = adapter
    }

    private fun observeData() {
        viewModel.myRoomsLiveData.observe(viewLifecycleOwner) { rooms ->
            adapter.updateRoom(rooms)
        }
    }


    override fun initViewModel(): MyRoomsViewModel =
        ViewModelProvider(this)[MyRoomsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms


}