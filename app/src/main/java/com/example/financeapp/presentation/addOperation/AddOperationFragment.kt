package com.example.financeapp.presentation.addOperation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.financeapp.R
import com.example.financeapp.databinding.FragmentAddOperationBinding
import com.example.financeapp.databinding.FragmentOperationsBinding
import com.example.financeapp.presentation.main.MainViewModel

class AddOperationFragment : Fragment() {
    private var _binding: FragmentAddOperationBinding? = null
    private val binding get() = _binding!!
    private lateinit var income:RadioButton
    private lateinit var navController: NavController
    private lateinit var consumption:RadioButton
    private lateinit var categorySpinner:Spinner
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentAddOperationBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        navController = findNavController()
        initWidgets()
        updateRadioGroup(income)
        type = "Доход"
        binding.income.setOnClickListener {
            updateRadioGroup(income)

            setupSpinnerIn()
        }
        binding.consumption.setOnClickListener {
            updateRadioGroup(consumption)
            setupSpinnerCon()
        }
        binding.btnAddOperation.setOnClickListener {
            val type = AddOperationFragment.type
            var cost = (binding.costEt.text.toString()).trim().toInt()
            if(type =="Расход"){
                cost = (binding.costEt.text.toString()).trim().toInt() * -1
            }
            val category = categorySpinner.selectedItem.toString()
            val comment = binding.commentEt.text.toString()
            viewModel.addOperationItem(type,cost,category,comment)
            navController.navigate(R.id.action_addOperationFragment_to_operationsFragment)
        }
    }

    private fun setupSpinnerCon(){
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.consumption,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            categorySpinner.adapter = adapter
        }
        type = "Расход"
        binding.plus.text = "-"
    }

    private fun setupSpinnerIn(){
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.income,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            categorySpinner.adapter = adapter
        }
        type = "Доход"
        binding.plus.text = "+"
    }

    private fun getTypeRadioGroup():String{
        return if(income.isEnabled) "Доход" else "Расход"
    }

    private fun initWidgets(){
        income = binding.income
        consumption = binding.consumption
        categorySpinner = binding.categorySpinner

    }

    private fun updateRadioGroup(selected:RadioButton){
        income.background = ContextCompat.getDrawable(requireContext(),R.drawable.radio_off)
        consumption.background = ContextCompat.getDrawable(requireContext(),R.drawable.radio_off)

        selected.background =  ContextCompat.getDrawable(requireContext(),R.drawable.radio_on)
    }
    companion object{
        var type:String = "Доход"
    }





}