package com.example.pluralofertasandroid2.fragments.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class ProfileEditFragment: Fragment(), RecyclerItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVoltarMeuPerfilTextView.setOnClickListener{
            onBackPressed()
        }
    }
    fun onBackPressed() {
        activity?.let {
            MyUsefulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}