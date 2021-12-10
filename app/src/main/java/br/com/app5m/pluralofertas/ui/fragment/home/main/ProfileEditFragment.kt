package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Validation
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.birth_edittext
import kotlinx.android.synthetic.main.fragment_edit_profile.phone_edittext
import kotlinx.android.synthetic.main.fragment_signup.*

class ProfileEditFragment: Fragment(), WSResult {


    private lateinit var useful: Useful
    private lateinit var userControl: UserControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        userControl = UserControl(requireContext(), this, useful)

        userControl.listId()

        loadClicks()

    }

    override fun uResponse(list: List<User>, type: String) {

        val user = list[0]

        if (user.status == "01") {
            SingleToast.INSTANCE.show(requireContext(), user.msg!!, Toast.LENGTH_LONG)

            userControl.listId()
        } else {
            nomeCompletoMeuPerfilEditText.setText(user.name)
            phone_edittext.setText(user.cellphone)
            cpfMeuPerfilEditText3.setText(user.cpf)
            emailMeuPerfilEditText.setText(user.email)
            birth_edittext.setText(user.birth)
        }


    }


    private fun loadClicks() {

        btnAlterarSenhaMeuPerfilTextView.setOnClickListener {
            useful.startFragmentOnBack(UpdatePasswordFragment(), requireActivity().supportFragmentManager)
        }


        update_bt.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val updateUser = User()

            updateUser.name = nomeCompletoMeuPerfilEditText.text.toString()
            updateUser.cellphone = phone_edittext.text.toString()
            updateUser.cpf = cpfMeuPerfilEditText3.text.toString()
            updateUser.birth = birth_edittext.text.toString()


            userControl.updateUserData(updateUser)

        }

        btnAlterarSenhaMeuPerfilTextView.setOnClickListener {
            useful.startFragmentOnBack(UpdatePasswordFragment(), requireActivity().supportFragmentManager)
        }

    }

    private fun validate(): Boolean {
        return if (!validation.name(nomeCompletoMeuPerfilEditText)) false
        else if (!validation.cellphone(phone_edittext)) false
        else if (!validation.cpf(cpfMeuPerfilEditText3)) false
        else (validation.date(birth_edittext))
    }


}