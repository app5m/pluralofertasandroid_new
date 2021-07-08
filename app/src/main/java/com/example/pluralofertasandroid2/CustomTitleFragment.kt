package com.example.pluralofertasandroid2

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class CustomTitleFragment : Fragment() {

    interface ICustomToolbarActivity {
        fun setToolbarTitle(title: String)
        fun setToolbarTint(style: ToolbarTint)
    }

    enum class ToolbarTint {
        NONE, PURPLE, WHITE
    }

    abstract var title: String

    abstract var toolbarTint: ToolbarTint

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as ICustomToolbarActivity)?.setToolbarTitle(title)
        (activity as ICustomToolbarActivity)?.setToolbarTint(toolbarTint)
    }

    fun changeToolbarTitle(newTitle: String) {
        title = newTitle
        (activity as? ICustomToolbarActivity)?.setToolbarTitle(title)
    }

}
