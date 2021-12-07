package br.com.app5m.pluralofertas.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo


class Animation (private val context: Context) {

    /**
     * Useful
     *
     * @Requered Androidx, daimajia
     */


    fun shake(view: View) {
        YoYo.with(Techniques.Shake).duration(400).repeat(1).playOn(view);
        view.requestFocus();
    }

    fun pulse(view: View) {
        YoYo.with(Techniques.Pulse).duration(450).playOn(view);
    }

}