package com.example.feature_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.entity.User
import com.example.feature_profile.use_cases.LogOut
import com.example.feature_profile.use_cases.UpdateAvatar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val logOut: LogOut, private val updateAvatar: UpdateAvatar) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()


    fun logOut(){
       viewModelScope.launch {
           logOut.invoke()
       }
    }




}