package org.sopt.dosopttemplate.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import org.sopt.dosopttemplate.presentation.model.LoginFormState
import org.sopt.dosopttemplate.presentation.model.User
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _signUpResult = MutableLiveData<SignUpState>()
    val signUpResult: LiveData<SignUpState> get() = _signUpResult

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _user = MutableStateFlow<UserEntity>(User().toUserEntity())
    val user: StateFlow<UserEntity> = _user.asStateFlow()

    val id = MutableLiveData<String>()

    val pwd = MutableLiveData<String>()

    val nickname = MutableLiveData<String>()

    private val _loginForm = MutableLiveData<LoginFormState>()

    val loginFormState: LiveData<LoginFormState> = _loginForm.map {
        when {
            isValidId(id.value ?: "").not() -> LoginFormState(idError = "ID_REGEX_MSG")
            isValidPwd(pwd.value ?: "").not() -> LoginFormState(pwError = "PWD_REGEX_MSG")
            isValidNickname(nickname.value ?: "").not() -> LoginFormState(isDataValid = false)
            else -> LoginFormState(isDataValid = true)
        }
    }

    fun setUser() = viewModelScope.launch {
        _user.value = authRepo.getUser()
    }

    fun saveUser(userEntity: UserEntity) {
        authRepo.saveUser(userEntity)
        setUser()
    }

    fun signUp(requestSignUpDto: RequestSignUpDto) {
        viewModelScope.launch {
            authRepo.signUp(requestSignUpDto = requestSignUpDto)
                .onSuccess {
                    signUpSuccessEvent()
                    Log.d("test", "$it")
                }
                .onFailure {
                    Log.d("test", "$it")
                }
        }
    }

    fun isCorrectUserInfo() =
        viewModelScope.launch {
            val user = user.value
            if (!isValid(user))
                _signUpResult.postValue(SignUpState.EMPTY)
            else if (!isValidId(id.value ?: "") || !isValidPwd(
                    pwd.value ?: ""
                ) || !isValidNickname(nickname.value ?: "")
            )
                _signUpResult.postValue(SignUpState.FAIL)
            else _signUpResult.postValue(SignUpState.SUCCESS)
        }


    fun loginDataChanged() {
        _loginForm.value = LoginFormState()
    }

    private fun isValidId(id: String) =
        ID_PATTERN.matches(id)

    private fun isValidPwd(pwd: String) =
        PASSWORD_PATTERN.matches(pwd)

    private fun isValid(user: UserEntity) =
        user.id.isNotBlank() && user.pwd.isNotBlank() && user.nickname.isNotBlank() && user.sojuCount.isNotBlank()

    private fun isValidNickname(nickname: String) =
        NICKNAME_PATTERN.matches(nickname)


    fun signUpEvent() {
        event(Event.SignUp(Unit))
    }

    private fun signUpSuccessEvent() {
        event(Event.SignUpSuccess(Unit))
    }

    private fun event(event: Event) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

    sealed class Event {
        data class SignUp(val p: Unit) : Event()
        data class SignUpSuccess(val p: Unit) : Event()
    }

    companion object {
        private val ID_PATTERN = """^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}""".toRegex()
        private val PASSWORD_PATTERN =
            """^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}""".toRegex()
        private val NICKNAME_PATTERN = """^(?!\s+$).{2,}""".toRegex()
    }
}

enum class SignUpState {
    SUCCESS,
    FAIL,
    EMPTY,
}