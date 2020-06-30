package com.example.noteapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.Data.Note
import com.example.noteapplication.databinding.NoteItemBinding


// Display the List Of Items


class NoteListAdapter()
    : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    //interface to pass the onClick to the checkbox
   interface onNoteClickListener {
       fun setNoteItemClick(note : Note,isCheck : Boolean)
   }


    private var listOfNotes : List<Note> = ArrayList()

    //keep the onNoteListener
    private lateinit var mListener : onNoteClickListener

    //set new ItemList from the Database Query
    fun setNewNotes(newNote : List<Note>) {
        this.listOfNotes = newNote
        notifyDataSetChanged()
    }

    fun setCheckBoxListener(onNoteClickListener: onNoteClickListener) {
       this.mListener = onNoteClickListener
    }



    // View Holder need to cache the references to the Different view
    inner class NoteViewHolder
    internal constructor (val binding: NoteItemBinding, onNoteClickListener: onNoteClickListener)
        : RecyclerView.ViewHolder(binding.root) {


        val checkBox : CheckBox = binding.noteCheckBox
        val headerText : TextView = binding.textInfo


        // bind the data in xml with real clicked item
        fun bind(position: Int) {
            var note = listOfNotes[position]
            headerText.text = note.title
            checkBox.isChecked = note.isChecked
            checkBox.tag = note

            //get the CheckBox in View
            checkBox.setOnClickListener {
                val cb= it as CheckBox
                val note = cb.tag as Note

                if (mListener != null) {
                    mListener.setNoteItemClick(note,note.isChecked)
                }
            }

            binding.executePendingBindings()
        }



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //get called first time the RecyclerView is up. Layout needed to be inflate into View Class
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(layoutInflater, parent,false)

        return NoteViewHolder(binding,mListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //this callback will be called everytime the user scroll the screen to recycle new view form holder
        holder.bind(position)


    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }



}