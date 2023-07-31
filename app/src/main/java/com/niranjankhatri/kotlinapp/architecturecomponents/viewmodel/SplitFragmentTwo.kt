package com.niranjankhatri.kotlinapp.architecturecomponents.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.FragmentSplitOneBinding
import com.niranjankhatri.kotlinapp.databinding.FragmentSplitTwoBinding


class SplitFragmentTwo : Fragment() {

    private lateinit var binding : FragmentSplitTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplitTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            fragmentSplitTwoTextView.text = getString(R.string.label_total, 0)
        }
        val totalViewModel = ViewModelProvider(requireActivity()).get(TotalViewModel::class.java)
        totalViewModel.total.observe(viewLifecycleOwner){
            updateText(it)
        }
    }

    private fun updateText(value: Int){
        with(binding){
            fragmentSplitTwoTextView.text = getString(R.string.label_total, value)
        }
    }

}