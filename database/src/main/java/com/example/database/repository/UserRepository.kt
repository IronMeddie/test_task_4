package com.example.database.repository

import com.example.database.entity.User


interface UserRepository {
    suspend fun getUserByFirstName(firstName:String) : User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateAvatar(user: User)


    suspend fun getCurrentUser(): String
    suspend fun putCurrentUser(firstName: String)
    suspend fun logout()
}