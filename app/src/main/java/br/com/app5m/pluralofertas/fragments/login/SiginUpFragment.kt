package br.com.app5m.pluralofertas.fragments.login

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
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.fragments.dialog.RegisterAddressDialog
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.helper.Validation
import br.com.app5m.pluralofertas.model.UAddress
import br.com.app5m.pluralofertas.model.User
import kotlinx.android.synthetic.main.fragment_siginup.*

class SiginUpFragment : Fragment(), RecyclerItemClickListener, WSResult {

    private  val TAG = "SiginUpFragment"

    private lateinit var useful: MyUsefulKotlin
    private lateinit var preferences: Preferences
    private lateinit var userControl: UserControl
    private lateinit var validation: Validation

    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    private val user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_siginup, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = MyUsefulKotlin()
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        userControl = UserControl(requireContext(), this)

        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()

        loadClicks()

    }

    override fun uResponse(list: List<User>, type: String) {

        useful.closeLoading(alertDialog)

        val user = list[0]

        if (user.status.equals("01")) {
            preferences.setUserData(user)

            registerCheck()
        }

        Toast.makeText(context, user.msg, Toast.LENGTH_SHORT).show()

    }

    override fun uAResponse(list: List<UAddress>, type: String) {



    }

    override fun onClickListenerUAddress(uaddress: UAddress) {



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        useful.openLoading(requireContext(), alertDialog)

        userControl.register(user)

    }

    override fun error(error: String) {
        useful.closeLoading(alertDialog)
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun loadClicks() {

        btnCriarConta.setOnClickListener {

            if (!validate()) return@setOnClickListener

            user.name = fullname_edittext.text.toString()
            user.email = email_edittext.text.toString()
            user.cpf = cpf_edittext.text.toString()
            user.phone = phone_edittext.text.toString()
            user.password = login_password_edittext.text.toString()

            val dialog = RegisterAddressDialog()
            dialog.show(this.parentFragmentManager,"DialogRegisterAddress")


        }

    }

    private fun validate(): Boolean {
        return if (!validation.name(fullname_edittext)) false
        else if (!validation.email(email_edittext)) false
        else if (!validation.cpf(cpf_edittext)) false
        else if (!validation.cellphone(phone_edittext)) false
        else if (!validation.password(login_password_edittext, 0)) false
        else validation.coPassword(login_password_edittext, login_password2_edittext)
    }

    private fun registerCheck(){
        preferences.setLogin(true)

        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

        activity?.finishAffinity()
    }
}