package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.code.gdsc_app.Attendance.Database.Attendance
import net.code.gdsc_app.Attendance.Database.AttendanceViewmodel
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentAddSubjectBinding
import net.code.gdsc_app.utils.Constants
import net.code.gdsc_app.utils.Snacker

@Suppress("DEPRECATION")
class AddSubjectFragment : Fragment() {
    private lateinit var attendanceViewmodel: AttendanceViewmodel
    private var _binding: FragmentAddSubjectBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddSubjectBinding.inflate(inflater, container, false)
        binding.addSub.setOnClickListener {
            attendanceViewmodel = ViewModelProviders.of(this).get(AttendanceViewmodel::class.java)
            if(binding.userToDoEditText.text.toString() == ""){
                Snacker(it,"This Field can't be empty").error()
            }
            else {
                val attendance = Attendance(
                    subject = binding.userToDoEditText.text.toString(),
                    attended = 0,
                    total = 0,
                    percentage = 100,
                    bg = Constants.getRandomCardColor()
                )
                attendance.id = System.currentTimeMillis()
                attendanceViewmodel.insert(attendance)
                findNavController().navigate(R.id.action_addSubjectFragment_to_attendanceManagerFragment)
            }
        }
        return binding.root
    }
}