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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val user: User? = if (intent != null) {
            intent.getParcelable(USER, User::class.java) as? User
        } else {
            null
        }

        with(binding) {
            tvId.text = user?.id
            tvSojuCount.text = user?.sojuCount
            tvNickname.text = user?.nickname
        }
    }

}