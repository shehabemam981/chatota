package com.example.chatapp.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vb:ViewBinding,vm:BaseViewModel>:AppCompatActivity() {
    var _binding : vb? = null
    val binding :vb get() = _binding!!


lateinit var viewModel: vm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          _binding = DataBindingUtil.inflate(
              layoutInflater,getLayoutId(),null,false)
    setContentView(binding.root)
        viewModel = initViewModel()
        viewModel.viewMessage.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }


    }
abstract fun getLayoutId():Int
abstract fun initViewModel():vm
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}