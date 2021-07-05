package com.example.pluralofertasandroid2.fragment.offer

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.graycompany.pluralofertas.R
import com.graycompany.pluralofertas.adapter.*
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.pluralofertasandroid2.CustomTitleFragment
import com.example.pluralofertasandroid2.R
import com.graycompany.pluralofertas.GlideApp
import com.graycompany.pluralofertas.MemoryKeys
import com.graycompany.pluralofertas.SharedPreferencesManager
import com.graycompany.pluralofertas.classes.business_logic.filter.models.OfferCategoryResponse
import com.graycompany.pluralofertas.classes.business_logic.offer.models.OfferResponseList
import com.graycompany.pluralofertas.fragments.CustomTitleFragment
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_COUNT_FILTER
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_FILTER_ON
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_LIST_CATEGORY
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_MAX_PRICE
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_MIN_PRICE
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_SEARCH
import com.graycompany.pluralofertas.fragments.PluralConstants.PREFS_USER_LOGGED
import kotlinx.android.synthetic.main.cell_destaque.*
import kotlinx.android.synthetic.main.cell_home_sem_internet.view.*
import kotlinx.android.synthetic.main.fragment_home.view.layoutHomeSemInternet
import kotlinx.android.synthetic.main.fragment_layout_home_itens_na_rolagem.*
import kotlinx.android.synthetic.main.fragment_lista_cupons.*
import kotlinx.android.synthetic.main.home_body.*
import kotlinx.android.synthetic.main.home_body.view.*
import kotlinx.android.synthetic.main.menu_scrolling.view.*
import kotlin.collections.ArrayList

class HomeFragment : CustomTitleFragment() {

    private lateinit var viewHome: View
    private lateinit var viewModel: HomeFragmentOfferViewModel
    override var title: String = ""
    override var toolbarTint: ToolbarTint =
        ToolbarTint.NONE

    //private lateinit var adapterCupons: CouponsAdapter
    private lateinit var headerPager: SectionsPagerAdapter

    var idCity: String = SharedPreferencesManager.getString(MemoryKeys.ID_CITY.key, "")!!
    var minPrice = SharedPreferencesManager.getInt(PREFS_MIN_PRICE, 0)
    var maxPrice = SharedPreferencesManager.getInt(PREFS_MAX_PRICE, 2000)
    var idCategories: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view

        viewHome = inflater.inflate(R.layout.fragment_home, container, false)

        if (SharedPreferencesManager.getArrayListofCategories(PREFS_LIST_CATEGORY)
                .isNullOrEmpty()
        ) {

            var list: ArrayList<OfferCategoryResponse> = ArrayList()

            SharedPreferencesManager.saveArrayListofCategories(PREFS_LIST_CATEGORY, list)

        }

        /*
        //inicializa adaptador para recyclerView
        adapterCupons = CouponsAdapter(
            childFragmentManager,
            requireContext(),
            this@HomeFragment,
            ::onFavorite

        )*/

        selecionaLayout()

        //Botão de região sem internet - Botão com internet no adapter da recycler view
        viewHome?.btnSwitchCityHomeSemInternet?.setOnClickListener {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToSelecionarEstado(
                        true
                    )
                )
        }

        //Botão de filtro sem internet - Botão com internet no adapter da recycler view
        viewHome.filtroTvHomeSemInternet.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFiltroFragment()
            )
        }

        //generateData()

        return viewHome
    }//end view


    //Testa se há conexão com a internet
    fun temInternet(): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        return isConnected
    }


    fun carregaCupons() {
/*
        viewHome?.homeContentRV?.apply {
            adapter = adapterCupons
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            /*gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position == 0 || position == 1) return 2
                    return 1
                }*/

            /*gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position == 0) return 2
                    return 1
                }
            }*/
            layoutManager = gridLayoutManager
        }
*/
    }

    //Decidi se inicia a tela com ou sem a mensagem de "sem internet"
    fun selecionaLayout() {
        if (temInternet()) {
            viewHome?.layoutHomeSemInternet.visibility = View.GONE
            viewHome?.homeContentRV.visibility = View.VISIBLE

            viewHome?.btnSwitchCity.apply {
                setOnClickListener {
                    findNavController()
                        .navigate(HomeFragmentDirections.actionHomeFragmentToSelecionarEstado(true))
                }

                text = SharedPreferencesManager.getString(MemoryKeys.NAME_CITY.key, "")

            }



            if (SharedPreferencesManager.getInt(PREFS_COUNT_FILTER, 0) > 0) {
                viewHome?.filtroTv.visibility = View.GONE
                viewHome?.filtroAtivoTv.visibility = View.VISIBLE
                viewHome?.filtroAtivoQuantidadeTv.visibility = View.VISIBLE
                viewHome?.filtroAtivoQuantidadeTv.text =
                    SharedPreferencesManager.getInt(PREFS_COUNT_FILTER, 0).toString()
            }

            viewHome?.filtroTv.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFiltroFragment()
                )
            }

            viewHome?.filtroAtivoTv.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFiltroFragment()
                )
            }

            viewHome?.filtroAtivoQuantidadeTv.setOnClickListener {

                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFiltroFragment()
                )

            }

        } else {
            viewHome?.layoutHomeSemInternet?.visibility = View.VISIBLE
            viewHome?.homeContentRV?.visibility = View.GONE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeFragmentOfferViewModel::class.java)

        headerPager = SectionsPagerAdapter(childFragmentManager, ::onFavoriteHeader)

        viewPagerHomeFragment.adapter = headerPager

        //Configurações da recycler view
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        homeContentRV.layoutManager = gridLayoutManager

        for (i in SharedPreferencesManager.getArrayListofCategories(PREFS_LIST_CATEGORY)!!) {
            idCategories.add(i.id!!)
        }

        //Guarda o valor que está sendo usando no "buscar" no campo
        btnHomeSearchBar.setText(SharedPreferencesManager.getString(PREFS_SEARCH, ""))

        //Se já tem algum valor de pesquisa ao abrir a tela, esconde lupa e mostra close
        if (!SharedPreferencesManager.getString(PREFS_SEARCH, "").isNullOrEmpty()) {
            btnHomeSearchBar.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            ) // congigura valores de drawable - tiro a lupa
            btnCloseSearch.visibility = View.VISIBLE
        }

        //Botão de busca - ouvinte do "enter" do teclado
        btnHomeSearchBar.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                SharedPreferencesManager.putString(PREFS_SEARCH, btnHomeSearchBar.text.toString())

                btnHomeSearchBar.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    null,
                    null
                ) // congigura valores de drawable - tira a lupa
                btnCloseSearch.visibility = View.VISIBLE

                btnHomeSearchBar.hideKeyboard()

                listOffers()

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        //Botão de close da search
        btnCloseSearch.setOnClickListener {
            SharedPreferencesManager.putString(PREFS_SEARCH, "")
            btnHomeSearchBar.setText("")
            btnHomeSearchBar.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                getContext()?.getResources()?.getDrawable(R.drawable.simbolo62_14),
                null
            ) // congigura valores de drawable - add lupa
            btnCloseSearch.visibility = View.GONE
            listOffers()

        }

        getHeaderHighlight()
        listOffers()

        //Botão testa novamente a internet
        viewHome.tenteNovamenteHomeSemInternet.setOnClickListener {
            if (temInternet()) {
                selecionaLayout()
                getHeaderHighlight()
                listOffers()
            } else {
                Toast.makeText(requireContext(), "Falha ao conectar", Toast.LENGTH_SHORT).show()
            }
        }

    }

    //Adiciona/Remove dos favoritos
    private fun onFavorite(offerId: String, bookmarked: Boolean) {

        if (isUserLogged()) {

            //Caso o usuário já tenha como favorito
            if (bookmarked) {

                removeFavorite(offerId)

            } else {

                addFavorite(offerId)

            }

        } else {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }

    private fun addFavorite(offerId: String) {

        Log.e("offerIdHOme", "${offerId}")

        viewModel.performAddFavorite(offerId)?.observe(viewLifecycleOwner, Observer {
            if (it.error?.message.isNullOrEmpty()) {
                GlideApp.with(favoritoOfferBtn.context)
                    .load(R.drawable.btn_coracao_vermelho_favoritos)
                    .into(favoritoOfferBtn)
                listOffers()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Falha ao adicionar aos favoritos",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }

    private fun removeFavorite(offerId: String) {

        viewModel.performRemoveFavorite(offerId)?.observe(viewLifecycleOwner, Observer {
            if (it.error?.message.isNullOrEmpty()) {
                GlideApp.with(favoritoOfferBtn.context)
                    .load(R.drawable.coracao)
                    .into(favoritoOfferBtn)
                listOffers()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Falha ao remover dos favoritos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun isUserLogged(): Boolean {
        return SharedPreferencesManager.getBoolean(PREFS_USER_LOGGED, false)
    }

    //Carrega feed de acordo com o uso (ou não) do filtro
    private fun listOffers() {

        //Com paginação
        if (SharedPreferencesManager.getBoolean(
                PREFS_FILTER_ON,
                false
            ) || !SharedPreferencesManager.getString(
                PREFS_SEARCH, ""
            ).isNullOrEmpty()
        ) {
            viewModel.updateFilter(
                btnHomeSearchBar.text.toString(),
                idCategories,
                minPrice.toString(),
                maxPrice
            )
            viewModel.restartOfferFilter()
            viewModel.homeDataSourcerFilter.observe(viewLifecycleOwner, Observer {
                if (!it.items.isNullOrEmpty()) {
                    homeContentRV.adapter =
                        HomeCouponsPaginationAdapter(requireContext(), ::onFavorite).apply {
                            dataSource = viewModel.homeDataSourcerFilter
                        }
                    (homeContentRV.adapter as HomeCouponsPaginationAdapter).submitList(it.items)

                }

                try {
                    if(it.items?.size == 0){
                        Toast.makeText(requireContext(),"Não foram encontrados resultados para a busca",Toast.LENGTH_SHORT).show()

                        homeContentRV.adapter =
                            HomeCouponsPaginationAdapter(requireContext(), ::onFavorite).apply {
                                dataSource = viewModel.homeDataSourcerFilter
                            }
                        (homeContentRV.adapter as HomeCouponsPaginationAdapter).submitList(it.items)

                    }
                }catch (e: Exception){

                }

            })

        } else {
            viewModel.restartOffer()
            viewModel.homeDataSource.observe(viewLifecycleOwner, Observer {
                if (!it.items.isNullOrEmpty()) {
                    homeContentRV.adapter =
                        HomeCouponsPaginationAdapter(requireContext(), ::onFavorite).apply {
                            dataSource = viewModel.homeDataSource
                        }
                    (homeContentRV.adapter as HomeCouponsPaginationAdapter).submitList(it.items)
                }
            })
        }

        //Sem paginação
        /*if (SharedPreferencesManager.getBoolean(
                PREFS_FILTER_ON,
                false
            ) || !SharedPreferencesManager.getString(
                PREFS_SEARCH, ""
            ).isNullOrEmpty()
        ) {
            viewModel.performFilterOffer(
                idCity,
                btnHomeSearchBar.text.toString(),
                idCategories,
                minPrice.toString(),
                maxPrice.toString()
            )?.observe(viewLifecycleOwner, Observer {
                adapterCupons.submitList(it.data!!)
                loadHome.visibility = View.GONE

            })
        } else {
            viewModel.performOffer(idCity)
                ?.observe(viewLifecycleOwner, Observer {
                    adapterCupons.submitList(it.data!!)
                    loadHome.visibility = View.GONE
                })
        }*/

    }

    //Usa uma view e chama o método pra fechar o teclado
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    //Carrega os destaques
    fun getHeaderHighlight() {
        viewModel.performHighlight(idCity)?.observe(viewLifecycleOwner, Observer {

            if (it.error?.message.isNullOrEmpty()) {
                var offerResponseList = OfferResponseList(listOffer = it.data!!)

                headerPager.submitList(offerResponseList)

                if (it.data?.size > 1) {
                    circleIndicatorHomeFragment.setViewPager(viewHome?.viewPagerHomeFragment)
                }
            }
        })
    }

    //Adiciona/Remove dos favoritos do destaque
    private fun onFavoriteHeader(offerId: String, bookmarked: Boolean) {

        if (isUserLogged()) {

            //Caso o usuário já tenha como favorito
            if (bookmarked) {

                removeFavoriteHeader(offerId)

            } else {

                addFavoriteHeader(offerId)

            }

        } else {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }

    private fun addFavoriteHeader(offerId: String) {

        viewModel.performAddFavorite(offerId)
            ?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it.error?.message.isNullOrEmpty()) {

                    GlideApp.with(favoritoImage.context)
                        .load(R.drawable.btn_coracao_vermelho_favoritos)
                        .into(favoritoImage)
                    getHeaderHighlight()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Falha ao adicionar aos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    private fun removeFavoriteHeader(offerId: String) {

        viewModel.performRemoveFavorite(offerId)
            ?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it.error?.message.isNullOrEmpty()) {
                    GlideApp.with(favoritoImage.context)
                        .load(R.drawable.coracao)
                        .into(favoritoImage)
                    getHeaderHighlight()

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Falha ao remover dos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }


}
