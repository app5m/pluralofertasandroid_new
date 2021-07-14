package com.example.pluralofertasandroid2.fragments.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.Preferences
import kotlinx.android.synthetic.main.fragment_login_content.view.*
import kotlinx.android.synthetic.main.fragment_perfil.*


/**
 * A simple [Fragment] subclass.
 * Use the [PerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilFragment : Fragment() {
    private lateinit var viewFragment: View
    private var preferences: Preferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_perfil, container, false)


        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (preferences?.getLogin() == true) {
            layoutLoggedOut.visibility = View.GONE
            layoutLogged.visibility = View.VISIBLE
        }else{
            layoutLoggedOut.visibility = View.VISIBLE
            layoutLogged.visibility = View.GONE
        }

        btnLogout.setOnClickListener {
            Preferences(context).setLogin(false)
            layoutLogged.visibility = View.GONE
            layoutLoggedOut.visibility = View.VISIBLE
        }
    }
}