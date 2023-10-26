package org.sopt.dosopttemplate.presentation.signin

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

    private val _user = MutableStateFlow<User>(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun setUser(user: User) = viewModelScope.launch {
        _user.value = user
    }

    fun signIn() {
        authRepo.saveUser(user.value.toUserEntity())
        authRepo.saveCheckLogin(true)
    }

    fun checkLogin() = authRepo.checkLogin()

    fun isCorrectUserInfo(regaxUser: User) = viewModelScope.launch {
        val user = user.value
        if (!checkIdCorrect(user.id, regaxUser.id) || !checkPwdCorrect(
                user.pwd,
                regaxUser.pwd
            )
        ) _signInResult.postValue(SignInState.FAIL)
        else if (user.id.isBlank() || user.pwd.isBlank()) _signInResult.postValue(SignInState.EMPTY)
        else _signInResult.postValue(SignInState.SUCCESS)
    }

    private fun checkIdCorrect(id: String, regaxId: String) =
        id == regaxId

    private fun checkPwdCorrect(pwd: String, regaxPwd: String) =
        pwd == regaxPwd

    fun signInEvent() {
        event(SignEvent.SignIn(Unit))
    }

    fun navigateSignUpEvent() {
        event(SignEvent.NavigateSignUp(Unit))
    }

    private fun event(event: SignEvent) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

}

