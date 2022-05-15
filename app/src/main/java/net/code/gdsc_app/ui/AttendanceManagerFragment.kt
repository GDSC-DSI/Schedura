package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.code.gdsc_app.Attendance.Adapter.AttendanceAdapter
import net.code.gdsc_app.Attendance.Database.Attendance
import net.code.gdsc_app.Attendance.Database.AttendanceViewmodel
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentAttendanceManagerBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import net.code.gdsc_app.utils.SwipeToDeleteCallback

class AttendanceManagerFragment : Fragment() {
    private lateinit var attendanceViewmodel: AttendanceViewmodel
    lateinit var attendanceAdapter: AttendanceAdapter
    private var _binding: FragmentAttendanceManagerBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAttendanceManagerBinding.inflate(inflater, container, false)
        requireActivity().window.setStatusBarColor(this.getResources().getColor(R.color.material_blue))
        binding.toolbarDashboard.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_attendanceManagerFragment_to_dashBoardFragment)
        }

        attendanceViewmodel = ViewModelProvider(this)[AttendanceViewmodel::class.java]
        attendanceAdapter = AttendanceAdapter(attendanceViewmodel, binding.rv.rootView, requireContext() ,activity)
        binding.rv.adapter = attendanceAdapter
        binding.rv.layoutManager = LinearLayoutManager(context)
//
        attendanceViewmodel.allLists.observe(viewLifecycleOwner, Observer { list ->
            if (list.isEmpty()) {
                binding.anim.visibility = View.VISIBLE
            } else {
                binding.anim.visibility = View.GONE
            }
            attendanceAdapter.submitList(list)
            attendanceAdapter.attendanceList = list as ArrayList<Attendance>
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_attendanceManagerFragment_to_addSubjectFragment)
        }

        return binding.root
    }
}