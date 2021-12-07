package br.com.app5m.pluralofertas.activity.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.fragment.login.SignUpContentFragment
import br.com.app5m.pluralofertas.helper.Useful

class SigininContentActivity:AppCompatActivity() {

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_content)

        useful = Useful(this)

        useful.startFragment(SignUpContentFragment(), supportFragmentManager)



    }





}