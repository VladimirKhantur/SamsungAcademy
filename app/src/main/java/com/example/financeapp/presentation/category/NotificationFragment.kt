package com.example.financeapp.presentation.category

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.financeapp.databinding.FragmentCategotyBinding
import com.example.financeapp.presentation.main.MainViewModel



import java.util.*

class NotificationFragment : Fragment() {
    private var _binding: FragmentCategotyBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel
    private  var alarmManager: AlarmManager? = null
    private lateinit var alarmIntentEveryDay:PendingIntent
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentCategotyBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,8)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)

        if(calendar.time.compareTo( Date()) < 0) calendar.add(Calendar.HOUR_OF_DAY,0)
//        val intent = Intent(context)


        binding.everyDay.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){

            }else{

            }
        }
    }

}