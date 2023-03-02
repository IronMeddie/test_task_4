package com.example.database.repository

import com.example.database.data_source.UserDao
import com.example.database.data_source.UserSP
import com.example.database.entity.UserEntity
import com.example.database.entity.toEntity
import com.example.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal class UserRepositoryImpl(private val dao: UserDao, private val sp: UserSP) : UserRepository {
    override fun getUserByFirstName(firstName: String): Flow<User?> = dao.getUserByFirstName(firstName).map { it?.toUser() }

    override suspend fun insertUser(user: User) = dao.insertUser(user.toEntity())

    override suspend fun deleteUser(user: User) = dao.deleteUser(user.toEntity())
    override suspend fun updateAvatar(user: User) = dao.updateAvatar(user.toEntity())

    override  fun getCurrentUser(): Flow<String> = sp.getCurrentUser()
    override suspend fun putCurrentUser(firstName: String)= sp.putCurrentUser(firstName)
    override suspend fun logout() = sp.logOut()
}