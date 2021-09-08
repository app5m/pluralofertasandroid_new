package br.com.app5m.pluralofertas.fragments.home.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.MyUseFulKotlin
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class ProfileEditFragment: Fragment(), RecyclerItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
        val viewFragment = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVoltarMeuPerfilTextView.setOnClickListener{
            onBackPressed()
        }
    }
    fun onBackPressed() {
        activity?.let {
            MyUseFulKotlin().startFragmentOnBack(MainMenuFragment(),
                it.supportFragmentManager)
        }
    }
}