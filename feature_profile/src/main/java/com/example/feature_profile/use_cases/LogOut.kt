package com.example.feature_profile.use_cases

import com.example.database.repository.UserRepository
import javax.inject.Inject

class LogOut @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.logout()
}