package com.example.pluralofertasandroid2.fragments.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.helper.RecyclerItemClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_details_product_bottom.*

class ProductDetails_BottomFragment : Fragment(),RecyclerItemClickListener{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_product_bottom, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configInitialViews()

    }
    private fun configInitialViews(){
        val adapter: FragmentStateAdapter = ScreenSlidePagerAdapter(activity)
        mPager.adapter = adapter

        mPager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(tabLayout, mPager) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Agenda"
                1 -> tab.text = "Notícias"
                2 -> tab.text = "Eventos de outras instituições"
            }
        }.attach()
    }
    
    private class ScreenSlidePagerAdapter(fa: FragmentActivity?) :
        FragmentStateAdapter(fa!!) {
    
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 ->  optionsTab()
                1 ->  HighlightsFrag()
                2 ->  HighlightsFrag()
                3 ->  HighlightsFrag()
                else -> EventFrag()
            }
        }

        private fun optionsTab(): Fragment {

        }

        override fun getItemCount(): Int {
            return 4
        }
        
    }
  
}