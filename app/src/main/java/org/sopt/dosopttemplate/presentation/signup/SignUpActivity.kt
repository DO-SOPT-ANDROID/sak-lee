package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.ui.base.BindingActivity
import org.sopt.dosopttemplate.ui.context.repeatOnStarted
import org.sopt.dosopttemplate.ui.context.snackBar
import org.sopt.dosopttemplate.ui.context.toast

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickSignUpBtn()
        collectSignUpEvent()
        isCheckSignUpResult()
        collectUser()

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
            signUpViewModel.saveUser(
                UserEntity(
                    binding.etvId.text.toString(),
                    binding.etvPwd.text.toString(),
                    binding.etvSojuCount.text.toString(),
                    binding.etvNickname.text.toString()
                )
            )
        }

        is SignUpViewModel.Event.SignUpSuccess -> {
            Log.d("test2","test")
            navigateToLoginActivity()
        }
    }

    private fun collectUser() {
        signUpViewModel.user.flowWithLifecycle(lifecycle).onEach {
            signUpViewModel.isCorrectUserInfo()
        }.launchIn(lifecycleScope)
    }

    private fun isCheckSignUpResult() {
        signUpViewModel.signUpResult.observe(this@SignUpActivity) { signUpResult ->
            when (signUpResult) {
                SignUpState.SUCCESS -> {
                    signUpViewModel.signUp(
                        RequestSignUpDto(
                            binding.etvId.text.toString(),
                            binding.etvNickname.text.toString(),
                            binding.etvPwd.text.toString(),
                        )
                    )
                    toast(SUCCESS_SIGN_MSG)
                }

                SignUpState.FAIL -> {
                    snackBar(binding.root) { FAIL_MSG }
                    binding.tfId.error = FAIL_MSG
                    binding.tfPwd.error = FAIL_MSG
                }

                SignUpState.EMPTY -> snackBar(binding.root) { EMPTY_MSG }
            }
        }
    }

    private fun navigateToLoginActivity() {
        finish()
    }

    companion object {
        const val USER = "USER"
        const val SUCCESS_SIGN_MSG = "회원가입 성공"
        const val FAIL_MSG = "아디나 비번 길이 안맞음"
        const val EMPTY_MSG = "모든 값을 입력"
    }
}


