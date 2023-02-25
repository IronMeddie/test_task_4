package com.example.database.repository

import com.example.database.entity.User


interface UserRepository {
    suspend fun getUserByFirstName(firstName:String) : User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)
}