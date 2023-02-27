package com.example.feature_f.domain.usecase

import com.example.database.data_source.UserSP
import com.example.database.entity.User
import com.example.database.repository.UserRepository
import javax.inject.Inject


class GetCurrentUser @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): User? {
        val firstName = repository.getCurrentUser()
        return if (firstName != UserSP.USER_IS_NULL) {
            repository.getUserByFirstName(firstName)
        } else null
    }
}