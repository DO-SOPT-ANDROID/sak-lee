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

        val navController =
            supportFragmentManager.findFragmentById(R.id.fragment_container_main)
                ?.findNavController()!!

        with(binding) {
            bottomNavigationMain.itemIconTintList = null
            navController?.let { NavController ->
                bottomNavigationMain.setupWithNavController(NavController)
            }
        }

        setBottomVisible(navController)
    }

    private fun setBottomVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationMain.visibility = if (destination.id in listOf(
                    R.id.navigation_home,
                    R.id.navigation_do_android,
                    R.id.navigation_my_page,
                )
            ) View.VISIBLE else View.GONE

        }
    }


    companion object {
        const val BACKTIME = 2000
        const val BACK_TOAST = "한번 더 종료"
    }
}