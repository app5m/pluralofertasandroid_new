package br.com.app5m.pluralofertas.controller.webservice

import br.com.app5m.pluralofertas.model.User
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    //User

    @POST ("cadastros/fcm")
    fun fcm(@Body u: User): Call<List<User>>

    @POST("cadastros/login")
    fun login(@Body u: User): Call<List<User>>

    @POST("cadastros/adicionar")
    fun register(@Body u: User): Call<List<User>>

    @POST("cadastros/listid")
    fun listId(@Body u: User): Call<List<User>>

    @POST("cadastros/updatepassword")
    fun updatePassword(@Body u: User): Call<List<User>>

    @POST("cadastros/updateuser")
    fun updateUserData(@Body u: User): Call<List<User>>



}