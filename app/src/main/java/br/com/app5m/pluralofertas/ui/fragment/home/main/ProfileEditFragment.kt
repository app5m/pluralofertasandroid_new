package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Validation
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class ProfileEditFragment: Fragment(), WSResult {


    private lateinit var useful: Useful
    private lateinit var userControl: UserControl
    private lateinit var uAddressControl: UAddressControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private val user = User()

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

        nomeCompletoMeuPerfilEditText.setText(user.name)
        emailMeuPerfilEditText.setText(user.email)
        cpfMeuPerfilEditText3.setText(user.cpf)

    }


    private fun loadClicks() {

        btnAlterarSenhaMeuPerfilTextView.setOnClickListener {

        }

        btnVoltarMeuPerfilTextView.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btnEditarPerfil.setOnClickListener {


/*        {
            "id": 3,
            "nome": "Cadastro teste",
            "email": "cadastro@cadastro.com",
            "cpf": "02737594006",
            "celular":"(51)9888888",
            "data_nascimento": "12/08/1991",
            "token": "plural_ofertas@2021"
        }*/

            val updateUser = User()

            userControl.updateUserData(updateUser)

        }

        btnAlterarSenhaMeuPerfilTextView.setOnClickListener {
            useful.startFragmentOnBack(UpdatePasswordFragment(), requireActivity().supportFragmentManager)
        }

    }
/*
    private fun validate(): Boolean {
        return if (!validation.name(fullname_edittext)) false
        else if (!validation.email(email_edittext)) false
        else if (!validation.cpf(cpf_edittext)) false
        else if (!validation.cellphone(phone_edittext)) false
        else if (!validation.password(login_password_edittext, 0)) false
        else validation.coPassword(login_password_edittext, login_password2_edittext)
    }*/


}