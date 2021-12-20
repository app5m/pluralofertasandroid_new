package br.com.app5m.pluralofertas.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.fragment.home.main.MyAddressesFragment
import br.com.app5m.pluralofertas.ui.fragment.home.main.ProfileEditFragment
import br.com.app5m.pluralofertas.ui.fragment.home.main.RequestsFrag
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.toolbar.*

class MainMenuContainerAct : AppCompatActivity() {

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_container)
        setSupportActionBar(toolbar)

        useful = Useful(this)


        if (intent.extras != null) {

            when(intent!!.extras!!.getString("screenKey")) {
                "profile" -> {
                    useful.startFragment(ProfileEditFragment(), supportFragmentManager)
                }
                "requests" -> {
                    useful.startFragment(RequestsFrag(), supportFragmentManager)
                }
                "addresses" -> {
                    useful.startFragment(MyAddressesFragment(), supportFragmentManager)
                }

            }


            useful.setActionBar(this, supportActionBar!!, "", 0)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}