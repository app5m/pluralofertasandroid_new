package com.example.pluralofertasandroid2.fragments.offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody

class HomeFragmentOfferViewModel(
/*
    private val offerService: OfferServiceInterface = OfferServiceRest()
*/
) : ViewModel() {

    data class HomeViewState(
        var titleContains: String? = null,
        var cateogryId: ArrayList<String> = ArrayList(),
        var minPrice: String? = null,
        var maxPrice: String? = null
    )

    private val _homeViewState: MutableLiveData<HomeViewState> = MutableLiveData(
        HomeViewState()
    )

    val homeViewState: LiveData<HomeViewState>
        get() = _homeViewState
/*

    val homeDataSource = HomeDataSource()

    val homeDataSourcerFilter = HomeDataSourceFilter()
*/

    fun restartOffer() {
        //Usser observe once aqui?
/*
            homeDataSource.loadInitialItems()
*/
    }

    fun restartOfferFilter() {

        //Usser observe once aqui?
/*
        homeDataSourcerFilter.loadInitialItems()
*/
    }


    fun updateFilter(
        titleContains: String?,
        categoryId: ArrayList<String>,
        minPrice: String?,
        maxPrice: Int?
    ) {
      /*  homeDataSourcerFilter.apply {
            this.titleContains = titleContains
            this.categoryId = categoryId
            this.minPrice = minPrice

            if(maxPrice!! > 1999){
                this.maxPrice = 1000000.toString()
            }else{
                this.maxPrice = maxPrice.toString()
            }
        }*/
    }

    //Busca sem paginação
    //Ofertas sem filtros - apenas com ID da cidade
    /*fun performOffer(cityId: String): LiveData<ServiceResponse<ArrayList<OfferResponse>>>? {
        return offerService.offers(cityId, false)
    }*/

    //Filtros
    /*fun performFilterOffer(
        cityId: String,
        titleContains: String,
        category: ArrayList<String>,
        minPrice: String,
        maxPrice: String
    ): LiveData<ServiceResponse<ArrayList<OfferResponse>>>? {
        return offerService.filterOffers(cityId, titleContains, category, minPrice, maxPrice, false)
    }*/
//
//    fun performHighlight(cityId: String): LiveData<ServiceResponse<ArrayList<OfferResponse>>> {
//        return offerService.offersHighlight(true, cityId, true)
//    }

   /* fun performAddFavorite(
        offerId: String
    ): LiveData<ServiceResponse<ResponseBody>>? {

        var body = bookmarkedoBody(offerId)

        return offerService.addFavorite(body)
    }

    fun performRemoveFavorite(offerId: String): LiveData<ServiceResponse<Unit>>? {

        return offerService.removeFavorite(RemoveFavoriteRequest(offerId = offerId))
    }

    fun performBookmarkedId(offerId: String): LiveData<ServiceResponse<ArrayList<BookmarkedOfferIdResponse>>>?{
        return offerService.bookmarkedId(offerId = offerId,
            userI = SharedPreferencesManager.getString(PREFS_USER_ID,"")!!)
    }

    //Lista de favoritos do usuário
    fun performFavorites(): LiveData<ServiceResponse<ArrayList<bookmarkedoOffersResponse>>>? {
        return offerService.getFavorite()
    }*/

}