package com.example.pluralofertasandroid2.fragments.myCupons

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

class MyCuponsContentFragment : Fragment(),RecyclerItemClickListener {

    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
       val viewFragment = inflater.inflate(R.layout.fragment_my_cupons_content, container, false)

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
                0 -> tab.text = "Usados"


            }
        }.attach()

    }
    private class ScreenSlidePagerAdapter(fa: FragmentActivity?) :
        FragmentStateAdapter(fa!!) {

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 ->  UsedMyCuponsFragment()
                else -> UsedMyCuponsFragment()
            }
        }



        override fun getItemCount(): Int {
            return 1
        }

    }
}


