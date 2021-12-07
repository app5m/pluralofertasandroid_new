package br.com.app5m.pluralofertas.fragment.dialog

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.CategoriesSearchAdapter
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Category
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.dialog_filter.view.*
import java.util.*

class FilterDialog : DialogFragment(), RecyclerItemClickListener,AdapterView.OnItemSelectedListener {
    private var categoriesList = ArrayList<Category>()
    private lateinit var rootView: View
    var spinner: Spinner? = null
    var textView_msg: TextView? = null


    var list_of_items = arrayOf("5 Km", "10 Km", "15 Km","30 km", "50 Km" , "100 Km")
    private val TAG = "FilterDialog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme) }

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

/*
        textView_msg = this.msg
*/

        spinner = this.mySpinner
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, list_of_items) }
        // Set layout to use when the list of choices appear

        if (aa != null) {
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        filterBnt.setOnClickListener {
            dialog?.dismiss()
        }
        backBnt.setOnClickListener {
            dialog?.dismiss()


        }
        configureInitialViews()


    }

    private fun configureInitialViews() {
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


        val categoriesSearchAdapter =
            CategoriesSearchAdapter(requireContext(), categoriesList, this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        categoriesRv.layoutManager = layoutManager
        categoriesRv.adapter = categoriesSearchAdapter
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
/*
        textView_msg!!.text = "Selected : " + list_of_items[position]
*/
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {


    }
}