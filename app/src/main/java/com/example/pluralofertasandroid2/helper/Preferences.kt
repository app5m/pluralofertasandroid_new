package com.example.pluralofertasandroid2.helper

import android.content.Context

class Preferences(context: Context?) {
    /**
     * Preferences
     *
     * @author Android version: Wesley Costa
     * @since Version 1.0.2
     * @Created  06/2020 - 01/2021
     */

    private var preferences = context?.getSharedPreferences("high.preference", 0)
    private var editor = preferences?.edit()

   /* fun setUserData(user: User?) {
        val data = Gson().toJson(user)
        editor!!.putString("getData", data)
        editor!!.commit()
    }

    fun getUserData(): User? {
        val user: User
        val gson = Gson()
        val data = preferences!!.getString("getData", "")
        return if (data != null && data.isNotEmpty()) {
            user = gson.fromJson(data, User::class.java)
            user
        } else null
    }*/

    fun setLogin(enable: Boolean){
        editor!!.putBoolean("login", enable)
        editor!!.commit()
    }

    fun getLogin(): Boolean{
        return preferences?.getBoolean("login", false)!!
    }

    fun clearUserData(){
        editor?.remove("getData")
        editor?.remove("login")
        editor!!.commit()
    }

    fun firstUser(): String? {
        return preferences!!.getString("user", "0")
    }


}