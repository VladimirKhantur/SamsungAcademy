package com.example.financeapp.presentation.operations

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import com.example.financeapp.databinding.FragmentOperationsBinding
import com.example.financeapp.presentation.adapters.OperationsListAdapter
import com.example.financeapp.presentation.addOperation.AddOperationFragment
import com.example.financeapp.presentation.main.MainViewModel


class OperationsFragment : Fragment() {
    private var _binding:FragmentOperationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var operationListAdapter:OperationsListAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentOperationsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.operationsList.observe(viewLifecycleOwner){
            operationListAdapter.submitList(it)
        }
        binding.btnAddOperation.setOnClickListener {
            navController.navigate(R.id.action_operationsFragment_to_addOperationFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        val rvOperationsList = binding.rvOperations
        with(rvOperationsList) {
            operationListAdapter = OperationsListAdapter()
            adapter = operationListAdapter
        }
        setupClickListener()
        setupSwipeListener(rvOperationsList)
    }

    private fun setupClickListener() {
        operationListAdapter.onOperationsItemClickListener = {

        }


    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = operationListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteOperationItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

}