package com.oratakashi.uangku.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.ActivityMainBinding
import com.oratakashi.uangku.utils.onNavDestinationSelected
import com.oratakashi.viewbinding.core.binding.activity.viewBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            setupNavigation()
            Log.e("debug", "debug: Starting...")
            bnMain.addBubbleListener { bnMain.onNavDestinationSelected(it, nav) }
        }
    }

    private fun setupNavigation(){
        with(binding){
            val nav = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main)?.findNavController()
            nav?.let{
                it.addOnDestinationChangedListener { _, destination, _ ->
                    bnMain.setSelectedWithId(destination.id, false)
                    when(destination.id){
                        R.id.dashboardFragment, R.id.historyFragment, R.id.settingsFragment -> {
                            showBottomNav()
                        }
                        else -> hideBottomNav()
                    }
                }
            }
        }
    }

    private fun showBottomNav(duration: Int = 400) {
        with(binding){
            if (bnMain.visibility == View.VISIBLE) return
            bnMain.visibility = View.VISIBLE
            val animate = TranslateAnimation(0f, 0f, bnMain.height.toFloat(), 0f)
            animate.duration = duration.toLong()
            bnMain.startAnimation(animate)
        }
    }

    private fun hideBottomNav(duration: Int = 400) {
        with(binding){
            if (bnMain.visibility == View.GONE) return
            val animate = TranslateAnimation(0f, 0f, 0f, bnMain.height.toFloat())
            animate.duration = duration.toLong()
            bnMain.startAnimation(animate)
            bnMain.visibility = View.GONE
        }
    }

    private val nav: NavController by lazy { findNavController(R.id.nav_host_fragment_main) }
    private val binding: ActivityMainBinding by viewBinding()
}