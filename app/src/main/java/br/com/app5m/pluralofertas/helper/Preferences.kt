package br.com.app5m.pluralofertas.helper

import android.content.Context
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import com.google.gson.Gson

class Preferences(context: Context?) {

    /**
     * Preferences
     *
     */

    val ENTERING_FIRST_TIME = "EnteringFirstTime"

    private var preferences = context?.getSharedPreferences("high.preference", 0)
    private var editor = preferences?.edit()

    fun setUserData(user: User?) {
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
    }

    fun setUAddressData(uAddress: UAddress?) {
        val data = Gson().toJson(uAddress)
        editor!!.putString("getUAddressData", data)
        editor!!.commit()
    }

    fun getUAddressData(): UAddress? {
        val uAddress: UAddress
        val gson = Gson()
        val data = preferences!!.getString("getUAddressData", "")
        return if (data != null && data.isNotEmpty()) {
            uAddress = gson.fromJson(data, UAddress::class.java)
            uAddress
        } else null
    }

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
        editor?.remove("getUAddressData")
        editor!!.commit()
    }

    fun firstUser(): String? {
        return preferences!!.getString("user", "0")
    }

    fun storeInt(key: String?, value: Int) {
        editor?.putInt(key, value)
        editor!!.commit()
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return preferences!!.getInt(key, defaultValue)
    }

}