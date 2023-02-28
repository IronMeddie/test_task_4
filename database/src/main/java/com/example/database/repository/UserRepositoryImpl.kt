package com.example.database.repository

import com.example.database.data_source.UserDao
import com.example.database.data_source.UserSP
import com.example.database.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


internal class UserRepositoryImpl(private val dao: UserDao, private val sp: UserSP) : UserRepository {
    override fun getUserByFirstName(firstName: String): Flow<User?> = dao.getUserByFirstName(firstName)

    override suspend fun insertUser(user: User) = dao.insertUser(user)

    override suspend fun deleteUser(user: User) = dao.deleteUser(user)
    override suspend fun updateAvatar(user: User) = dao.updateAvatar(user)

    override  fun getCurrentUser(): Flow<String> = sp.getCurrentUser()
    override suspend fun putCurrentUser(firstName: String)= sp.putCurrentUser(firstName)
    override suspend fun logout() = sp.logOut()
}