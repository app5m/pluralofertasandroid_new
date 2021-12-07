package br.com.app5m.pluralofertas.fragment.home.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.UAddressAdapter
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.fragment.dialog.RegisterAddressDialog
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.helper.Validation
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.helper.Useful
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class MyAddressesFragment : Fragment(), WSResult {


    private lateinit var useful: Useful
    private lateinit var uAddressControl: UAddressControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    private val uAddress = UAddress()

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

        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()

        configureInitialViews()


    }


    override fun uAResponse(list: List<UAddress>, type: String) {

        useful.closeLoading()


    }


    override fun onActivityResult(requestCode: Int, resultCode2: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode2, data)

        if (data!!.extras!!.getBoolean("msg")) {
            useful.openLoading()
            preferences.getUAddressData()?.let { uAddressControl.updateAddress(it) }
        }


    }


    private fun configureInitialViews(){

        val productsAdapter = UAddressAdapter(requireContext(), uaddressList, alertDialog)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        myAdressesRv.layoutManager = layoutManager
        myAdressesRv.adapter = productsAdapter

        loadClicks()

    }

    private fun loadClicks() {

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        saveBnt2.setOnClickListener {

        }

        addAdressMainMenuFab.setOnClickListener {
            val dialog = RegisterAddressDialog()
            dialog.setTargetFragment(this, 1)
            dialog.show(parentFragmentManager,"DialogRegisterAddress")
        }

    }


}
