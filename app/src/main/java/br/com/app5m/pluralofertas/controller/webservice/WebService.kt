package br.com.app5m.pluralofertas.controller.webservice

import android.location.Address
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    //User

    @POST ("fvdsfdsfdsfvsf")
    fun fcm(@Body u: User): Call<List<User>>

    @POST("usuarios/login")
    fun login(@Body u: User): Call<List<User>>

    @POST("usuarios/save")
    fun register(@Body u: User): Call<List<User>>

    @POST("usuarios/listid/3")
    fun listId(@Body u: User): Call<List<User>>

    @POST("usuarios/updatepassword")
    fun updatePassword(@Body u: User): Call<List<User>>

    @POST("usuarios/update")
    fun updateUserData(@Body u: User): Call<List<User>>


    //Address

    @POST("usuarios/findenderecos/3")
    fun findAddress(@Body a: UAddress): Call<List<UAddress>>

    @POST("usuarios/saveendereco")
    fun saveAddress(@Body a: UAddress): Call<List<UAddress>>

    @POST("usuarios/findenderecoid/5")
    fun listIdAddress(@Body a: UAddress): Call<List<UAddress>>

    @POST("usuarios/updateendereco")
    fun updateAddressData(@Body a: UAddress): Call<List<UAddress>>


//    esse é direto na url , tem q ver como faz
    //Sales

//    @POST("usuarios/updateendereco")
//    fun updateAddressData(@Body a: UAddress): Call<List<UAddress>>



}