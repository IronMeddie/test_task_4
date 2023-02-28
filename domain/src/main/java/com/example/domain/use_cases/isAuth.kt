package com.example.domain.use_cases

import com.example.database.data_source.UserSP
import com.example.database.repository.UserRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsAuth @Inject constructor(private val repository: UserRepository) {
    operator fun invoke() = repository.getCurrentUser().map { if (it == UserSP.USER_IS_NULL) AuthState.NotAuthorizated else AuthState.Authorizated }
}

sealed class AuthState() {
    object Loading : AuthState()
    object Authorizated : AuthState()
    object NotAuthorizated : AuthState()
}