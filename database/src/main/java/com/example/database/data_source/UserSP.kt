package com.example.database.data_source

import android.content.Context
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserSP @Inject constructor(context: Context) {

    private val sharedPref = context.getSharedPreferences("com.example.app", Context.MODE_PRIVATE)

    fun getCurrentUser() = flow { emit(sharedPref.getString("currentUser", null) ?: USER_IS_NULL) }


    fun putCurrentUser(firstName: String) {
        sharedPref.edit().putString("currentUser", firstName).apply()
    }

    fun logOut() {
        sharedPref.edit().putString("currentUser", USER_IS_NULL).apply()
    }

    companion object {
        const val USER_IS_NULL = "user_is_null"
    }

}