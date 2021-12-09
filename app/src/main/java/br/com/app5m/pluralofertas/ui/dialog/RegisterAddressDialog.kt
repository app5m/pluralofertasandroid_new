package br.com.app5m.pluralofertas.ui.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.CompileByCepControl
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Validation
import br.com.app5m.pluralofertas.model.UAddress
import kotlinx.android.synthetic.main.adapter_uaddress.*
import kotlinx.android.synthetic.main.dialog_register_address.*


class RegisterAddressDialog: DialogFragment() {

    private val TAG = "DialogRegisterAddress"

    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

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

        preferences = Preferences(requireContext())
        validation = Validation(requireContext())

        cep_edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (s.toString().length == 9) {

                    CompileByCepControl.compile(cep_edit.rawText, object :
                        CompileByCepControl.CepResult {
                        override fun resultCep(cep: UAddress) {

                            if (cep.cep == null) {
                                cep_edit.error = "Por favor, digite um CEP valido."
                                return
                            }
                            city_edit.setText(cep.place)
                            uf_edit.setText(cep.uf)
                            nbh_edit.setText(cep.neighborhood)
                            address_edit.setText(cep.logradouro)


                        }


                    })
                }
            }
        })

        save_btn.setOnClickListener {

            val uAddress = UAddress()

            if (!validation.validateTextField(cep_edit)) return@setOnClickListener
            if (!validation.validateTextField(city_edit)) return@setOnClickListener
            if (!validation.validateTextField(uf_edit)) return@setOnClickListener
            if (!validation.validateTextField(nbh_edit)) return@setOnClickListener
            if (!validation.validateTextField(address_edit)) return@setOnClickListener
            if (!validation.validateTextField(num_edit)) return@setOnClickListener

            uAddress.cep = cep_edit.text.toString()
            uAddress.city = city_edit.text.toString()
            uAddress.state = uf_edit.text.toString()
            uAddress.neighborhood = nbh_edit.text.toString()
            uAddress.address = address_edit.text.toString()
            uAddress.number = num_edit.text.toString()
            uAddress.complement = comp_edit.text.toString()

            preferences.clearUserData()
            preferences.setUAddressData(uAddress)

            onExit(true)

            dialog?.dismiss()
        }

        back_ib.setOnClickListener {
            dialog?.dismiss()

        }
    }

    private fun onExit(msg: Boolean){
        val intent = Intent()
        intent.putExtra("msg", msg)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }

}