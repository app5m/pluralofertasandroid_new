package br.com.app5m.pluralofertas.controller.webservice

import android.location.Address
import br.com.app5m.pluralofertas.model.*
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    //User

    @POST ("usuarios/savefcm")
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

//    @FormUrlEncoded
//    @POST("user/ofertas/find?")
//    fun findSale(
//        @Query("destaque") destaque: String
//        , @Query("valor_de") valor_de: String
//        , @Query("valor_ate") valor_ate: String
//        , @Query("categoria") categoria: String
//        , @Body s: Sale
//    ): Call<List<Sale>>

    @POST("ofertas/find")
    fun findSale(@Body s: Sale): Call<List<Sale>>

    @POST("ofertas/listid/" + "{id}")
    fun listIdSale(
        @Path("id") id: String,
        @Body s: Sale
    ): Call<List<Sale>>

    @POST("ofertas/cupons/" + "{id}")
    fun listCoupons(
        @Path("id") id: String,
        @Body s: Sale
    ): Call<List<Sale>>

    //cart

    @POST("carrinho/additem")
    fun addItem(@Body c: Cart): Call<List<Cart>>

    @POST("carrinho/itenscarrinho/" + "{id}")
    fun listItems(
        @Path("id") id: String,
        @Body c: Cart
    ): Call<List<Cart>>

    @POST("carrinho/removeitem/" + "{id}")
    fun removeItem(
        @Path("id") id: String,
        @Body c: Cart
    ): Call<List<Cart>>

    @POST("carrinho/addcupom")
    fun addCoupon(@Body c: Cart): Call<List<Cart>>

    @POST("carrinho/carrinhoaberto")
    fun loadCart(@Body c: Cart): Call<List<Cart>>

    //freights

    @POST("frete/fretecorreios")
    fun estimateFreight(@Body f: Freight): Call<List<Freight>>

    //coupons

    @POST("cupons/lista/" + "{id}")
    fun listCoupons(
        @Path("id") id: String,
        @Body cp: Coupon
    ): Call<List<Coupon>>

    @POST("cupons/lista_usados/" + "{id}")
    fun usedListCoupons(
        @Path("id") id: String,
        @Body cp: Coupon
    ): Call<List<Coupon>>

    //request

    @POST("pedidos/novopedido")
    fun newRequest(@Body r: Request): Call<List<Request>>

    @POST("pedidos/find/" + "{id}")
    fun find(
        @Path("id") id: String,
        @Body r: Request
    ): Call<List<Request>>

    @POST("pedidos/findid/" + "{id}")
    fun findId(
        @Path("id") id: String,
        @Body r: Request
    ): Call<List<Request>>

    /*
     *Outros
     */
    @GET("{CEP}/json/")
    fun getAddressByCEP(@Path("CEP") CEP: String?): Call<UAddress>

}