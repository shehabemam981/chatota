package com.example.chatapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chatapp.R
import com.example.chatapp.ui.auth.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigatetoAuthScreen()
    }

    private fun navigatetoAuthScreen() {
        Handler(mainLooper).postDelayed(
            {
            startActivity(Intent(this, MainActivity::class.java))
                finish()
            },2000
        )
    }


}