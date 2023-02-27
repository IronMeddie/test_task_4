package com.example.database.repository

import com.example.database.data_source.UserDao
import com.example.database.data_source.UserSP
import com.example.database.entity.User


class UserRepositoryImpl(private val dao: UserDao, private val sp: UserSP) : UserRepository {
    override suspend fun getUserByFirstName(firstName: String): User? =
        dao.getUserByFirstName(firstName)

    override suspend fun insertUser(user: User) = dao.insertUser(user)

    override suspend fun deleteUser(user: User) = dao.deleteUser(user)
    override suspend fun updateAvatar(user: User) = dao.updateAvatar(user)


    override suspend fun getCurrentUser(): String? = sp.getCurrentUser()
    override suspend fun putCurrentUser(firstName: String)= sp.putCurrentUser(firstName)
    override suspend fun logout() = sp.logOut()
}