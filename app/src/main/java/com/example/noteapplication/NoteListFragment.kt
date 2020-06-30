package com.example.noteapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.Data.Note
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


        //ViewModel init
        val application = requireNotNull(requireActivity().application)
        noteListViewModelFactory = NoteListViewModelFactory(application)
        viewModel = ViewModelProviders.of(this,noteListViewModelFactory).get(NoteListViewModel::class.java)

        //setup the RecyclerView
        val recyclerView = binding.noteRecyclerview
        val noteAdapter : NoteListAdapter = NoteListAdapter()
        noteAdapter.setCheckBoxListener(object : NoteListAdapter.onNoteClickListener{

            override fun setNoteItemClick(note: Note, isCheck: Boolean) {
                if (isCheck) {
                    viewModel.onCheckStatusChange(note.id, false)
                } else {
                    viewModel.onCheckStatusChange(note.id, true)
                }
            }

        })
        noteAdapter.notifyDataSetChanged()


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = noteAdapter
        recyclerView.setHasFixedSize(true)

        //observer ListChanges in Local database and update newNoteList
        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {newList:List<Note>->
           noteAdapter.setNewNotes(newList)
        })









        binding.addNoteFab.setOnClickListener {
            Timber.d("AddNote FAB is clicked !")
            closeFab(binding)
            it.findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)

        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
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
