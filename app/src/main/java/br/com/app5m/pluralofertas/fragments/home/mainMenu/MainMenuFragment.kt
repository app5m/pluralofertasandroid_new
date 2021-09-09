package br.com.app5m.pluralofertas.fragments.home.mainMenu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.activity.login.SigininContentActivity
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import br.com.app5m.pluralofertas.helper.Preferences
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_mainmenu.*



/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuFragment : Fragment() {
    private lateinit var viewFragment: View
    private var preferences: Preferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_mainmenu, container, false)


        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            MyUsefulKotlin().startFragment(ProfileEditFragment(), requireActivity().supportFragmentManager)

        }
        myAddresses.setOnClickListener {
            MyUsefulKotlin().startFragment(MyAdressesFragment(), requireActivity().supportFragmentManager)

        }
        myPeyments.setOnClickListener {
            MyUsefulKotlin().startFragment(MyPaymentsFragment(), requireActivity().supportFragmentManager)

        }
        myshoopings.setOnClickListener {
            MyUsefulKotlin().startFragment(MyShoopingsFragment(), requireActivity().supportFragmentManager)

        }
    }
}