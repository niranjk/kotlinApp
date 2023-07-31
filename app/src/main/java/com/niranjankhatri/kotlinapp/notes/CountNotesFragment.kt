package com.niranjankhatri.kotlinapp.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.FragmentCountNotesBinding
import com.niranjankhatri.kotlinapp.notes.viewmodel.CountNotesViewModel

class CountNotesFragment : Fragment() {

    lateinit var binding : FragmentCountNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCountNotesBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(CountNotesViewModel::class.java)
        viewModel.getNoteCountLiveData().observe(viewLifecycleOwner){
            binding.fragmentCountNotesTextView.text = getString(R.string.label_total, it)
        }

        binding.fragmentCountNotesButton.setOnClickListener {
            viewModel.insertNote(binding.fragmentCountEditText.text.toString())
        }
    }
}