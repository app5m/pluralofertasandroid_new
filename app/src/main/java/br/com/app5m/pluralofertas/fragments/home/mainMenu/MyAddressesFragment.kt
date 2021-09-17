package br.com.app5m.pluralofertas.fragments.home.mainMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.UAddressAdapter
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.fragments.dialog.RegisterAddressDialog
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.helper.Validation
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_mainmenu.*
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class MyAddressesFragment : Fragment(), WSResult {


    private lateinit var useful: MyUsefulKotlin
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

        useful = MyUsefulKotlin()
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        uAddressControl = UAddressControl(requireContext(), this)

        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()

        configureInitialViews()


    }


    override fun uAResponse(list: List<UAddress>, type: String) {

        useful.closeLoading(alertDialog)


    }

    override fun error(error: String) {
        useful.closeLoading(alertDialog)
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode2: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode2, data)

        if (data!!.extras!!.getBoolean("msg")) {
            useful.openLoading(requireContext(), alertDialog)
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
