package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        val url = "https://gdsc.community.dev/dayananda-sagar-institutions-bengaluru/"

        binding.webViewResource.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
        return binding.root
    }
}