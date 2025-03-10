package com.example.database.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user"
    }
}