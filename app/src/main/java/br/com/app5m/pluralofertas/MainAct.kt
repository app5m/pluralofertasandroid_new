package br.com.app5m.pluralofertas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.app5m.pluralofertas.util.DialogMessages
import br.com.app5m.appshelterpassenger.util.MyLocation
import br.com.app5m.pluralofertas.ui.activity.HomeAct
import br.com.app5m.pluralofertas.ui.activity.IntroAct
import br.com.app5m.pluralofertas.ui.activity.PermissionLocalizationAct
import br.com.app5m.pluralofertas.util.Preferences
import br.com.app5m.pluralofertas.model.LocationUser

class MainAct : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var myLocation: MyLocation
    private lateinit var locationResult: MyLocation.LocationResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = Preferences(this)
        myLocation = MyLocation(this)

        preferences.clearUserLocation()

        locationResult = object : MyLocation.LocationResult() {

            override fun getLocation(location: Location?) {

                if (location == null) {
                    preferences.clearUserLocation()
                    statusCheck()
                } else {
                    val locationUser = LocationUser()

                    locationUser.latitude = location.latitude.toString()
                    locationUser.longitude = location.longitude.toString()

                    preferences.setUserLocation(locationUser)

                    start2()
                }
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {


            startActivity(Intent(this@MainAct, PermissionLocalizationAct::class.java))


            return
        }

        statusCheck()
    }

    override fun onRestart() {
        super.onRestart()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            DialogMessages(this).booleanClick("Atenção!",
                "É necessário ter aceitos todas as permissões para obter maior precisão de sua localização!",
                object : DialogMessages.AnswerBoolean {
                    override fun setOnClickListener(boolean: Boolean) {
                        if (boolean) {
                            startActivity(Intent(this@MainAct, PermissionLocalizationAct::class.java))
                        } else {
                            finishAffinity()
                        }
                    }
                })
        } else {

            statusCheck()

        }
    }

    private fun start2() {

        if (preferences.getLogin()) {

            startActivity(Intent(this, HomeAct::class.java))
        } else {

            if (preferences.getInt(
                    Preferences.ENTERING_FIRST_TIME, 1) == 1) {

                startActivity(Intent(this, IntroAct::class.java))

            } else {

                startActivity(Intent(this, HomeAct::class.java))
            }


        }

        finish()

    }


    private fun statusCheck() {

        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        val enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!enabled) {
            DialogMessages(this).buildAlertMessageNoGps()
        } else {
            myLocation.getLocation(locationResult)
        }

    }

}