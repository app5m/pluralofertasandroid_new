package com.example.pluralofertasandroid2.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.multidex.MultiDex
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.example.pluralofertasandroid2.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), CustomTitleFragment.ICustomToolbarActivity,

    NavController.OnDestinationChangedListener {

    private var changeBottomNavigationFlag: Boolean = true

    //Objeto que vai chamar o ouvinte
    private val navController
        get() = findNavController(R.id.nav_host_fragment)

    //Função que irá executar no ouvinte
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.selecionarEstado -> {
                changeBottomNavigationFlag = false
                if (SharedPreferencesManager.getString(MemoryKeys.ID_CITY.key, "")!!.isNullOrEmpty()) {
                    bottom_navigation.visibility = View.GONE
                } else {
                    bottom_navigation.visibility = View.VISIBLE
                }
            }
            R.id.selecionarCidadeFragment -> {
                changeBottomNavigationFlag = false
                if (SharedPreferencesManager.getString(MemoryKeys.ID_CITY.key, "")!!.isNullOrEmpty()) {
                    bottom_navigation.visibility = View.GONE
                } else {
                    bottom_navigation.visibility = View.VISIBLE
                }
            }
            /*R.id.loginFragment -> bottom_navigation.visibility = View.GONE
            R.id.ressetPasswordFragment -> bottom_navigation.visibility = View.GONE
            R.id.getPassworldFragment -> bottom_navigation.visibility = View.GONE*/

            else -> {
                changeBottomNavigationFlag = true
                bottom_navigation.visibility = View.VISIBLE
            }
        }
    }

    private val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        Log.v("TESTE", "OBSERVER SIMULATION")
        bottom_navigation?.also {
            val r = Rect()
            it.getWindowVisibleDisplayFrame(r)
            val screenHeight = it.rootView.height
            val keypadHeight = screenHeight - r.bottom
            if (keypadHeight >= screenHeight * 0.15) {
                if(changeBottomNavigationFlag)
                bottom_navigation?.visibility = View.GONE

            } else {

                Handler().postDelayed({
                    if(changeBottomNavigationFlag)
                    bottom_navigation?.visibility = View.VISIBLE

                }, 50)

            }

        }
    }

    private var idCity: String = SharedPreferencesManager.getString(MemoryKeys.ID_CITY.key, "")!!

    private var toolbarTint: CustomTitleFragment.ToolbarTint = CustomTitleFragment.ToolbarTint.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = NOTIF_CHANNEL_ID
            val channelName = NOTIF_CHANNEL_NAME
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH
                )
            )
        }


        if (!idCity.isNullOrEmpty()) {
            var options = NavOptions.Builder()
                .setPopUpTo(R.id.selecionarEstado, true)
                .build()
            findNavController().navigate(
                SelecionarEstadoFragmentDirections.actionSelecionarEstadoToHomeFragment(),
                options
            )
        }

        //Chamando o ouvinte de destino
        navController.addOnDestinationChangedListener(this)

        //navegação do bottom_navitation
        NavigationUI.setupWithNavController(bottom_navigation, findNavController())

        //toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //   setupBottomNavigationView()

        handleIntent(intent)

        bottom_navigation?.post {
            bottom_navigation?.viewTreeObserver?.addOnGlobalLayoutListener(globalLayoutListener)
        }

    }

    override fun onDestroy() {
        bottom_navigation?.viewTreeObserver?.removeOnGlobalLayoutListener(globalLayoutListener)
        super.onDestroy()
    }

    private fun findNavController(): NavController =
        Navigation.findNavController(this, R.id.nav_host_fragment)

    //navegação telas
    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

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

    override fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        handleIntent(intent!!)

    }

    private fun handleIntent(intent: Intent) {
        val appLinkAction = intent.action
        val appLinkData: Uri? = intent.data
        if (Intent.ACTION_VIEW == appLinkAction) {
            appLinkData?.lastPathSegment?.also { recipeId ->
                Uri.parse("content://com.pluralofertas-app/cupom")
                    .buildUpon()
                    .appendPath(recipeId)
                    .build().also { appData ->
                        startCouponsDetails(appData)

                    }
            }
        }

    }

    private fun startCouponsDetails(appData: Uri?) {
        var id: String = appData.toString().substring(appData.toString().lastIndexOf("/") + 1)
        findNavController().popBackStack(R.id.homeFragment, false)
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetalhesDoProdutoFragment(
                idCoupom = id,
                isFavorite = false
            )
        )

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }


}



