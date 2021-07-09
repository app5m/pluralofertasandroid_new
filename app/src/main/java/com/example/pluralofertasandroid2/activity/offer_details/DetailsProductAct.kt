package com.example.pluralofertasandroid2.activity.offer_details


import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.app_bar.*
import java.lang.Exception


class DetailsProductAct : AppCompatActivity(), RecyclerItemClickListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)

        getOfferDetails()
        //toolbar
       setSupportActionBar(toolbar)
        supportActionBar?.let { MyUsefulKotlin().setActionBar(this, it,"") }

        toolbar.visibility = View.VISIBLE



    }
    //Recebe os detalhes da oferta
    fun getOfferDetails() {
        viewModel.performOfferDetails(args.idCoupom)?.observe(viewLifecycleOwner, Observer {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}