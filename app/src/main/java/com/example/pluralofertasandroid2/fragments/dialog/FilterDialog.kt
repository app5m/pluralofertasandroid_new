package com.example.pluralofertasandroid2.fragments.dialog

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.adapter.CategoriesSearchAdapter
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.example.pluralofertasandroid2.model.Category
import com.example.pluralofertasandroid2.model.Product
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.dialog_filter.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.*

class FilterDialog : DialogFragment(), RecyclerItemClickListener {
    private var categoriesList  = ArrayList<Category>()
    private lateinit var rootView: View


    private val TAG = "FilterDialog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.AppTheme)

        //Listagem de filmes

        //Listagem de filmes

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.dialog_filter, container)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        filterBnt.setOnClickListener {
            dialog?.dismiss();
        }
        backBnt.setOnClickListener {
            dialog?.dismiss();



        }
        configureInitialViews()



    }
    private fun configureInitialViews(){
        //Configura a range bar

            rootView?.rangeBarFiltro?.setRange(0.00f, 2000.00f)
            rootView?.rangeBarFiltro?.setProgress(0f, 2000f)
            rootView?.rangeBarFiltro?.setIndicatorTextDecimalFormat("0")
            rootView?.rangeBarFiltro?.setIndicatorTextStringFormat("R$%s")
            rootView?.rangeBarFiltro?.leftSeekBar.apply {
                this!!.setTypeface(
                    Typeface.createFromAsset(
                        requireContext().applicationContext.assets,
                        "uni_neue_bold_filtro.ttf"
                    )
                )
            }
            rootView?.rangeBarFiltro?.rightSeekBar.apply {
                this!!.setTypeface(
                    Typeface.createFromAsset(
                        requireContext().applicationContext.assets,
                        "uni_neue_bold_filtro.ttf"
                    )
                )
                setIndicatorTextStringFormat("R$%s+")
            }



        val categoriesSearchAdapter = CategoriesSearchAdapter(requireContext(),categoriesList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        categoriesRv.layoutManager = layoutManager
        categoriesRv.adapter = categoriesSearchAdapter
    }


}