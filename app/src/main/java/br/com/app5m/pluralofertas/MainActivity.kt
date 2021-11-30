package br.com.app5m.pluralofertas


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.app5m.pluralofertas.CustomTitleFragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.fragments.home.HomeFragmentOffer
import br.com.app5m.pluralofertas.fragments.home.cart.CartFragment
import br.com.app5m.pluralofertas.fragments.home.mainMenu.MainMenuFragment
import br.com.app5m.pluralofertas.fragments.home.myCupons.MyCuponsContentFragment
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin
import br.com.app5m.pluralofertas.helper.Preferences
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar.*


class MainActivity : AppCompatActivity(), CustomTitleFragment.ICustomToolbarActivity {

    private var itemView: BottomNavigationItemView? = null
    private var preferences: Preferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar
        setSupportActionBar(toolbar)
        MyUsefulKotlin().startFragment(HomeFragmentOffer(), this.supportFragmentManager)
        MyUsefulKotlin().setActionBar(this, supportActionBar!!, "")

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
                R.id.homeFragment -> MyUsefulKotlin().startFragment(
                    HomeFragmentOffer(),
                    supportFragmentManager
                )
                R.id.cartFragment -> MyUsefulKotlin().startFragment(
                    CartFragment(),
                    supportFragmentManager
                )
                R.id.myCouponsFragment -> MyUsefulKotlin().startFragment(
                    MyCuponsContentFragment(),
                    supportFragmentManager
                )
                R.id.settings_visitante -> MyUsefulKotlin().startFragment(
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







