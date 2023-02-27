package com.example.database.data_source

import android.content.Context
import javax.inject.Inject

class UserSP @Inject constructor(context: Context) {

    private val sharedPref = context.getSharedPreferences("com.example.app", Context.MODE_PRIVATE)

    fun getCurrentUser() : String?{
        return sharedPref.getString("currentUser",null)
    }

    fun putCurrentUser(firstName: String){
        sharedPref.edit().putString("currentUser", firstName).apply()
    }

    fun logOut(){
        sharedPref.edit().putString("currentUser", null).apply()
    }

}