package com.example.database.repository

import com.example.database.entity.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getUserByFirstName(firstName:String) : Flow<User?>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateAvatar(user: User)


    fun getCurrentUser(): Flow<String>
    suspend fun putCurrentUser(firstName: String)
    suspend fun logout()
}