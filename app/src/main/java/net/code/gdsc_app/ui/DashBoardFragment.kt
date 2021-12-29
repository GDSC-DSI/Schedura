package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentDashBoardBinding

class DashBoardFragment : Fragment() {
    private var _binding: FragmentDashBoardBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashBoardBinding.inflate(inflater, container, false)

        binding.ttButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoardFragment_to_homeFragment)
        }
        binding.attButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoardFragment_to_attendanceManagerFragment)
        }
        return binding.root
    }
}