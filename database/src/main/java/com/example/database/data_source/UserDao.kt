package com.example.database.data_source

import androidx.room.*
import com.example.database.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    suspend fun getUserByFirstName(firstName: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user:User)

    @Update
    suspend fun updateAvatar(user:User)
}