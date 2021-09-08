package br.com.app5m.pluralofertas.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.app5m.pluralofertas.R
import kotlinx.android.synthetic.main.dialog_register_address.*


class RegisterAdressDialog: DialogFragment() {
    private val TAG = "DialogRegisterAdress"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_register_address, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save_btn.setOnClickListener {
            dialog?.dismiss();
        }
        btnBackLoginButton.setOnClickListener {
            dialog?.dismiss();

        }
    }
}