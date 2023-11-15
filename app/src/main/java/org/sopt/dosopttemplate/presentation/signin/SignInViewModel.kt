package org.sopt.dosopttemplate.presentation.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import org.sopt.dosopttemplate.presentation.model.User
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _signInResult = MutableLiveData<SignInState>()
    val signInResult: LiveData<SignInState> get() = _signInResult

    private val _eventFlow = MutableSharedFlow<SignEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun signIn(requestSignInDto: RequestSignInDto) {
        viewModelScope.launch {
            authRepo.signIn(requestSignInDto)
                .onSuccess {
                    signInSuccessEvent()
                    Log.d("success", "$it")
                }
                .onFailure {
                    Log.d("fail", "$it")
                    _signInResult.postValue(SignInState.FAIL)
                }
        }
    }

    fun signInSuccess() {
        authRepo.saveCheckLogin(true)
    }

    fun checkLogin() = authRepo.checkLogin()

    fun isCorrectUserInfo(id:String,pwd:String) = viewModelScope.launch {
        if (id.isBlank() || pwd.isBlank()) _signInResult.postValue(SignInState.EMPTY)
        else _signInResult.postValue(SignInState.SUCCESS)
    }


    fun signInEvent() {
        event(SignEvent.SignIn(Unit))
    }

    fun signInSuccessEvent() {
        event(SignEvent.SignInSuccess(Unit))
    }

    fun navigateSignUpEvent() {
        event(SignEvent.NavigateSignUp(Unit))
    }

    private fun event(event: SignEvent) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

}

