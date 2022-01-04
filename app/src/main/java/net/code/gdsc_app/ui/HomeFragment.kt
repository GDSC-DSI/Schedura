package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentHomeBinding
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.utils.Snacker

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

        binding.toolbarDashboard.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.ttButton.setOnClickListener {

            if(binding.spinner1.selectedItem.toString() == "Branch" &&
                binding.spinner2.selectedItem.toString() == "Semester" &&
                 binding.spinner3.selectedItem.toString() == "Section" ){
                Snacker(requireActivity().findViewById(android.R.id.content), "Select branch, semester and section").error()
            }

            else if (binding.spinner1.selectedItem.toString() == "Branch") {
                Snacker(requireActivity().findViewById(android.R.id.content), "Branch not selected").error()
            }

            else if (binding.spinner2.selectedItem.toString() == "Semester") {
                Snacker(requireActivity().findViewById(android.R.id.content), "Semester not selected").error()
            }

            else if (binding.spinner3.selectedItem.toString() == "Section") {
                Snacker(requireActivity().findViewById(android.R.id.content), "Section not selected").error()
            }

            else {
                val branch = binding.spinner1.selectedItem.toString()
                val sem = binding.spinner2.selectedItem.toString()
                val sec = binding.spinner3.selectedItem.toString()

                val query = Query(branch, sem, sec)
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToTimeTableActivity(
                        query
                    )
                )
            }

        }
//        binding.button2.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_attendanceManagerFragment)
//        }

        return binding.root
    }
}