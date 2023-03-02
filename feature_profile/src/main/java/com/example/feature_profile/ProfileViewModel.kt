package com.example.feature_profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.GetCurrentUser
import com.example.domain.use_cases.LogOut
import com.example.domain.use_cases.UpdateAvatar
import com.example.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOut: LogOut,
    private val updateAvatar: UpdateAvatar,
    private val getCurrentUser: GetCurrentUser
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()


    init {
        getUser()
    }

    fun logOut() {
        viewModelScope.launch {
            logOut.invoke()
        }
    }
    fun getUser(){
        getCurrentUser().onEach {
            _user.value = it
        }.launchIn(viewModelScope)
    }

    fun saveAvatar(uri: Uri){
        viewModelScope.launch {
            val newUser = _user.value?.copy(avatar = uri.toString())
            updateAvatar(newUser ?: return@launch)
        }
    }


}