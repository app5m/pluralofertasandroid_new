package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.UAddressAdapter
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.ui.dialog.RegisterAddressDialog
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Validation
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class MyAddressesFragment : Fragment(), WSResult, RecyclerItemClickListener {


    private lateinit var useful: Useful
    private lateinit var uAddressControl: UAddressControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private var uaddressList  = ArrayList<UAddress>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view

        return inflater.inflate(R.layout.fragment_myaddresses, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        uAddressControl = UAddressControl(requireContext(), this, useful)

        useful.openLoading()
        uAddressControl.findAddress()

        configureInitialViews()


    }


    @SuppressLint("NotifyDataSetChanged")
    override fun uAResponse(list: List<UAddress>, type: String) {

        useful.closeLoading()

        if (type == "findAddress") {

            uaddressList.clear()
            uaddressList.addAll(list)

            myAdressesRv.adapter!!.notifyDataSetChanged()

        } else {

            useful.openLoading()
            uAddressControl.findAddress()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode2: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode2, data)

        useful.openLoading()

        if (data!!.extras!!.getBoolean("msg")) {

            if (requestCode == 1) {
                preferences.getUAddressData()?.let { uAddressControl.saveAddress(it) }
            } else {
                preferences.getUAddressData()?.let { uAddressControl.updateAddress(it) }
            }

        }
    }

    override fun onClickListenerUAddress(uaddress: UAddress) {

        preferences.setUAddressData(uaddress)

        val dialog = RegisterAddressDialog()
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager,"DialogRegisterAddress")

    }


    private fun configureInitialViews(){

        val uAddressAdapter = UAddressAdapter(requireContext(), uaddressList, useful, this, this)

        myAdressesRv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = uAddressAdapter
        }

        loadClicks()

    }

    private fun loadClicks() {

        addAddressMainMenuFab.setOnClickListener {

            preferences.clearUAddressData()

            val dialog = RegisterAddressDialog()
            dialog.setTargetFragment(this, 1)
            dialog.show(parentFragmentManager,"DialogRegisterAddress")
        }

    }


}
