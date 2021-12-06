package br.com.app5m.pluralofertas.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.adapter.CuponsAdapter
import br.com.app5m.pluralofertas.helper.RecyclerItemClickListener
import br.com.app5m.pluralofertas.model.Coupon
import kotlinx.android.synthetic.main.dialog_cupon.*
import java.util.ArrayList

class CuponsDialog: DialogFragment(), RecyclerItemClickListener {
    private val TAG = "DialogCuponsFrag"

    var recyclerProduct: RecyclerView? = null
    private var cupons  = ArrayList<Coupon>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_cupon, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureInitialViews()
        cupons.add(Coupon())

        ApllyBnt.setOnClickListener {
            dialog?.dismiss();
            Toast.makeText(context, "Cupom aplicado", Toast.LENGTH_SHORT).show()

        }
    }
    fun configureInitialViews(){
        val cuponssAdapter = CuponsAdapter(requireContext(),cupons,this)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)

        cuponsRv.layoutManager = layoutManager

        cuponsRv.adapter = cuponssAdapter


    }

    override fun onClickListenerCupons(coupon: Coupon) {
    }

}