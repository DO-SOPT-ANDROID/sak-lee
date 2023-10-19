package org.sopt.dosopttemplate.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.presentation.signin.SignEvent
import org.sopt.dosopttemplate.presentation.signin.SignInViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<MainEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun signOut() {
        authRepo.clear()
    }

    fun getUser() = authRepo.getUser()

    fun logoutEvent() {
        event(MainEvent.LogOut(Unit))
    }

    private fun event(event: MainEvent) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }
}
