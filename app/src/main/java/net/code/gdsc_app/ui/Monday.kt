package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentMondayBinding


class Monday : Fragment() {
private lateinit var binding:FragmentMondayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_monday, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvMonday.text = "Fragment One"
    }


}