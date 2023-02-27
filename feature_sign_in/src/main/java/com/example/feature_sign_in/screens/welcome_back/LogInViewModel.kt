package com.example.feature_sign_in.screens.welcome_back

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_sign_in.domain.use_cases.GetUserByName
import com.example.feature_sign_in.domain.use_cases.NewCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val getUserByName: GetUserByName , private val currentUser: NewCurrentUser) : ViewModel() {

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _eventFLow = MutableSharedFlow<Logged>()
    val eventFLow = _eventFLow.asSharedFlow()


    fun logIn(){
        viewModelScope.launch {
           val user = getUserByName(firstName = firstName.value)
            if (user != null){
                _eventFLow.emit(Logged.Success)
                currentUser(user)
            } else{
                _eventFLow.emit(Logged.Failure("User not exist"))
            }

        }
    }

    fun updateFirstName(str:String){
        if (!str.contains("\n")) _firstName.value = str
    }

    fun updatePassword(str:String){
        if (!str.contains("\n")) _password.value = str
    }
}

sealed class Logged{
    object Success : Logged()
    data class Failure(val message: String): Logged()
}