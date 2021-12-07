package br.com.app5m.pluralofertas.ui.activity


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.app5m.pluralofertas.util.CustomTitleFragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.fragment.home.HomeFragmentOffer
import br.com.app5m.pluralofertas.ui.fragment.home.cart.CartFragment
import br.com.app5m.pluralofertas.ui.fragment.home.main.MainMenuFragment
import br.com.app5m.pluralofertas.ui.fragment.home.coupon.MyCuponsContentFragment
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Useful
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class HomeAct : AppCompatActivity(), CustomTitleFragment.ICustomToolbarActivity {

    private var itemView: BottomNavigationItemView? = null
    private var preferences: Preferences? = null

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        useful = Useful(this)

        useful.startFragment(HomeFragmentOffer(), this.supportFragmentManager)
        useful.setActionBar(this, supportActionBar!!, "", 0)

        toolbar.visibility = View.GONE


        //Configura o nabBottom para receber o badge
        val bottomNavigationMenuView = bottom_navigation.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(1)
        itemView = v as BottomNavigationItemView

        configureInitialViews()


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

    fun configureInitialViews(){
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> useful.startFragment(
                    HomeFragmentOffer(),
                    supportFragmentManager
                )
                R.id.cartFragment -> useful.startFragment(
                    CartFragment(),
                    supportFragmentManager
                )
                R.id.myCouponsFragment -> useful.startFragment(
                    MyCuponsContentFragment(),
                    supportFragmentManager
                )
                R.id.settings_visitante -> useful.startFragment(
                    MainMenuFragment(),
                    supportFragmentManager
                )

            }
            true
        }
        val intent = intent
        if (intent.hasExtra("CHANGE_NAV_CART")) {
            bottom_navigation.setSelectedItemId(R.id.cartFragment)
        }


    }

}







