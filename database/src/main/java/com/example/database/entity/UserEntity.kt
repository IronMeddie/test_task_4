package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.models.User

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val avatar: String
){
    fun toUser() = User(firstName, lastName, email, password, avatar)
}

fun User.toEntity(): UserEntity{
    return UserEntity(firstName, lastName, email, password, avatar)
}
