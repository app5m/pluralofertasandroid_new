package com.example.pluralofertasandroid2.fragments.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.Preferences
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_siginup.*

class SiginUpFragment : Fragment(), RecyclerItemClickListener {


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
    }

    fun registerCheck(){
        Preferences(context).setLogin(true)
        activity?.finish()
    }
}