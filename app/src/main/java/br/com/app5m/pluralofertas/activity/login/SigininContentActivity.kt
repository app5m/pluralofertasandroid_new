package br.com.app5m.pluralofertas.activity.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.fragments.login.SigninContentFragment
import br.com.app5m.pluralofertas.helper.MyUsefulKotlin

class SigininContentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_content)

        MyUsefulKotlin().startFragment(SigninContentFragment(), supportFragmentManager)



    }





}