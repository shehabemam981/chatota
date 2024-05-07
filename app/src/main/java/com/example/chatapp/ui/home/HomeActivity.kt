package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle

import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.ui.create_room.CreateRoomActivity
import com.example.chatapp.ui.home.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>() {
    override fun initViewModel(): HomeViewModel {
      return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel()
          adapterViewPager()
        navigateCreateRoom()
    }

    private fun navigateCreateRoom() {
        viewModel.event.observe(this){event->
            when(event){
                HomeEvent.navigateToCreateRoom->{
                    startActivity(Intent(this,CreateRoomActivity::class.java))
                }
            }
        }
    }

    private fun homeViewModel() {
        binding.vm = viewModel
        binding.lifecycleOwner =this
    }

    private fun adapterViewPager() {
        val adapter = ViewPagerAdapter(this)
        binding.roomsViewPager.adapter = adapter
         TabLayoutMediator(binding.roomsTabLayout,binding.roomsViewPager){ tab, position ->
             val tabs =resources.getStringArray(R.array.titles_view_pager)?: emptyArray()
       tab.text =tabs[position]
         }.attach()
    }


}