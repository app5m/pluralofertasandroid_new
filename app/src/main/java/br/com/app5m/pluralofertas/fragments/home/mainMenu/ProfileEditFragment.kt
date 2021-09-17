package br.com.app5m.pluralofertas.fragments.home.mainMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.activity.MainActivity
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
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_siginup.*

class ProfileEditFragment: Fragment(), WSResult {


    private lateinit var useful: MyUsefulKotlin
    private lateinit var userControl: UserControl
    private lateinit var uAddressControl: UAddressControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    private val user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = MyUsefulKotlin()
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        userControl = UserControl(requireContext(), this)

        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()

        userControl.listId()

    }

    override fun uResponse(list: List<User>, type: String) {

        val user = list[0]

        nomeCompletoMeuPerfilEditText.setText(user.name)
        emailMeuPerfilEditText.setText(user.email)
        cpfMeuPerfilEditText3.setText(user.cpf)

    }


    override fun error(error: String) {
        useful.closeLoading(alertDialog)
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun loadClicks() {

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