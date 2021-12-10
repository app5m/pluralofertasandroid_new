package br.com.app5m.pluralofertas.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.Useful
import kotlinx.android.synthetic.main.activity_documentpdf_webview.*
import kotlinx.android.synthetic.main.toolbar.*

class DocumentPdfWebViewAct : AppCompatActivity() {

    private val pdfLink: String = "https://pluralofertas.com.br/termos_plural.pdf"
    private lateinit var useful: Useful

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentpdf_webview)
        setSupportActionBar(toolbar)

        useful = Useful(this)
        useful.setActionBar(this, supportActionBar!!,"Termos de uso", 0)

        pdfView?.settings?.javaScriptEnabled = true

        pdfView?.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfLink")
        pdfView?.settings?.builtInZoomControls = true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
