package com.example.domain.use_cases

import com.example.database.repository.UserRepository
import javax.inject.Inject

class GetUserByName @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(firstName: String) = repository.getUserByFirstName(firstName)
}