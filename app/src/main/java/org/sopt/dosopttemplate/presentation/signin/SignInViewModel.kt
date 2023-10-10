package org.sopt.dosopttemplate.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.presentation.model.User

class SignInViewModel() : ViewModel() {

    private val _signInResult = MutableLiveData<SignInState>()
    val signInResult: LiveData<SignInState> get() = _signInResult

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun isCorrectUserInfo(user: User, regaxUser: User) {
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
        event(Event.SignIn(Unit))
    }

    fun navigateSignUpEvent() {
        event(Event.navigateSignUp(Unit))
    }

    private fun event(event: Event) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

    sealed class Event {
        data class SignIn(val p: Unit) : Event()
        data class navigateSignUp(val p: Unit) : Event()
    }
}

enum class SignInState {
    SUCCESS,
    FAIL,
    EMPTY,
}