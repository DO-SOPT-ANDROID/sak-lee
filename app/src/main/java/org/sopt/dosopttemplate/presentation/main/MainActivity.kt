package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.ui.context.toast

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var back: Long = 0
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - back >= BACKTIME) {
                back = System.currentTimeMillis()
                toast(BACK_TOAST)
            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        this.onBackPressedDispatcher.addCallback(this, callback)

        setNav()
    }

    private fun setNav() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.fragment_container_main)
                ?.findNavController()

        with(binding) {
            navController?.let { NavController ->
                bottomNavigationMain.setupWithNavController(NavController)
            }
        }
    }


    companion object {
        const val BACKTIME = 2000
        const val BACK_TOAST = "한번 더 종료"
    }
}