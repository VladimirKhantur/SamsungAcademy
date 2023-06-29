package com.example.financeapp.presentation.statistics

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.financeapp.databinding.FragmentStatisticsBinding
import com.example.financeapp.presentation.main.MainViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class StatisticsFragment : Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var pieChart: PieChart
    private lateinit var pieChartSecond: PieChart
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentStatisticsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        pieChart = binding.pieStatistics
        pieChartSecond = binding.pieStatisticsIncome
        viewModel.operationsList.observe(viewLifecycleOwner){
            val arrayList = ArrayList<PieEntry>()
            val arrayListCon = ArrayList<PieEntry>()
            for(items in it.indices){
                if(it[items].type == "Расход"){
                    arrayList.add(PieEntry(it[items].cost.toFloat() * -1,it[items].category))
                }
                else{
                    arrayListCon.add(PieEntry(it[items].cost.toFloat(),it[items].category))
                }

                val pieDataSet = PieDataSet(arrayList,"")
                val data = PieData(pieDataSet)
                pieChart.invalidate()
                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
                pieDataSet.valueTextColor = Color.BLACK
                pieDataSet.valueTextSize = 15f
                binding.pieStatistics.data = data
                pieChart.description.text=""
                pieChart.animateY(1000)

                val pieDataSetIncome = PieDataSet(arrayListCon,"")
                val dataSecond = PieData(pieDataSetIncome)
                pieChartSecond.invalidate()
                pieDataSetIncome.setColors(ColorTemplate.MATERIAL_COLORS,255)
                pieDataSetIncome.valueTextColor = Color.BLACK
                pieDataSetIncome.valueTextSize = 15f
                binding.pieStatisticsIncome.data = dataSecond
                pieChartSecond.animateY(1000)
                pieChartSecond.description.text=""
            }
        }








    }
}