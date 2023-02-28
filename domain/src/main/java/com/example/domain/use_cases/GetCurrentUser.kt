package com.example.domain.use_cases

import com.example.database.data_source.UserSP
import com.example.database.entity.User
import com.example.database.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class GetCurrentUser @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Flow<User?> = repository.getCurrentUser().flatMapLatest {
        repository.getUserByFirstName(it)
    }
}