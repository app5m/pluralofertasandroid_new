package com.example.pluralofertasandroid2.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_login_content.*
import kotlinx.android.synthetic.main.fragment_login_content.view.*

class SiginContentFragment : Fragment(),RecyclerItemClickListener {

    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
       val viewFragment = inflater.inflate(R.layout.fragment_login_content, container, false)

/*
        MyUsefulKotlin().startFragment(LoginFragment(), requireActivity().supportFragmentManager)
*/

        viewFragment.btnVoltarLoginButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialViews()


    }
    private fun configInitialViews(){
        val adapter: FragmentStateAdapter =ScreenSlidePagerAdapter(activity)
        mPagerLogin.adapter = adapter
        TabLayoutMediator(tabLayoutLogin, mPagerLogin) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Login"
                1 -> tab.text = "Cadastrar"

            }
        }.attach()

    }
    private class ScreenSlidePagerAdapter(fa: FragmentActivity?) :
        FragmentStateAdapter(fa!!) {

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 ->  LoginFragment()
                1 ->  SiginUpFragment()
                else -> LoginFragment()
            }
        }



        override fun getItemCount(): Int {
            return 2
        }

    }
}


