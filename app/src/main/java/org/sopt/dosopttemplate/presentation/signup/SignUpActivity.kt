package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.ui.base.BindingActivity
import org.sopt.dosopttemplate.ui.context.repeatOnStarted
import org.sopt.dosopttemplate.ui.context.snackBar
import org.sopt.dosopttemplate.ui.context.toast

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private lateinit var user: User
    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickSignUpBtn()
        collectSignUpEvent()
        isCheckSignUpResult()

    }

    private fun clickSignUpBtn() {
        binding.btnSignIn.setOnClickListener {
            signUpViewModel.signUpEvent()
        }
    }

    private fun collectSignUpEvent() {
        repeatOnStarted {
            signUpViewModel.eventFlow.collect(::handleEvent)
        }
    }

    private fun handleEvent(event: SignUpViewModel.Event) = when (event) {
        is SignUpViewModel.Event.SignUp -> {
            Log.d("내맘", "덤벼라 오비")
            user = with(binding) {
                User(
                    etvId.text.toString(),
                    etvPwd.text.toString(),
                    etvSojuCount.text.toString(),
                    etvNickname.text.toString()
                )
            }
            signUpViewModel.isCorrectUserInfo(user)
        }
    }

    private fun isCheckSignUpResult() {
        signUpViewModel.signUpResult.observe(this@SignUpActivity) { signUpResult ->
            when (signUpResult) {
                SignUpState.SUCCESS -> {
                    navigateToLoginActivity(user)
                    toast(SUCCESS_SIGN_MSG)
                }

                SignUpState.FAIL -> snackBar(binding.root) { FAIL_MSG }
                SignUpState.EMPTY -> snackBar(binding.root) { EMPTY_MSG }
            }
        }
    }

    private fun navigateToLoginActivity(usel: User) {
        intent.putExtra(USER, usel)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val USER = "USER"
        const val SUCCESS_SIGN_MSG = "회원가입 성공"
        const val FAIL_MSG = "아디나 비번 길이 안맞음"
        const val EMPTY_MSG = "모든 값을 입력"
    }
}

