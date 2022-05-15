package net.code.gdsc_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import net.code.gdsc_app.Attendance.Adapter.RecyclerViewAdapter
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.FragmentWeekdayBinding
import net.code.gdsc_app.models.Subject
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.networking.Repository
import net.code.gdsc_app.utils.Constants
import net.code.gdsc_app.utils.Snacker
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setStatusBarColor(this.getResources().getColor(R.color.material_blue))
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

        var loadProgress = binding.shimmerEffect
        when (weekDay){
            Constants.Companion.WeekDay.MONDAY -> {
                viewModel.mondayData.observe(viewLifecycleOwner, {
                    loadProgress.visibility=View.GONE
                    if(it.body()?.size!! > 0){
                        binding.noDataText.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.body())
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.noDataText.visibility = View.VISIBLE
                    }
                })
            }
            Constants.Companion.WeekDay.TUESDAY -> {
                viewModel.tuesdayData.observe(viewLifecycleOwner, {
                    loadProgress.visibility=View.GONE
                    if(it.body()?.size!! > 0){
                        binding.noDataText.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.body())
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.noDataText.visibility = View.VISIBLE
                    }
                })
            }
            Constants.Companion.WeekDay.WEDNESDAY -> {
                viewModel.wednesdayData.observe(viewLifecycleOwner, {
                    loadProgress.visibility=View.GONE
                    if(it.body()?.size!! > 0){
                        binding.noDataText.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.body())
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.noDataText.visibility = View.VISIBLE
                    }
                })
            }
            Constants.Companion.WeekDay.THURSDAY -> {
                viewModel.thursdayData.observe(viewLifecycleOwner, {
                    loadProgress.visibility=View.GONE
                    if(it.body()?.size!! > 0){
                        binding.noDataText.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.body())
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.noDataText.visibility = View.VISIBLE
                    }
                })
            }
            Constants.Companion.WeekDay.FRIDAY -> {
                viewModel.fridayData.observe(viewLifecycleOwner, {
                    loadProgress.visibility=View.GONE
                    if(it.body()?.size!! > 0){
                        binding.noDataText.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.body())
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.noDataText.visibility = View.VISIBLE
                    }
                })
            }
            Constants.Companion.WeekDay.SATURDAY -> {
                viewModel.saturdayData.observe(viewLifecycleOwner, {
                    loadProgress.visibility=View.GONE
                    if(it.body()?.size!! > 0){
                        binding.noDataText.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.body())
                    } else {
                        binding.recyclerView.visibility = View.GONE
                        binding.noDataText.visibility = View.VISIBLE
                    }
                })
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Snacker(requireActivity().findViewById(android.R.id.content), it).error()
        })
    }


}