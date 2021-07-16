package com.example.pluralofertasandroid2.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.activity.MainActivity
import com.example.pluralofertasandroid2.helper.Preferences
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login_content.view.*

class LoginFragment: Fragment(), RecyclerItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_login, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entrarButtonLogin.setOnClickListener {
            Preferences(context).setLogin(true)
            activity?.finishAffinity()
            //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("finishedIntro", true).apply()
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            //SharedPreferencesManager.putBoolean(PREFS_ONBOARDING,true)
            startActivity(intent)
        }

    }

}