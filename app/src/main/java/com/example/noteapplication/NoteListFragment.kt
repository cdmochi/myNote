package com.example.noteapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.Data.Note
import com.example.noteapplication.Data.NoteDatabase
import com.example.noteapplication.Data.NoteRepository
import com.example.noteapplication.adapters.NoteListAdapter
import com.example.noteapplication.adapters.NoteListViewModel
import com.example.noteapplication.adapters.NoteListViewModelFactory
import com.example.noteapplication.databinding.NoteListFragmentBinding

import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class NoteListFragment : Fragment(){
    private lateinit var noteListViewModelFactory: NoteListViewModelFactory
    private lateinit var viewModel : NoteListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
            .inflate<NoteListFragmentBinding>(inflater,R.layout.note_list_fragment,container,false)
        setHasOptionsMenu(false)
        val application = requireNotNull(requireActivity().application)
        val noteRepository = NoteRepository(application)
        noteListViewModelFactory = NoteListViewModelFactory(application)
        viewModel = ViewModelProviders.of(this,noteListViewModelFactory).get(NoteListViewModel::class.java)

        //recyclerView Handler
        val noteAdapter : NoteListAdapter = NoteListAdapter()
        val recyclerView = binding.noteRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = noteAdapter



        hideActionBar()
        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
           noteAdapter.setNewNotes(it)
        })





        binding.addNoteFab.setOnClickListener {
            Timber.d("AddNote FAB is clicked !")
            closeFab(binding)
            it.findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)

        }

        //TODO 2 Call onClickItemListener to handle the BottomAppLayout


        return binding.root
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checkState = view.isChecked

        }


    }

    private fun closeFab(binding : NoteListFragmentBinding) {
        val fab = binding.addNoteFab

        val closeAnim = AnimationUtils.loadAnimation(context, R.anim.open_anim)
        fab.animation = closeAnim
        fab.startAnimation(fab.animation)
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }




}
