package com.example.database.repository

import com.example.database.data_source.UserDao
import com.example.database.entity.User


class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override suspend fun getUserByFirstName(firstName: String): User? =
        dao.getUserByFirstName(firstName)

    override suspend fun insertUser(user: User) = dao.insertUser(user)

    override suspend fun deleteUser(user: User) = dao.deleteUser(user)
}