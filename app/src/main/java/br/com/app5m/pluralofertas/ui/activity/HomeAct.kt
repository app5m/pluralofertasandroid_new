package br.com.app5m.pluralofertas.ui.activity


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.UserControl
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.ui.fragment.home.main.HomeFragmentOffer
import br.com.app5m.pluralofertas.ui.fragment.home.cart.CartFragment
import br.com.app5m.pluralofertas.ui.fragment.home.main.MainMenuFragment
import br.com.app5m.pluralofertas.ui.fragment.home.coupon.UsedCouponsFrag
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.util.Useful
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*


class HomeAct : AppCompatActivity() {

    private lateinit var preferences: Preferences

    private lateinit var useful: Useful

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        useful = Useful(this)
        preferences = Preferences(this)

        saveFcm()

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
            IntentFilter("Notification")
        )

        useful.startFragment(HomeFragmentOffer(), this.supportFragmentManager)
        useful.setActionBar(this, supportActionBar!!, "", 0)

        toolbar.visibility = View.GONE

        configureInitialViews()


    }


    private fun configureInitialViews(){
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
                    UsedCouponsFrag(),
                    supportFragmentManager
                )
                R.id.settings_visitante -> useful.startFragment(
                    MainMenuFragment(),
                    supportFragmentManager
                )

            }
            true
        }

    }

    private val myReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if (intent.extras != null) {

                val order = intent.extras!!.getString("order")

                if (order == "ADD_TO_CART") {
                    bottom_navigation.selectedItemId = R.id.cartFragment
                }
            }
        }
    }

    private fun saveFcm() {

        if (preferences.getLogin()) {
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult: InstanceIdResult ->

                if (instanceIdResult.token == "") {

                    Log.d("TAG", "token vazio")
                }

                if (preferences.getInstanceTokenFcm() == instanceIdResult.token) {

                    Log.d("TAG", "não salvou")

                } else {

                    val user = User()

                    user.type = WSConstants.TYPE_FCM
                    user.registrationId = instanceIdResult.token

                    preferences.saveInstanceTokenFcm("token", instanceIdResult.token)

                    val userControl = UserControl(this, object : WSResult {
                        override fun uResponse(list: List<User>, type: String) {
                            Log.d("TAG", "salve")

                        }
                    }, useful)

                    userControl.saveFcm(user)

                }
            }
        }


    }

}







