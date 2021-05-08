package com.prueba.andyrios.presentation.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.prueba.andyrios.R
import com.prueba.andyrios.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {
    private var url: String? = null
    private val argUrl = "ARG_PARAM1"
    private lateinit var fragmentInfoBinding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(argUrl)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        fragmentInfoBinding.pageWebView.webViewClient = WebViewClient()
        fragmentInfoBinding.pageWebView.loadUrl(url?:"")
        fragmentInfoBinding.pageWebView.settings.javaScriptEnabled = true
        fragmentInfoBinding.pageWebView.settings.setSupportZoom(true)

        return fragmentInfoBinding.root
    }
    companion object {
        val FRAGMENT_NAME = InfoFragment::class.java.name
        @JvmStatic
        fun newInstance(url : String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(argUrl, url)
                }
            }
    }
}