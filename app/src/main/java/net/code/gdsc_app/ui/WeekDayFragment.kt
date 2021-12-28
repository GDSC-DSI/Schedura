package net.code.gdsc_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.code.gdsc_app.Attendance.Adapter.RecyclerViewAdapter
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentWeekdayBinding
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.networking.Repository
import net.code.gdsc_app.utils.Constants
import net.code.gdsc_app.viewmodels.TimeTableViewModel
import net.code.gdsc_app.viewmodels.TimeTableViewModelFactory


class WeekDayFragment : Fragment() {

    companion object{
        fun newInstance(query: Query, weekDay: Constants.Companion.WeekDay) : WeekDayFragment {
            val fragment = WeekDayFragment()
            val args = Bundle()
            args.putParcelable("query", query)
            args.putSerializable("weekDay", weekDay)
            fragment.arguments = args
            return fragment
        }
    }

private lateinit var binding:FragmentWeekdayBinding
private lateinit var viewModel: TimeTableViewModel
private val repository : Repository by lazy {
    Repository()
}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weekday, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentWeekdayBinding.bind(view)

        viewModel = ViewModelProvider(this,TimeTableViewModelFactory(repository))[TimeTableViewModel::class.java]

        //        binding.tvMonday.text = "Fragment One"

        val query = arguments?.getParcelable<Query>("query")
        val weekDay : Constants.Companion.WeekDay = arguments?.getSerializable("weekDay") as Constants.Companion.WeekDay

        if (query != null){
            viewModel.getTimetable(query,weekDay)
        }
        val adapter:RecyclerViewAdapter = RecyclerViewAdapter()

        binding.recyclerView.layoutManager=LinearLayoutManager(activity)
        binding.recyclerView.adapter=adapter

        when (weekDay){
            Constants.Companion.WeekDay.MONDAY -> {
                viewModel.mondayData.observe(viewLifecycleOwner, {
                    adapter.submitList(arrayListOf(it))
                })
            }
            Constants.Companion.WeekDay.TUESDAY -> {
                viewModel.tuesdayData.observe(viewLifecycleOwner, {

                })
            }
            Constants.Companion.WeekDay.WEDNESDAY -> {
                viewModel.wednesdayData.observe(viewLifecycleOwner, {

                })
            }
            Constants.Companion.WeekDay.THURSDAY -> {
                viewModel.thursdayData.observe(viewLifecycleOwner, {

                })
            }
            Constants.Companion.WeekDay.FRIDAY -> {
                viewModel.fridayData.observe(viewLifecycleOwner, {

                })
            }
            Constants.Companion.WeekDay.SATURDAY -> {
                viewModel.saturdayData.observe(viewLifecycleOwner, {

                })
            }
        }
    }


}