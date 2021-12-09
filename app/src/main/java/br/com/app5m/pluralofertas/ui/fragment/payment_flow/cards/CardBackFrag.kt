package br.com.app5m.pluralofertas.ui.fragment.payment_flow.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import kotlinx.android.synthetic.main.fragment_card_back.*

/**
 * A simple [Fragment] subclass.
 */
class CardBackFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_back, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    fun setCVVCard(cvv: String) {

        cvv_tv.text = cvv

    }


}