package br.com.app5m.pluralofertas.ui.fragment.home.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.ui.activity.MainMenuContainerAct
import br.com.app5m.pluralofertas.ui.activity.SigininContentActivity
import br.com.app5m.pluralofertas.util.DialogMessages
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Useful
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_mainmenu.*



/**
 * A simple [Fragment] subclass.
 */
class MainMenuFragment : Fragment(), WSResult {
    private lateinit var preferences: Preferences

    private lateinit var useful: Useful
    private lateinit var userControl: UserControl


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_mainmenu, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        useful = Useful(requireContext())
        preferences = Preferences(requireContext())

        userControl = UserControl(requireContext(), this, useful)

        if (!Preferences(context).getLogin()) {
            layoutLoggedOut.visibility = View.VISIBLE
            layoutLogged.visibility = View.GONE
        }else{
            layoutLoggedOut.visibility = View.GONE
            layoutLogged.visibility = View.VISIBLE

            useful.openLoading()
            userControl.listId()

        }



        btnEntreOuCadastreseSettingsVisitanteImageView.setOnClickListener {
            val intent = Intent(context, SigininContentActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            DialogMessages(requireContext()).click("Aten????o",
                "Se voc?? deslogar, precisar?? realizar o login novamente mais tarde. Deseja continuar?",
                object : DialogMessages.Answer {
                    override fun setOnClickListener() {
                        preferences.clearUserData()

                        val intent = Intent(context, SigininContentActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                })

        }

        btnEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), MainMenuContainerAct::class.java).putExtra("screenKey", "profile"))

        }
        myAddresses.setOnClickListener {
            startActivity(Intent(requireContext(), MainMenuContainerAct::class.java).putExtra("screenKey", "addresses"))

        }
        myshoopings.setOnClickListener {
            startActivity(Intent(requireContext(), MainMenuContainerAct::class.java).putExtra("screenKey", "requests"))

        }
    }

    override fun uResponse(list: List<User>, type: String) {

        useful.closeLoading()

        val responseInfo = list[0]
//
//        Glide.with(requireContext()).load(WSConstants.SALE_URL + responseInfo.avatar).into(userAvatar_iv)

        userNameTv.text = responseInfo.name
    }
}