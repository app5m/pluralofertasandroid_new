package com.example.pluralofertasandroid2.activity


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.pluralofertasandroid2.CustomTitleFragment
import com.example.pluralofertasandroid2.R
import com.example.pluralofertasandroid2.fragments.home.HomeFragmentOffer
import com.example.pluralofertasandroid2.fragments.home.cart.CartFragment
import com.example.pluralofertasandroid2.fragments.home.mainMenu.MainMenuFragment
import com.example.pluralofertasandroid2.fragments.home.myCupons.MyCuponsContentFragment
import com.example.pluralofertasandroid2.helper.MyUsefulKotlin
import com.example.pluralofertasandroid2.helper.Preferences
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



//cair na tela de login deslogado
       preferences = Preferences(this)
      /* if (!Preferences(this).getLogin()) {
             val intent = Intent(this, SigininContentActivity::class.java)
             startActivity(intent)


            } else {

             Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show()


         }*/

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


         /*   registerForContextMenu(filterTv)*/



    }
   /* override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.overflow_menu_filter,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_1 -> Toast.makeText(this, "balalaica", Toast.LENGTH_SHORT).show()
            R.id.option_2 -> Toast.makeText(this, "item 2", Toast.LENGTH_SHORT).show()
            R.id.option_3 -> Toast.makeText(this, "item 3", Toast.LENGTH_SHORT).show()
        }
        return super.onContextItemSelected(item)
    }*/
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

/*
*/
    }

}







