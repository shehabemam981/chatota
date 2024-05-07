package com.example.chatapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityAuthBinding

class MainActivity : AppCompatActivity() {
    var _binding:ActivityAuthBinding? = null
    val binding :ActivityAuthBinding get() = _binding!!
    lateinit var  navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavComponant()
    }

    private fun initNavComponant() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.auth_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}