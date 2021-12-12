package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.code.gdsc_app.databinding.FragmentTimeTableBinding

class TimeTableFragment : Fragment() {
    private var _binding: FragmentTimeTableBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        return binding.root
    }
}