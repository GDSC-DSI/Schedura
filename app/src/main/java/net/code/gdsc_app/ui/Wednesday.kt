package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentTuesdayBinding
import net.code.gdsc_app.databinding.FragmentWednesdayBinding

class Wednesday : Fragment() {
    private lateinit var binding: FragmentWednesdayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_monday, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvWednesday.text = "Fragment Three"
    }

}