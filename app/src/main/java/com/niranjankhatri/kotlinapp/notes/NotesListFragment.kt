package com.niranjankhatri.kotlinapp.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.niranjankhatri.kotlinapp.databinding.FragmentNotesListBinding
import com.niranjankhatri.kotlinapp.notes.viewmodel.NoteListViewModel

class NotesListFragment : Fragment() {


    lateinit var binding : FragmentNotesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            fragmentNoteListRecyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = NoteListAdapter(LayoutInflater.from(context))
            fragmentNoteListRecyclerView.adapter = adapter
            val viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteListViewModel(
                        (requireActivity().application as NotesApplication).notesRepository) as T
                }
            }).get(NoteListViewModel::class.java)
            viewModel.getNoteListLiveData().observe(viewLifecycleOwner){
                adapter.replaceItems(it)
            }
        }
    }
}