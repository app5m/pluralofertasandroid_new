package br.com.app5m.pluralofertas.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.activity.HomeAct
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.helper.Validation
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.helper.Useful
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*

class LoginFragment: Fragment(), RecyclerItemClickListener, WSResult {

    private lateinit var useful: Useful
    private lateinit var userControl: UserControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    private val user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        userControl = UserControl(requireContext(), this, useful)

        builder = AlertDialog.Builder(requireContext())
        alertDialog = builder.create()

        loadClicks()

    }

    override fun uResponse(list: List<User>, type: String) {

        useful.closeLoading()

        val user = list[0]

        if (user.status.equals("01")) {
            preferences.setUserData(user)

            Toast.makeText(requireContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show()
            loginCheck()

        } else {

            Toast.makeText(requireContext(), user.msg, Toast.LENGTH_SHORT).show()

        }


    }


    private fun loadClicks() {

        entrarButtonLogin.setOnClickListener {

            if (!validate()) return@setOnClickListener

            user.email = email_et.text.toString()
            user.password = password_et.text.toString()

            useful.openLoading()
            userControl.login(user)


        }

    }

    private fun validate(): Boolean {
        return if (!validation.name(email_et)) false
        else (validation.password(password_et, 1))
    }

    private fun loginCheck(){
        preferences.setLogin(true)

        val intent = Intent(requireContext(), HomeAct::class.java)
        startActivity(intent)

        activity?.finishAffinity()
    }

}