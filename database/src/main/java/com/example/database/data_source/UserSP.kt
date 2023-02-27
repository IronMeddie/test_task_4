package com.example.database.data_source

import android.content.Context
import android.util.Log
import javax.inject.Inject

class UserSP @Inject constructor(context: Context) {

    private val sharedPref = context.getSharedPreferences("com.example.app", Context.MODE_PRIVATE)

     fun getCurrentUser() : String{
        return sharedPref.getString("currentUser",null) ?: USER_IS_NULL
    }

    fun putCurrentUser(firstName: String){
        sharedPref.edit().putString("currentUser", firstName).apply()
    }

    fun logOut(){
        Log.d("checkCodeLogOut", "loging out")
        sharedPref.edit().putString("currentUser", USER_IS_NULL).apply()
    }

    companion object{
        const val USER_IS_NULL = "user_is_null"
    }

}