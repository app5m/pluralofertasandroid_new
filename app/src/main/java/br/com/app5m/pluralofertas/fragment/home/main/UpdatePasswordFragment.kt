package br.com.app5m.pluralofertas.fragment.home.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.helper.Validation
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.helper.Useful
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_update_password.*


/**
 * A simple [Fragment] subclass.
 */
class UpdatePasswordFragment : Fragment(), WSResult {

    private lateinit var useful: Useful
    private lateinit var userControl: UserControl
    private lateinit var preferences: Preferences
    private lateinit var validation: Validation

    private val user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_update_password, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        validation = Validation(requireContext())
        userControl = UserControl(requireContext(), this, useful)

        loadClicks()

    }

    override fun uResponse(list: List<User>, type: String) {

        useful.closeLoading()

        val user = list[0]

        if (user.status.equals("01")) {

            Toast.makeText(requireContext(), "Senha alterada com sucesso!", Toast.LENGTH_LONG)
                .show()

            newPassword_et.setText("")
            coPassword_et.setText("")

        } else {

            Toast.makeText(requireContext(), user.msg, Toast.LENGTH_SHORT).show()

        }


    }


    private fun loadClicks() {

        updatePass_bt.setOnClickListener {

            if (!validate()) return@setOnClickListener

            userControl.updatePassword(newPassword_et.text.toString())


        }

    }

    private fun validate(): Boolean {
        return if (!validation.password(newPassword_et, 0)) false
        else (validation.coPassword(newPassword_et, coPassword_et))
    }

}