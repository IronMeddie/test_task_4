package com.example.feature_sign_in.domain.use_cases

import com.example.database.entity.User
import com.example.database.repository.UserRepository
import javax.inject.Inject


class InsertUserToDB @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.insertUser(user)
}