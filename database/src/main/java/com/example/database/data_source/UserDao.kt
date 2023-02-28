package com.example.database.data_source

import androidx.room.*
import com.example.database.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    fun getUserByFirstName(firstName: String): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user:User)

    @Update
    suspend fun updateAvatar(user:User)
}