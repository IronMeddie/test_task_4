package com.example.feature_f.domain.usecase

import com.example.database.entity.User
import com.example.database.repository.UserRepository
import javax.inject.Inject


class GetCurrentUser @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() : User?{
       val firstName =  repository.getCurrentUser()
        return if (firstName != null){
            val user = repository.getUserByFirstName(firstName)
            user
        }else null
    }
}