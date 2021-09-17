package br.com.app5m.pluralofertas.controller.webservice

import android.location.Address
import br.com.app5m.pluralofertas.model.Sale
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

    @POST("usuarios/listid/" + "{id}")
    fun listId(
        @Path("id") idUser: String,
        @Body u: User
    ): Call<List<User>>

    @POST("usuarios/updatepassword")
    fun updatePassword(@Body u: User): Call<List<User>>

    @POST("usuarios/update")
    fun updateUserData(@Body u: User): Call<List<User>>


    //Address

    @POST("usuarios/findenderecos/" + "{id}")
    fun findAddress(
        @Path("id") idUAddress: String,
        @Body a: UAddress
    ): Call<List<UAddress>>

    @POST("usuarios/saveendereco")
    fun saveAddress(@Body a: UAddress): Call<List<UAddress>>

    @POST("usuarios/findenderecoid/" + "{id}")
    fun listIdAddress(
        @Path("id") idUser: String,
        @Body a: UAddress
    ): Call<List<UAddress>>

    @POST("usuarios/updateendereco")
    fun updateAddressData(@Body a: UAddress): Call<List<UAddress>>


    //sales

//    esse é direto na url , tem q ver como faz
    //Sales

    @FormUrlEncoded
    @POST("user/ofertas/find?")
    fun findSale(
        @Query("destaque") destaque: String
        , @Query("valor_de") valor_de: String
        , @Query("valor_ate") valor_ate: String
        , @Query("categoria") categoria: String
        , @Body s: Sale
    ): Call<List<Sale>>


//    https://app5m.com.br/iusui1872a5a78512rew/pluralofertas/apiv3/
    // user/ofertas/find?destaque=1&valor_de=R$180,00&valor_ate=R$190,00&categoria=1

    /*
     *Outros
     */

    /*
     *Outros
     */
    @GET("{CEP}/json/")
    fun getAddressByCEP(@Path("CEP") CEP: String?): Call<UAddress>

}