package br.com.app5m.appshelterpassenger.util.card_config

import android.R
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.text.Editable
import android.widget.ImageView
import br.com.app5m.pluralofertas.util.card_config.CardUtils
import java.lang.StringBuilder

/**
 * Created by Jaison on 23/06/17.
 * Updated by Vilvocx on 235/10/2021.
 */
class CardFormattingTextWatcher : TextWatcher {

    private var etCard: EditText
    private var tvCard: TextView? = null
    private var ivType: ImageView? = null
    private var isDelete = false
    var creditCardType: CreditCardType? = null

    constructor(
        etcard: EditText,
        tvcard: TextView?,
        ivType: ImageView?,
        creditCardType: CreditCardType?
    ) {
        etCard = etcard
        tvCard = tvcard
        this.ivType = ivType
        this.creditCardType = creditCardType
    }


    constructor(etCard: EditText, creditCardType: CreditCardType?) {
        this.etCard = etCard
        this.creditCardType = creditCardType
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        isDelete = before != 0
    }

    override fun afterTextChanged(s: Editable) {

        val source = s.toString()
        val length = source.length
        val stringBuilder = StringBuilder()

        stringBuilder.append(source)

        if (length > 0 && length % 5 == 0) {
            if (isDelete) stringBuilder.deleteCharAt(length - 1) else stringBuilder.insert(
                length - 1,
                " "
            )
            etCard.setText(stringBuilder)
            etCard.setSelection(etCard.text.length)
        }
        if (length >= 4 && creditCardType != null) creditCardType!!.setCardType(
            CardUtils.getCardType(
                source.trim { it <= ' ' })
        ) else {
            creditCardType!!.setCardType(null)
        }
        if (tvCard != null) {
            if (length == 0) tvCard!!.text = "XXXX XXXX XXXX XXXX" else tvCard!!.text =
                stringBuilder
        }
        if (ivType != null && length == 0) ivType!!.setImageResource(R.color.transparent)
    }

    interface CreditCardType {
        fun setCardType(type: Int?)
    }
}