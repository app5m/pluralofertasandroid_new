package br.com.app5m.pluralofertas.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.ui.fragment.login.SignUpContentFragment
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.toolbar.*

class SigininContentActivity:AppCompatActivity() {

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_content)
        setSupportActionBar(toolbar)

        useful = Useful(this)

        useful.startFragment(SignUpContentFragment(), supportFragmentManager)
        useful.setActionBar(this, supportActionBar!!, "Faça login ou realize seu cadastro", 0)


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