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

        binding.toolbarDashboard.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_attendanceManagerFragment_to_dashBoardFragment)
        }

        attendanceViewmodel = ViewModelProvider(this)[AttendanceViewmodel::class.java]
        attendanceAdapter = AttendanceAdapter(attendanceViewmodel, binding.rv.rootView, activity)
        binding.rv.adapter = attendanceAdapter
        binding.rv.layoutManager = LinearLayoutManager(context)
        enableSwipeToDeleteAndUndo(attendanceAdapter)
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

        //attendance back press
//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().navigate(R.id.action_attendanceManagerFragment_to_dashBoardFragment)
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }

    private fun enableSwipeToDeleteAndUndo(attendanceAdapter: AttendanceAdapter) {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                attendanceAdapter.removeitem(position)
                val item = attendanceAdapter.getList()[position]
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes") { _, dialogInterface ->
                        Snackbar
                            .make(
                                binding.coordLayout,
                                "Item is removed from the list.",
                                Snackbar.LENGTH_SHORT
                            )
                            .show()
                    }
                    .setNegativeButton("No") { _, dialogInterface ->
                        attendanceAdapter.restoreItem(item, position)
                    }
                    .setCancelable(false)
                    .show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(binding.rv)
    }
}