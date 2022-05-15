package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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
        _binding = FragmentAddSubjectBinding.inflate(inflater, container, false)
        requireActivity().window.setStatusBarColor(this.getResources().getColor(R.color.material_blue))
        binding.toolbarDashboard.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.presentClassNumber.transformationMethod = null
        binding.totalClassNumber.transformationMethod = null

        binding.addSub.setOnClickListener {
            var presentClasses = binding.presentClassNumber.text.toString()
            var totalClasses = binding.totalClassNumber.text.toString()
            attendanceViewmodel = ViewModelProviders.of(this).get(AttendanceViewmodel::class.java)
            if (binding.userToDoEditText.text.toString() == "") {
                Snacker(it, "This Field can't be empty").error()
            }
            else if (binding.presentClassNumber.text.toString() == ""
            ) {
                Snacker(it, "Enter the number of classes present").error()
            }
            else if (binding.totalClassNumber.text.toString() == ""
            ) {
                Snacker(it, "Enter the total number of classes").error()
            }
            else if (binding.totalClassNumber.text.toString().toLong() == 0L) {
                Snacker(it, "Total Classes cannot be zero").error()
            }
            else if (binding.totalClassNumber.text.toString()
                    .toLong() < binding.presentClassNumber.text.toString().toLong()
            ) {
                Snacker(it, "Total classes cannot be less than present classes").error()
            }
            else if (presentClasses == "." || totalClasses =="."){
                Snacker(it, "Invalid Characters")
            }
            else {
                val attendance = Attendance(
                    subject = binding.userToDoEditText.text.toString(),
                    attended = binding.presentClassNumber.text.toString().toLong(),
                    total = binding.totalClassNumber.text.toString().toLong(),
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