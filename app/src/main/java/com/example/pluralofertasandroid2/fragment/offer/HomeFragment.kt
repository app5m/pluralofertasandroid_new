package com.example.pluralofertasandroid2.fragment.offer

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager

import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.pluralofertasandroid2.CustomTitleFragment
import com.example.pluralofertasandroid2.R

import kotlinx.android.synthetic.main.cell_home_sem_internet.view.*
import kotlinx.android.synthetic.main.fragment_layout_home_itens_na_rolagem.*

import kotlinx.android.synthetic.main.home_body.*
import kotlinx.android.synthetic.main.home_body.view.*
import kotlinx.android.synthetic.main.menu_scrolling.view.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

   
}

