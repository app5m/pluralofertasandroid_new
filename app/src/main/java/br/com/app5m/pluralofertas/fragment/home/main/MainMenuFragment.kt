package br.com.app5m.pluralofertas.fragment.home.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.activity.login.SigininContentActivity
import br.com.app5m.pluralofertas.helper.Preferences
import br.com.app5m.pluralofertas.helper.Useful
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_mainmenu.*



/**
 * A simple [Fragment] subclass.
 */
class MainMenuFragment : Fragment() {
    private lateinit var viewFragment: View
    private var preferences: Preferences? = null

    private lateinit var useful: Useful

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        return inflater.inflate(R.layout.fragment_mainmenu, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        useful = Useful(requireContext())
        preferences = Preferences(context)

        if (!Preferences(context).getLogin()) {
            layoutLoggedOut.visibility = View.VISIBLE
            layoutLogged.visibility = View.GONE
        }else{


            layoutLoggedOut.visibility = View.GONE
            layoutLogged.visibility = View.VISIBLE
        }
        btnEntreOuCadastreseSettingsVisitanteImageView.setOnClickListener {
            val intent = Intent(context, SigininContentActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            Preferences(context).setLogin(false)
            layoutLogged.visibility = View.GONE
            layoutLoggedOut.visibility = View.VISIBLE
        }

        btnEditProfile.setOnClickListener {
            useful.startFragmentOnBack(ProfileEditFragment(), requireActivity().supportFragmentManager)

        }
        myAddresses.setOnClickListener {
            useful.startFragmentOnBack(MyAddressesFragment(), requireActivity().supportFragmentManager)

        }
        myPeyments.setOnClickListener {
            useful.startFragmentOnBack(MyPaymentsFragment(), requireActivity().supportFragmentManager)

        }
        myshoopings.setOnClickListener {
            useful.startFragmentOnBack(MyShoopingsFragment(), requireActivity().supportFragmentManager)

        }
    }
}