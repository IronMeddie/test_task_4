package com.example.domain.use_cases

import com.example.database.repository.UserRepository
import com.example.models.User
import javax.inject.Inject

class UpdateAvatar @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(user: User) {
        repository.updateAvatar(user)
    }

}