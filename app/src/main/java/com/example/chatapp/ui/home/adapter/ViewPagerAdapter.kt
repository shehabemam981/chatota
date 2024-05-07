package com.example.chatapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.home.allRooms.AllRoomsFragment
import com.example.chatapp.ui.home.myRooms.MyRoomsFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int =constants.numberpages



    override fun createFragment(position: Int): Fragment {
     val fragment =   when(position){
           0 ->{
             AllRoomsFragment()

           } else ->{
               MyRoomsFragment()
           }
       }
        return  fragment
    }

}