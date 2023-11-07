package org.sopt.dosopttemplate.presentation.mypage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.presentation.signin.SignInActivity
import org.sopt.dosopttemplate.ui.base.BindingFragment
import org.sopt.dosopttemplate.ui.context.repeatOnStarted


@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectMainEvent()
        clickBtnLogout()
    }


    private fun clickBtnLogout() {
        binding.btnLogout.setOnClickListener {
            myPageViewModel.logoutEvent()
        }
    }

    private fun initView() {
        val user = myPageViewModel.getUser()

        with(binding) {
            tvId.text = user?.id
            tvSojuCount.text = user?.sojuCount
            tvNickname.text = user?.nickname
        }
    }

    private fun collectMainEvent() {
        repeatOnStarted {
            myPageViewModel.eventFlow.collect(::handleEvent)
        }
    }

    private fun handleEvent(event: MyPageEvent) = when (event) {
        is MyPageEvent.LogOut -> {
            myPageViewModel.signOut()
            navigateTo<SignInActivity>()
        }

    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(requireActivity(), T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

}