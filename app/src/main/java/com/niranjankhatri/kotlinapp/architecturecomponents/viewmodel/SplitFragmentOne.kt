package com.niranjankhatri.kotlinapp.architecturecomponents.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.FragmentSplitOneBinding
import kotlinx.coroutines.launch


class SplitFragmentOne : Fragment() {

    private lateinit var binding : FragmentSplitOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplitOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            fragmentSplitOneTextView.text = getString(R.string.label_total, 0)
        }
        prepareViewModel()
    }

    private fun prepareViewModel(){
        val totalViewModel = ViewModelProvider(requireActivity()).get(TotalViewModel::class.java)
        totalViewModel.total.observe(viewLifecycleOwner){
            updateText(it)
        }

        // collecting the flow which is emitted
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                totalViewModel.totalFlow.collect{
                    updateText(it)
                }
            }
        }
        binding.fragmentSplitOneButton.setOnClickListener {
            // totalViewModel.increaseTotal()
            totalViewModel.increaseTotalUsingFlow()
        }
    }

    private fun updateText(value: Int){
        with(binding){
            fragmentSplitOneTextView.text = getString(R.string.label_total, value)
        }
    }
}