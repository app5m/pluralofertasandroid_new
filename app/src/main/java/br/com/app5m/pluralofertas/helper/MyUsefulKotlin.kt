package br.com.app5m.pluralofertas.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import br.com.app5m.pluralofertas.R
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MyUsefulKotlin() {

    /**
     * Useful
     */

    fun changeViewVisibility(view: View, type: Int) {

        if (type == 0) {

            view.visibility = View.GONE
        } else {

            view.visibility = View.VISIBLE
        }

    }

    fun startFragment(frag: Fragment, fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, frag).commit()
    }

    fun startFragmentOnBack(frag: Fragment, fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, frag).addToBackStack(null).commit()
    }


    fun openLoading(context: Context, alertDialog: AlertDialog) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.loading, null)
        alertDialog.setView(view)
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun closeLoading(alertDialog: AlertDialog) {
        if (alertDialog.isShowing) alertDialog.dismiss()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setActionBar(activity: Activity, bar: ActionBar, title: String, type: Int){

        val view = activity.layoutInflater.inflate(R.layout.tool_bar, null)
        val params = ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER)

        val textTitle = view.findViewById<TextView>(R.id.toolbarTitle)
        textTitle.text = title
        bar.setCustomView(view, params)

//        if (type == 0) {
//            bar.setHomeAsUpIndicator(activity.resources.getDrawable(R.drawable.ic_arrow_left_green, null))
//        }

        bar.setDisplayShowTitleEnabled(false)
        bar.setDisplayShowCustomEnabled(true)
        bar.setDisplayHomeAsUpEnabled(true)

    }

   @SuppressLint("UseCompatLoadingForDrawables")
    fun setActionBar(activity: Activity, bar: ActionBar, toolbarTitle: String){

        bar.setHomeAsUpIndicator(activity.resources.getDrawable(
            R.drawable.ic_arrow_back,
            null))

        bar.title = toolbarTitle
        bar.setDisplayShowTitleEnabled(true)
        bar.setDisplayShowCustomEnabled(true)
        bar.setDisplayHomeAsUpEnabled(true)


   }


}