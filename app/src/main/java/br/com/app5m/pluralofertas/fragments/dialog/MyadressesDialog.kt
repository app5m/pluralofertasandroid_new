package br.com.app5m.pluralofertas.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.UAddressAdapter
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.UAddress
import kotlinx.android.synthetic.main.dialog_myadresses_home.*
import kotlinx.android.synthetic.main.fragment_myadresses.myAdressesRv
import java.util.ArrayList

class MyadressesDialog: DialogFragment(), RecyclerItemClickListener {
    private val TAG = "MyadressesDialog"
    private lateinit var viewFragment: View
    private var uaddressList  = ArrayList<UAddress>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_myadresses_home, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButtonHome.setOnClickListener {

            onbackpressed()
        }
        saveBnt.setOnClickListener {
            onbackpressed()
        }
        addAdressHomeFab.setOnClickListener {
            val dialog = RegisterAdressDialog()
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let { it1 -> dialog.show(it1,"DialogRegisterAdress") }
        }



        configureInitialViews()
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())
        uaddressList.add(UAddress())

    }

    private fun configureInitialViews(){

        val productsAdapter = UAddressAdapter(requireContext(),uaddressList,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        myAdressesRv.layoutManager = layoutManager
        myAdressesRv.adapter = productsAdapter
    }
    fun onbackpressed(){

        dialog?.dismiss()

    }


}