package br.com.app5m.pluralofertas.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.activity.HomeAct
import br.com.app5m.pluralofertas.controller.UAddressControl
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.ui.dialog.RegisterAddressDialog
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.RecyclerItemClickListener
import br.com.app5m.pluralofertas.util.Validation
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.ui.activity.DocumentPdfWebViewAct
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment(), RecyclerItemClickListener, WSResult {

    private  val TAG = "SiginUpFragment"

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
        return inflater.inflate(R.layout.fragment_signup, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        userControl = UserControl(requireContext(), this, useful)
        uAddressControl = UAddressControl(requireContext(), this, useful)

        loadClicks()

    }

    override fun uResponse(list: List<User>, type: String) {

        val user = list[0]

        useful.closeLoading()

        if (user.status.equals("01")) {
            preferences.setUserData(user)

            SingleToast.INSTANCE.show(requireContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG)
            registerCheck()
        } else {

            Toast.makeText(requireContext(), user.msg, Toast.LENGTH_SHORT).show()

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode2: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode2, data)

        if (data!!.extras!!.getBoolean("msg")) {
            useful.openLoading()


            user.cep = preferences.getUAddressData()!!.cep
            user.city = preferences.getUAddressData()!!.city
            user.state = preferences.getUAddressData()!!.state
            user.neighborhood = preferences.getUAddressData()!!.neighborhood
            user.address = preferences.getUAddressData()!!.address
            user.number = preferences.getUAddressData()!!.number
            user.complement = preferences.getUAddressData()!!.complement

            userControl.register(user)
        }


    }

    private fun loadClicks() {

        btnCriarConta.setOnClickListener {

            if (!validate()) return@setOnClickListener

            user.name = fullname_edittext.text.toString()
            user.email = email_edittext.text.toString()
            user.cpf = cpf_edittext.text.toString()
            user.cellphone = phone_edittext.text.toString()
            user.password = login_password_edittext.text.toString()
            user.birth = birth_edittext.text.toString()

            val dialog = RegisterAddressDialog()
            dialog.setTargetFragment(this, 1)
            dialog.show(parentFragmentManager,"DialogRegisterAddress")


        }

        terms_tv.setOnClickListener {
            startActivity(Intent(requireContext(), DocumentPdfWebViewAct::class.java ))
        }


    }

    private fun validate(): Boolean {
        return if (!validation.name(fullname_edittext)) false
        else if (!validation.email(email_edittext)) false
        else if (!validation.cpf(cpf_edittext)) false
        else if (!validation.date(birth_edittext)) false
        else if (!validation.cellphone(phone_edittext)) false
        else if (!validation.password(login_password_edittext, 0)) false
        else validation.coPassword(login_password_edittext, login_password2_edittext)
    }

    private fun registerCheck(){
        preferences.setLogin(true)

        val intent = Intent(requireContext(), HomeAct::class.java)
        startActivity(intent)

        activity?.finishAffinity()
    }
}