package org.sopt.dosopttemplate.presentation.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.signup.SignUpActivity
import org.sopt.dosopttemplate.ui.base.BindingActivity
import org.sopt.dosopttemplate.ui.context.repeatOnStarted
import org.sopt.dosopttemplate.ui.context.snackBar
import org.sopt.dosopttemplate.ui.context.toast

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private lateinit var returnSignUpLauncher: ActivityResultLauncher<Intent>
    private val signInViewModel: SignInViewModel by viewModels()

//    override fun onStart() {
//        if (signInViewModel.checkLogin()) navigateTo<MainActivity>()
//        super.onStart()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickSignInBtn()
        clickSignUpBtn()
        collectSignUpEvent()
        setSignUpActivityLauncher()
        isCheckSignInResult()

    }

    private fun clickSignUpBtn() {
        binding.btnNavigateSignUp.setOnClickListener {
            signInViewModel.navigateSignUpEvent()
        }
    }

    private fun clickSignInBtn() {
        binding.btnSignIn.setOnClickListener {
            signInViewModel.signInEvent()
        }
    }

    private fun collectSignUpEvent() {
        repeatOnStarted {
            signInViewModel.eventFlow.collect(::handleEvent)
        }
    }

    private fun handleEvent(event: SignEvent) = when (event) {
        is SignEvent.SignIn -> {
            signInViewModel.isCorrectUserInfo(
                binding.etvId.text.toString(),
                binding.etvPwd.text.toString()
            )
        }

        is SignEvent.SignInSuccess -> {
            toast(SUCCESS_SIGN_MSG)
            signInViewModel.signInSuccess()
            navigateTo<MainActivity>()
        }

        is SignEvent.NavigateSignUp -> {
            navigateToSignUp()
        }
    }


    private fun navigateToSignUp() {
        Intent(this, SignUpActivity::class.java).apply {
            returnSignUpLauncher.launch(this)
        }
    }

    private fun setSignUpActivityLauncher() {
        returnSignUpLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
            }
        }
    }

    private fun isCheckSignInResult() {
        signInViewModel.signInResult.observe(this@SignInActivity) { signInResult ->
            when (signInResult) {
                SignInState.SUCCESS -> {
                    signInViewModel.signIn(
                        RequestSignInDto(
                            binding.etvId.text.toString(),
                            binding.etvPwd.text.toString()
                        )
                    )
                }

                SignInState.FAIL -> snackBar(binding.root) { FAIL_MSG }
                SignInState.EMPTY -> snackBar(binding.root) { EMPTY_MSG }
            }
        }
    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(this@SignInActivity, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    companion object {
        const val SUCCESS_SIGN_MSG = "로그인 성공"
        const val FAIL_MSG = "아디나 비번 다름"
        const val EMPTY_MSG = "비었음"
    }

}


