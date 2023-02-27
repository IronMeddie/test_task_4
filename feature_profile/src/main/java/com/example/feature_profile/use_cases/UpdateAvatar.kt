package com.example.feature_profile.use_cases

import com.example.database.entity.User
import com.example.database.repository.UserRepository
import javax.inject.Inject

class UpdateAvatar @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(user: User) {
        repository.updateAvatar(user)
    }

}