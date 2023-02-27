package com.example.database.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.database.data_source.UserDatabase
import com.example.database.data_source.UserSP
import com.example.database.repository.UserRepository
import com.example.database.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNoteDatabase(app: Application): UserDatabase = Room.databaseBuilder(
        app,
        UserDatabase::class.java, UserDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideUserSp(@ApplicationContext context: Context) = UserSP(context)

    @Provides
    @Singleton
    fun provideNoteRepository(db: UserDatabase, userSP: UserSP): UserRepository = UserRepositoryImpl(db.userDao, userSP)



}