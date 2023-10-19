package org.sopt.dosopttemplate.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.presentation.signin.SignInActivity
import org.sopt.dosopttemplate.presentation.signin.SignInActivity.Companion.USER
import org.sopt.dosopttemplate.presentation.signin.SignInViewModel
import org.sopt.dosopttemplate.ui.context.getParcelable

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val signInViewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        initView()

        binding.ivUser.setOnClickListener {
            signInViewModel.signOut()
            Intent(this@MainActivity, SignInActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }
    }

    private fun initView() {
        val user = signInViewModel.getUser()

        with(binding) {
            tvId.text = user?.id
            tvSojuCount.text = user?.sojuCount
            tvNickname.text = user?.nickname
        }
    }

}