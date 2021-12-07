package br.com.app5m.pluralofertas.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_login_content.*

class SignUpContentFragment : Fragment(), RecyclerItemClickListener {

    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //start view
       return inflater.inflate(R.layout.fragment_login_content, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialViews()

        btnVoltarLoginButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }
    private fun configInitialViews(){
        val adapter: FragmentStateAdapter =ScreenSlidePagerAdapter(activity)
        mPagerLogin.adapter = adapter
        TabLayoutMediator(tabLayoutLogin, mPagerLogin) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Login"
                1 -> tab.text = "Cadastrar"

            }
        }.attach()

    }
    private class ScreenSlidePagerAdapter(fa: FragmentActivity?) :
        FragmentStateAdapter(fa!!) {

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 ->  LoginFragment()
                else -> SignUpFragment()
            }
        }



        override fun getItemCount(): Int {
            return 2
        }

    }
}


