package br.com.app5m.pluralofertas.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import br.com.app5m.pluralofertas.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.text.NumberFormat
import java.util.*


class Useful (private val context: Context) {

    /**
     * Useful
     *
     * @Requered Androidx, daimajia
     */

    private val builder = AlertDialog.Builder(context)
    val alertDialog = builder.create()

    fun startFragment(frag: Fragment, fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, frag).commitAllowingStateLoss()
    }

    fun startFragmentOnBack(frag: Fragment, fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        transaction.replace(R.id.container, frag).addToBackStack(null).commitAllowingStateLoss()
    }

    fun openLoading() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.loading, null)
        alertDialog.setView(view)
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun closeLoading() {
        if (alertDialog.isShowing) alertDialog.dismiss()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setActionBar(activity: Activity, bar: ActionBar, title: String, type: Int){

        val view = activity.layoutInflater.inflate(R.layout.toolbar, null)
        val params = ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT, Gravity.START)

        bar.setCustomView(view, params)
        bar.title = title

        when (type) {
            0 -> {
                bar.setDisplayShowTitleEnabled(true)
                bar.setDisplayHomeAsUpEnabled(true)
            }
            2 -> {
                bar.setDisplayShowTitleEnabled(false)
            }
        }

        bar.setDisplayShowCustomEnabled(true)
        bar.setHomeAsUpIndicator(activity.resources.getDrawable(R.drawable.ic_arrow_back, null))

    }

    fun bitmapDescriptorFromVector(
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun moneyToDouble(valor: String): Double {
        return valor.replace(" ", "")
            .replace("R$", "")
            .replace(",", ".").toDouble()
    }

    fun parseMoney(valor: Double): String {
        val COUNTRY = "BR"
        val LANGUAGE = "pt"
        return NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY)).format(valor)
    }


    @SuppressLint("QueryPermissionsNeeded")
    fun openWebPage(url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    fun createLink(targetTextView: TextView, completeString: String,
                   partToClick: String, clickableAction: ClickableSpan?): TextView {
        val spannableString = SpannableString(completeString)

        // make sure the String is exist, if it doesn't exist
        // it will throw IndexOutOfBoundException
        val startPosition = completeString.indexOf(partToClick)
        val endPosition = completeString.lastIndexOf(partToClick) + partToClick.length
        spannableString.setSpan(clickableAction, startPosition, endPosition,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        targetTextView.text = spannableString
        targetTextView.movementMethod = LinkMovementMethod.getInstance()
        return targetTextView
    }

}