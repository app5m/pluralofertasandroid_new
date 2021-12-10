package br.com.app5m.pluralofertas.ui.fragment.payment_flow.cards

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.app5m.appshelterpassenger.util.card_config.CardFormattingTextWatcher
import br.com.app5m.pluralofertas.util.card_config.CardUtils
import kotlinx.android.synthetic.main.fragment_add_new_card.*
import br.com.app5m.appshelterpassenger.util.visual.FlipPageViewTransformer
import br.com.app5m.appshelterpassenger.util.visual.SingleToast
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.RequestControl
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import br.com.app5m.pluralofertas.controller.webservice.WSResult
import br.com.app5m.pluralofertas.model.Request
import br.com.app5m.pluralofertas.model.User
import br.com.app5m.pluralofertas.ui.activity.PaymentFlowContainerAct
import br.com.app5m.pluralofertas.ui.activity.SucessAct
import br.com.app5m.pluralofertas.util.Mask
import br.com.app5m.pluralofertas.util.Useful
import br.com.app5m.pluralofertas.util.Validation
import br.com.app5m.pluralofertas.util.visual.Animation
import br.com.moip.encryption.entities.CreditCard
import br.com.moip.encryption.exception.MoipEncryptionException
import br.com.moip.encryption.helper.MoipValidator
import kotlinx.android.synthetic.main.fragment_add_new_card.birth_et
import kotlinx.android.synthetic.main.fragment_add_new_card.cellphone_et
import kotlinx.android.synthetic.main.fragment_add_new_card.document_et
import kotlinx.android.synthetic.main.fragment_add_new_card.name_et
import org.spongycastle.jce.provider.BouncyCastleProvider
import java.security.Security


/**
 * A simple [Fragment] subclass.
 */
class AddNewCardFrag : Fragment(), WSResult {

    private lateinit var useful: Useful
    private lateinit var requestControl: RequestControl
    private lateinit var animation: Animation
    private lateinit var validation: Validation

    private val cardFrontFrag = CardFrontFrag()
    private val cardBackFrag = CardBackFrag()
    private var mShowingBack = false

    private lateinit var cpfMask: TextWatcher
    private lateinit var cnpjMask: TextWatcher

    private lateinit var paymentFlowContainerAct: PaymentFlowContainerAct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful = Useful(requireContext())
        animation = Animation(requireContext())
        validation = Validation(requireContext())

        requestControl = RequestControl(requireContext(), this, useful)

        paymentFlowContainerAct = requireActivity() as PaymentFlowContainerAct

        configInitialViews()
        configTextWatchers()
        loadMasks()


    }

    override fun rResponse(list: List<Request>, type: String) {

        useful.closeLoading()

        val responseInfo = list[0]

        if (responseInfo.status == "01") {

            startActivity(Intent(requireContext(), SucessAct::class.java))
            requireActivity().finishAffinity()

        }

        SingleToast.INSTANCE.show(requireActivity(), responseInfo.msg!!, Toast.LENGTH_SHORT)

    }

    private fun configTextWatchers() {

        cardNumber_et.addTextChangedListener(
            CardFormattingTextWatcher(
                cardNumber_et,
                object : CardFormattingTextWatcher.CreditCardType {
                    override fun setCardType(type: Int?) {
                        if (mShowingBack) flipCard(false)
                        cardFrontFrag.setCardType(type, cardNumber_et.text.toString())
                    }
                })
        )

        name_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (mShowingBack) flipCard(false)
                cardFrontFrag.setNameCard(editable.toString())
            }
        })

        validity_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (mShowingBack) flipCard(false)
                cardFrontFrag.setValidityCard(editable.toString())
            }
        })

        secudeCode_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (!mShowingBack) flipCard(true)
                cardBackFrag.setCVVCard(editable.toString())
            }
        })

        document_et.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

//                if (charSequence.length > 13) {
//
//                    document_et.removeTextChangedListener(cpfMask)
//                    document_et.addTextChangedListener(cnpjMask)
//
//                } else if (charSequence.length < 14) {
//                    document_et.removeTextChangedListener(cnpjMask)
//                    document_et.addTextChangedListener(cpfMask)
//                }
            }
            override fun afterTextChanged(editable: Editable) {

            }
        })

        loadClicks()

    }

    private fun loadClicks() {

        finish_bt.setOnClickListener {
            if (!checkCard()) return@setOnClickListener
            if (!validate()) return@setOnClickListener

            Security.addProvider(BouncyCastleProvider())

            val creditCard = CreditCard()

            creditCard.cvc = secudeCode_et.text.toString()
            creditCard.number = cardNumber_et.text.toString()
            creditCard.expirationMonth = validity_et.text.toString().split("/").toTypedArray()[0]
            creditCard.expirationYear = validity_et.text.toString().split("/").toTypedArray()[1]
            creditCard.publicKey = WSConstants.PUBLIC_KEY_TEST

            try {

                useful.openLoading()

                val newRequest = Request()

                newRequest.idCart = paymentFlowContainerAct.fullDataPurchase.idCart
                newRequest.idAddress = paymentFlowContainerAct.fullDataPurchase.idAddress
                newRequest.paymentForm = paymentFlowContainerAct.fullDataPurchase.paymentForm

                newRequest.idFreight = paymentFlowContainerAct.fullDataPurchase.idFreight
                newRequest.freightValue = paymentFlowContainerAct.fullDataPurchase.freightValue
                newRequest.subTotalValue = paymentFlowContainerAct.fullDataPurchase.subTotalValue

                newRequest.descValueCoupon = paymentFlowContainerAct.fullDataPurchase.descValueCoupon
                newRequest.idCoupon = paymentFlowContainerAct.fullDataPurchase.idCoupon

                newRequest.obs = "nenhuma"

                newRequest.cardNumber = "80"
                newRequest.cardCep = "91250310"
                newRequest.cardState = "RS"
                newRequest.cardCity = "Porto Alegre"
                newRequest.cardNeighborhood = "Rubem Berta"
                newRequest.cardAddress = "Avenida adelino"
                newRequest.cardComplement = "complemento"

                newRequest.cardName = name_et.text.toString()
                newRequest.cardCellphone = cellphone_et.text.toString()
                newRequest.cardCpf = document_et.text.toString()
                newRequest.cpf = document_et.text.toString()
                newRequest.cardBirth = birth_et.text.toString()

                newRequest.hashCard = creditCard.encrypt().replace("\n", "")
                newRequest.installments = "1"
                newRequest.plataform = "1"


                requestControl.newRequest(newRequest)

            } catch (mee: MoipEncryptionException) {
                SingleToast.INSTANCE.show(requireContext(),
                    "Erro inesperado ao adicionar cartão, tente novamente mais tarde!.",
                    Toast.LENGTH_SHORT)
                mee.printStackTrace()
            }

        }

    }

    private fun checkCard(): Boolean {

        if (TextUtils.isEmpty(cardNumber_et.text)
            || !CardUtils.isValid(cardNumber_et.text.toString().replace(" ", ""))
            || !MoipValidator.isValidCreditCard(cardNumber_et.text.toString().replace(" ", ""))) {

            SingleToast.INSTANCE.show(requireContext(),
                "Por favor, insira um número de cartão válido.", Toast.LENGTH_SHORT)
            animation.shake(cardNumber_et)
            cardNumber_et.error

            return false
        }

        if (TextUtils.isEmpty(name_et.text)) {
            SingleToast.INSTANCE.show(requireContext(),
                "Por favor, insira um nome válido.", Toast.LENGTH_SHORT)
            animation.shake(name_et)
            name_et.error

            return false
        }

        if (TextUtils.isEmpty(validity_et.text)
            || !CardUtils.isValidDate(validity_et.text.toString())
            || !MoipValidator.isValidMonth(validity_et.text.toString().split("/").toTypedArray()[0])
            || !MoipValidator.isValidYear(validity_et.text.toString().split("/").toTypedArray()[1])) {

            SingleToast.INSTANCE.show(requireContext(),
                "Por favor, insira uma data válida.", Toast.LENGTH_LONG)
            animation.shake(validity_et)
            validity_et.error

            return false
        }

        if (TextUtils.isEmpty(secudeCode_et.text)
            || secudeCode_et.text.length < 3
            || !MoipValidator.isValidCVC(secudeCode_et.text.toString())) {

            SingleToast.INSTANCE.show(requireContext(),
                "Por favor, insira um código de segurança válido.", Toast.LENGTH_SHORT)
            animation.shake(secudeCode_et)
            secudeCode_et.error

            return false
        }

        return true
    }

    private fun validate() : Boolean {
//        if (!validation.email(email_et)) return false
        if (!validation.date(birth_et)) return false

        if (document_et.text.length > 13) {

            if (!validation.cpf(document_et)) return false

        } else if (document_et.text.length < 14) {
            if (!validation.cnpj(document_et)) return false
        }

        if (!validation.cellphone(cellphone_et)) return false

        return true
    }

    private fun loadMasks() {
        cpfMask = Mask.insert("###.###.###-##", document_et)
        cnpjMask = Mask.insert("##.###.###/####-##", document_et)

        document_et.addTextChangedListener(cpfMask)
    }

    private fun configInitialViews(){

        val adapter = SectionsPagerAdapter(childFragmentManager)
        cardPager.adapter = adapter

        cardPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mShowingBack = position == 1
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        cardPager.setPageTransformer(false, FlipPageViewTransformer())


    }

    @SuppressLint("ResourceType")
    private fun flipCard(flip: Boolean) {
        if (flip) {
            cardPager.currentItem = 1
            mShowingBack = true
        } else {
            cardPager.currentItem = 0
            mShowingBack = false
        }
    }


    private inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> cardFrontFrag
                else -> cardBackFrag
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }


}