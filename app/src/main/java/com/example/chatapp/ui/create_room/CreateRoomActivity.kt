package com.example.chatapp.ui.create_room

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityCreateRoomBinding
import com.example.chatapp.myDateBase.DataSpinner
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.create_room.adapter.SpinnerAdapter
import com.example.chatapp.ui.home.HomeActivity

class CreateRoomActivity : BaseActivity<ActivityCreateRoomBinding, CreateRoomViewModel>() {
    lateinit var adapter: SpinnerAdapter
    override fun getLayoutId(): Int {
        return  R.layout.activity_create_room
    }

    override fun initViewModel(): CreateRoomViewModel {
        return  ViewModelProvider(this)[CreateRoomViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSpinner()
        initBindingVariables()
        backArrow()
        navigateToChat()
        choiseSpinner()
        observeSelectedItem()

    }



    private fun navigateToChat() {
        viewModel.event.observe(this){event->
            when(event){
                is CreateRoomEvent.navigateToChatEvent->{
                    // i must navigate to chat
                    startActivity(Intent(this,HomeActivity::class.java).putExtra(constants.passedRoom,event.room))
                }
            }

        }
    }

    private fun backArrow() {
     binding.arrowBack.setOnClickListener {
         onBackPressed()
     }
    }


    private fun initBindingVariables() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun initSpinner() {
         adapter = SpinnerAdapter(DataSpinner.listSpinner)
        binding.spinner.adapter = adapter
    }

    private fun observeSelectedItem() {
        viewModel.categoryLiveData.observe(this) { selectedItem ->
            selectedItem?.let { item ->
                viewModel.viewMessage.observe(this){item->
                    "selected ${item.toString()}"
                }
            }
        }
    }

    private fun choiseSpinner(){
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position) as? DataSpinner

                if (selectedItem != null) {
                    viewModel.setSelectedItem(selectedItem)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }
    }


}