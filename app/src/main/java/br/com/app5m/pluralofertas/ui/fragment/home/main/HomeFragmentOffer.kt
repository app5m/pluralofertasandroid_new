package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.activity.SaleDetailsActivity
import br.com.app5m.pluralofertas.adapter.SalesAdapter
import br.com.app5m.pluralofertas.controller.SaleControl
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Sale
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Useful
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.home_body.*
import java.util.*

class HomeFragmentOffer : Fragment(), RecyclerItemClickListener, WSResult {

    private var saleList = ArrayList<Sale>()

    private lateinit var useful: Useful
    private lateinit var saleControl: SaleControl
    private lateinit var uAddressControl: UAddressControl

    private lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = Preferences(requireContext())
        useful = Useful(requireContext())
        saleControl = SaleControl(requireContext(), this, useful)
        uAddressControl = UAddressControl(requireContext(), this, useful)

        useful.openLoading()

        if (preferences.getLogin()) {
            uAddressControl.findAddress()
        } else {
            saleControl.findSale(null)
        }


        configureInitialViews()

    }

    override fun onClickListenerSale(sale: Sale) {

        val intent = Intent(activity, SaleDetailsActivity::class.java)
        intent.putExtra("idSale", sale.id)
        startActivity(intent)

    }


    override fun uAResponse(list: List<UAddress>, type: String) {

        val responseInfo = list

        address_sp.visibility = View.VISIBLE

        val spinnerArrayGroup: MutableList<String?> = ArrayList()

        if (preferences.getUserLocation() != null) {
            if (preferences.getUserLocation()!!.latitude != null
                && preferences.getUserLocation()!!.longitude != null
            ) {
                spinnerArrayGroup.add("Localização atual")
            }
        }

        if (!responseInfo[0].rows.equals("0")) {
            for (i in responseInfo.indices) {
                spinnerArrayGroup.add(
                    responseInfo[i].city.toString() + ", " + responseInfo[i].neighborhood
                )
            }
        }

        val adapter: ArrayAdapter<*> = ArrayAdapter<String?>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerArrayGroup)

        address_sp.adapter = adapter

        address_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

                useful.openLoading()

                    when (position) {
                        0 -> {
                            saleControl.findSale(null)
                        }
                        else -> {

                            saleControl.findSale(responseInfo[position].id)
                        }
                    }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun sResponse(list: List<Sale>, type: String) {

        useful.closeLoading()

        saleList.clear()
        saleList.addAll(list)

        salesRv.adapter!!.notifyDataSetChanged()

    }

    private fun configureInitialViews(){

        val salesAdapter = SalesAdapter(requireContext(), saleList,this)

        salesRv.apply {
            setHasFixedSize(false)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = salesAdapter
        }


        filtrar_btn.setOnClickListener {

            openFilterDialog()

        }

        search_btn.setOnClickListener {


            saleControl.findSaleByName(search_et.text.toString())
        }
    }

    private fun openFilterDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppTheme_SheetDialog)
        val bottomSheetView: View = LayoutInflater.from(context).inflate(
            R.layout.bottom_dialog_filter,
            requireActivity().findViewById(R.id.sheet_container)
        )
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()

        val categoryBtn = bottomSheetView.findViewById<Button>(R.id.category_btn)
        val valueBtn = bottomSheetView.findViewById<Button>(R.id.value_btn)

        categoryBtn.setOnClickListener {

            bottomSheetDialog.dismiss()
        }

        valueBtn .setOnClickListener {

            bottomSheetDialog.dismiss()
        }
    }


}

