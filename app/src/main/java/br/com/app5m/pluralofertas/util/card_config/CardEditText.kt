package br.com.app5m.appshelterpassenger.util.card_config

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText

/**
 * Created by Jaison on 23/06/17.
 * Updated by Vilvocx on 235/10/2021.
 */
class CardEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    var backButtonListener: BackButtonListener? = null


    fun setOnBackButtonListener(listener: BackButtonListener?) {
        backButtonListener = listener
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            // User has pressed Back key. So hide the keyboard
            Log.d("ET", "onKeyPreIme: ")
            if (backButtonListener != null) backButtonListener!!.onBackClick()
            return true
            // TODO: Hide your view as you do it in your activity
        }
        return false
    }

    interface BackButtonListener {
        fun onBackClick()
    }
}