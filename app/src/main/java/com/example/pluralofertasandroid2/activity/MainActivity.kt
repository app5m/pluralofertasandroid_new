package com.example.pluralofertasandroid2.activity


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.pluralofertasandroid2.CustomTitleFragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.offer.HomeFragmentOffer
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import kotlinx.android.synthetic.main.tool_bar.*

class MainActivity : AppCompatActivity(), CustomTitleFragment.ICustomToolbarActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //toolbar
        setSupportActionBar(toolbar)
        MyUsefulKotlin().startFragment(HomeFragmentOffer(), supportFragmentManager)
        MyUsefulKotlin().setActionBar(this, supportActionBar!!, "")

        toolbar.visibility = View.GONE

    }
    private var toolbarTint: CustomTitleFragment.ToolbarTint = CustomTitleFragment.ToolbarTint.WHITE

    override fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    override fun setToolbarTint(style: CustomTitleFragment.ToolbarTint) {

        toolbarTint = style
        toolbar.isVisible = style != CustomTitleFragment.ToolbarTint.NONE

        val purple = ContextCompat.getColor(this, R.color.darkish_purple)

        when (style) {
            CustomTitleFragment.ToolbarTint.PURPLE -> {
                toolbar.setBackgroundColor(purple)
                toolbarTitle.setTextColor(Color.WHITE)
            }
            CustomTitleFragment.ToolbarTint.WHITE -> {
                toolbar.setBackgroundColor(Color.WHITE)
                toolbarTitle.setTextColor(purple)
            }
        }
    }

}







