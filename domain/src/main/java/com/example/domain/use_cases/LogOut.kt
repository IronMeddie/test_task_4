package com.example.domain.use_cases

import com.example.database.repository.UserRepository
import javax.inject.Inject

class LogOut @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.logout()
}