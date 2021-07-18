package com.example.pluralofertasandroid2.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.productDetails.ProductDetailsActivity
import com.example.pluralofertasandroid2.adapter.HighlightsAdapter
import com.example.pluralofertasandroid2.adapter.ProductsAdapter
import com.example.pluralofertasandroid2.fragments.dialog.FilterDialog
import com.example.pluralofertasandroid2.fragments.dialog.MyadressesHomeDialog
import com.example.pluralofertasandroid2.helper.CircleRecyclerViewDecoration
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Highlight
import com.example.pluralofertasandroid2.model.Product
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_body.*
import java.util.*

class HomeFragmentOffer : Fragment(), RecyclerItemClickListener {
    private var productsList  = ArrayList<Product>()
    private lateinit var viewPhotoManager: RecyclerView.LayoutManager
    private lateinit var viewPhotoAdapter: RecyclerView.Adapter<*>
    private val highlightsList = ArrayList<Highlight>()
    private var highlight = Highlight()
    private  val TAG = "HomeFragmentOffer"
    private lateinit var viewHome: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        viewHome = inflater.inflate(R.layout.fragment_home, container, false)


        viewHome?.filterTv4.setOnClickListener {

            Log.d(TAG, "onClick: opening dialog")

            val dialog = FilterDialog()
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let { it1 -> dialog.show(it1,"FilterDialog") }

        }
        return viewHome
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
//            schedule = intent.getSerializableExtra("schedule") as Schedule
            if (/*photo.rows.equals("0")*/true){
                highlightsList.add(Highlight())
                highlightsList.add(Highlight())
                //container_rv.visibility = View.GONE
            }else{
                highlight.let {
                    //adiciona os valores do modelo (usado com request)
                    /*photoList.addAll(it)*/
                }
            }


            btnSwitchCity.setOnClickListener {
                Log.d(TAG, "onClick: opening dialog")

                val dialog = MyadressesHomeDialog()
                dialog.setTargetFragment(this, 1)
                fragmentManager?.let { it1 -> dialog.show(it1,"MyadressesHomeDialog") }
            }



        }

    }


    private fun configureInitialViews(){

        viewPhotoManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewPhotoAdapter = context?.let {
            HighlightsAdapter(it, highlightsList, this)
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

    override fun onClickListenerHighlights(highlight: Highlight) {
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra("highlight", highlight)
        startActivity(intent)

    }
}

