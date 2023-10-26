package org.sopt.dosopttemplate.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<MyPageEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun signOut() {
        authRepo.clear()
    }

    fun getUser() = authRepo.getUser()

    fun logoutEvent() {
        event(MyPageEvent.LogOut(Unit))
    }

    private fun event(event: MyPageEvent) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }
}
