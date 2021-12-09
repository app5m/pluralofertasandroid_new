package br.com.app5m.pluralofertas.ui.fragment.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.card_config.CardUtils.AMEX
import br.com.app5m.pluralofertas.util.card_config.CardUtils.DISCOVER
import br.com.app5m.pluralofertas.util.card_config.CardUtils.MASTERCARD
import br.com.app5m.pluralofertas.util.card_config.CardUtils.NONE
import br.com.app5m.pluralofertas.util.card_config.CardUtils.VISA
import kotlinx.android.synthetic.main.fragment_card_front.*

/**
 * A simple [Fragment] subclass.
 */
class CardFrontFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_front, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    val number: TextView
        get() = view?.findViewById(R.id.cardNumber_tv)!!
    val name: TextView
        get() = name_tv
    val validity: TextView
        get() = validity_tv
    val cardType: ImageView
        get() = type_iv

    fun setValidityCard(validityCard: String) {

        validity_tv.text = validityCard

    }

    fun setNameCard(nameCard: String) {

        name_tv.text = nameCard

    }

    fun setCardType(type: Int?, cardNumber: String) {

        cardNumber_tv.text = cardNumber

        if (type != null) {
            when (type) {
                VISA -> typeCard_iv.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_visa
                    )
                )
                MASTERCARD -> typeCard_iv.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_mastercard
                    )
                )
                AMEX -> typeCard_iv.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_amex
                    )
                )
                DISCOVER -> typeCard_iv.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_discover
                    )
                )
                NONE -> typeCard_iv.setImageResource(android.R.color.transparent)
            }
        }

    }
}