package com.example.financeapp.presentation.cash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.financeapp.R
import com.example.financeapp.databinding.FragmentCashBinding
import com.example.financeapp.databinding.FragmentOperationsBinding
import com.example.financeapp.presentation.adapters.OperationsListAdapter
import com.example.financeapp.presentation.main.MainViewModel

class CashFragment : Fragment() {
    private var _binding: FragmentCashBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentCashBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        var totalCost:Int = 0
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.operationsList.observe(viewLifecycleOwner){
            for(items in it.indices) {
                totalCost += it[items].cost
            }
            binding.totalCost.text = totalCost.toString() + "₽"
        }
    }
}