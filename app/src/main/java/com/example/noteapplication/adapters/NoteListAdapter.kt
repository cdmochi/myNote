package com.example.noteapplication.adapters

import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.Data.Note
import com.example.noteapplication.NoteListFragment
import com.example.noteapplication.databinding.NoteItemBinding
import timber.log.Timber


// Display the List Of Items


class NoteListAdapter(): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    private var listOfNotes : List<Note> = ArrayList()

    fun setNewNotes(newNote : List<Note>) {
        this.listOfNotes = newNote
        notifyDataSetChanged()
    }



    // View Holder need to cache the references to the Different view
    class NoteViewHolder(binding : NoteItemBinding,listOfNote : List<Note>) : RecyclerView.ViewHolder(binding.root){
        val checkBox : CheckBox = binding.noteCheckBox
        val headerText : TextView = binding.textInfo

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //get called first time the RecyclerView is up. Layout needed to be inflate into View Class
        val layoutInflater = LayoutInflater.from(parent.context)
        val noteItemBinding = NoteItemBinding.inflate(layoutInflater, parent,false)

        return NoteViewHolder(noteItemBinding,listOfNotes)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //this callback will be called everytime the user scroll the screen to recycle new view form holder

        holder.headerText.text = listOfNotes[position].title
        holder.checkBox.isChecked = listOfNotes[position].isChecked


    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }


}