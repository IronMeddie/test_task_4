package com.example.database.data_source

import androidx.room.*
import com.example.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE firstName = :firstName")
    fun getUserByFirstName(firstName: String): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateAvatar(user: UserEntity)
}