package com.example.pluralofertasandroid2.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.dialog.DialogRegisterAdress
import com.example.pluralofertasandroid2.helper.Preferences
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_siginup.*

class SiginUpFragment : Fragment(), RecyclerItemClickListener {
    private  val TAG = "SiginUpFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_siginup, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCriarConta.setOnClickListener {
            registerCheck()
        }
        adressRegister.setOnClickListener {
            Log.d(TAG, "onClick: opening dialog")
            val dialog = DialogRegisterAdress()
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let { it1 -> dialog.show(it1,"DialogRegisterAdress") }

        }
    }

    fun registerCheck(){
        Preferences(context).setLogin(true)
        activity?.finish()
    }
}