package org.sopt.dosopttemplate.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.presentation.model.User

class SignUpViewModel() : ViewModel() {

    private val _signUpResult = MutableLiveData<SignUpState>()
    val signUpResult: LiveData<SignUpState> get() = _signUpResult

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _user = MutableStateFlow<User>(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun setUser(user: User) = viewModelScope.launch {
        _user.value = user
    }

    fun isCorrectUserInfo() =
        viewModelScope.launch {
            val user = user.value
            if (!isValid(user))
                _signUpResult.postValue(SignUpState.EMPTY)
            else if (!checkIdLength(user.id) || !checkPwLength(user.pwd) || !isValidNickname(user.nickname))
                _signUpResult.postValue(SignUpState.FAIL)
            else _signUpResult.postValue(SignUpState.SUCCESS)
        }


    private fun checkIdLength(id: String) =
        id.isBlank() || id.length in 6..10

    private fun checkPwLength(pwd: String) =
        pwd.isBlank() || pwd.length in 8..12

    private fun isValid(user: User) =
        user.id.isNotBlank() && user.pwd.isNotBlank() && user.nickname.isNotBlank() && user.sojuCount.isNotBlank()

    private fun isValidNickname(nickname: String): Boolean {
        val regex = """^(?!\s+$).{1,}$""".toRegex()
        return regex.matches(nickname)
    }

    fun signUpEvent() {
        event(Event.SignUp(Unit))
    }

    private fun event(event: Event) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

    sealed class Event {
        data class SignUp(val p: Unit) : Event()
    }
}

enum class SignUpState {
    SUCCESS,
    FAIL,
    EMPTY,
}