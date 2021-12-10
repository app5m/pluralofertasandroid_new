package br.com.app5m.pluralofertas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.controller.webservice.WSConstants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo_container.*

class PhotoContainerFrag(private val url: String) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(WSConstants.AVATAR_USER_URL + url).into(fragment_image)
    }
}