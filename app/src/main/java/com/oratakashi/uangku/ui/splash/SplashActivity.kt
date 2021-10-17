package com.oratakashi.uangku.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.ActivitySplashBinding
import com.oratakashi.uangku.ui.main.MainActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(MainActivity::class.java)
                finish()
            }, 1000L)
        }
    }

    private val binding: ActivitySplashBinding by viewBinding()
}