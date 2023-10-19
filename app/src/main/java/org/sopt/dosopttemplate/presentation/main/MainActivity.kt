package org.sopt.dosopttemplate.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.presentation.signin.SignEvent
import org.sopt.dosopttemplate.presentation.signin.SignInActivity
import org.sopt.dosopttemplate.ui.context.repeatOnStarted
import org.sopt.dosopttemplate.ui.context.snackBar

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        initView()
        collectMainEvent()
        clickBtnLogout()

    }

    private fun clickBtnLogout() {
        binding.btnLogout.setOnClickListener {
            mainViewModel.logoutEvent()
        }
    }

    private fun initView() {
        val user = mainViewModel.getUser()

        with(binding) {
            tvId.text = user?.id
            tvSojuCount.text = user?.sojuCount
            tvNickname.text = user?.nickname
        }
    }

    private fun collectMainEvent() {
        repeatOnStarted {
            mainViewModel.eventFlow.collect(::handleEvent)
        }
    }

    private fun handleEvent(event: MainEvent) = when (event) {
        is MainEvent.LogOut -> {
            mainViewModel.signOut()
            navigateTo<SignInActivity>()
        }

    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(this@MainActivity, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

}