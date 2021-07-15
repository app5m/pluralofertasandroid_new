package com.example.pluralofertasandroid2.fragments.offer

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.offerDetails.ProductDetailsActivity
import com.example.pluralofertasandroid2.adapter.HighlightsAdapter
import com.example.pluralofertasandroid2.adapter.ProductsAdapter
import com.example.pluralofertasandroid2.helper.CircleRecyclerViewDecoration
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Highlights
import com.example.pluralofertasandroid2.model.Product
import kotlinx.android.synthetic.main.home_body.*
import java.util.*

class HomeFragmentOffer : Fragment(), RecyclerItemClickListener {
    var recyclerProduct: RecyclerView? = null
    private var productsList  = ArrayList<Product>()
    private lateinit var viewPhotoManager: RecyclerView.LayoutManager
    private lateinit var viewPhotoAdapter: RecyclerView.Adapter<*>
    private val photoList = ArrayList<Highlights>()
    private var photo = Highlights()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
         return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        configureInitialViews()

        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())
        productsList.add(Product())





        if (/*intent.extras != null*/true) {

/*
            schedule = intent.getSerializableExtra("schedule") as Schedule
*/

            if (/*photo.rows.equals("0")*/true){
                photoList.add(Highlights())
                photoList.add(Highlights())

                //container_rv.visibility = View.GONE
            }else{
                photo.let {
                    //adiciona os valores do modelo (usado com request)
                    /*photoList.addAll(it)*/

                }
            }


        }


    }

    private fun configureInitialViews(){

        viewPhotoManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewPhotoAdapter = context?.let {
            HighlightsAdapter(it, photoList, this)
        }!!

        highlightsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewPhotoManager
            adapter = viewPhotoAdapter
        }

        highlightsRecycler.addItemDecoration(CircleRecyclerViewDecoration())

        val productsAdapter = ProductsAdapter(requireContext(),productsList,this)
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        homeContentRV.layoutManager = layoutManager
        homeContentRV.adapter = productsAdapter
    }

    override fun onClickListenerNews(product: Product) {

        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)

    }
}

