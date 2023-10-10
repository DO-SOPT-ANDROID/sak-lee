package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.presentation.signin.SignInActivity.Companion.USER
import org.sopt.dosopttemplate.ui.context.getParcelable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        user = intent?.getParcelable(USER, User::class.java) ?: return
        initView()
    }

    private fun initView() {
        with(binding) {
            tvId.text = user.id
            tvSojuCount.text = user.sojuCount
            tvNickname.text = user.nickname
        }
    }


}