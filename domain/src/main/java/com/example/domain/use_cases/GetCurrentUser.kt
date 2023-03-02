package com.example.domain.use_cases

import com.example.database.repository.UserRepository
import com.example.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


class GetCurrentUser @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Flow<User?> = repository.getCurrentUser().flatMapLatest {
        repository.getUserByFirstName(it)
    }
}