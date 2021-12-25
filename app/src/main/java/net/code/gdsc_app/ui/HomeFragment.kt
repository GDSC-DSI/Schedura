package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentHomeBinding
import net.code.gdsc_app.models.Query

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.ttButton.setOnClickListener {
            val query = Query("CSE", 5, "E")
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTimeTableActivity(query))
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_attendanceManagerFragment)
        }
        return binding.root
    }
}