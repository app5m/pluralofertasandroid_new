package br.com.app5m.pluralofertas.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.CategoriesSearchAdapter
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Category
import kotlinx.android.synthetic.main.dialog_filter.*
import java.util.ArrayList

class FilterDialogCopy : DialogFragment(), RecyclerItemClickListener {
    private var categoriesList  = ArrayList<Category>()


    private val TAG = "FilterDialog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_filter, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterBnt.setOnClickListener {
            dialog?.dismiss();
        }
        backBnt.setOnClickListener {
            dialog?.dismiss();


            configureInitialViews()
            categoriesList.add(Category("veja"))
            categoriesList.add(Category("quero ver pegar em"))
            categoriesList.add(Category("brabooo"))
            categoriesList.add(Category("veja kk"))

        }


    }
    private fun configureInitialViews(){

        val categoriesSearchAdapter = CategoriesSearchAdapter(requireContext(),categoriesList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        categoriesRv.layoutManager = layoutManager
        categoriesRv.adapter = categoriesSearchAdapter
    }
}