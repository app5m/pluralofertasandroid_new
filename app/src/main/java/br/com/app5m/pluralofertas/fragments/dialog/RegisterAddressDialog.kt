package br.com.app5m.pluralofertas.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.model.UAddress
import kotlinx.android.synthetic.main.adapter_uaddress.*
import kotlinx.android.synthetic.main.dialog_register_address.*


class RegisterAddressDialog: DialogFragment() {

    private val TAG = "DialogRegisterAddress"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_register_address, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_btn.setOnClickListener {

            val uAddress = UAddress()

            uAddress.cep = cep_edit.text.toString()
            uAddress.city = city_edit.text.toString()
            uAddress.state = uf_edit.text.toString()
            uAddress.neighborhood = nbh_edit.text.toString()
            uAddress.address = address_edit.text.toString()
            uAddress.number = num_edit.text.toString()
            uAddress.complement = comp_edit.text.toString()

            dialog?.dismiss()
        }

        btnBackLoginButton.setOnClickListener {
            dialog?.dismiss()

        }
    }
}