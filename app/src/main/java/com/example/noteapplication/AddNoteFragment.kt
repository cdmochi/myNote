package com.example.noteapplication

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.noteapplication.Data.Note
import com.example.noteapplication.databinding.AddNoteFragmentBinding
import com.example.noteapplication.dialogs.TimePickerFragment
import com.google.android.material.datepicker.MaterialDatePicker
import timber.log.Timber
import java.time.LocalDateTime
import java.time.LocalTime

class AddNoteFragment : Fragment() , TimePickerDialog.OnTimeSetListener{

    private lateinit var addNoteFactory : AddNoteViewModelFactory
    private lateinit var viewModel : AddNoteViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<AddNoteFragmentBinding>(inflater,R.layout.add_note_fragment,container,false)
        val bottomNav = binding.addNoteBottomBar
        val addnoteNav = binding.addNoteBottomBar
        val addnoteFab = binding.menuFab
        val header = binding.headerInput
        val description = binding.descriptionText

        val application = requireActivity().application
        addNoteFactory = AddNoteViewModelFactory(application)
        viewModel = ViewModelProviders.of(this,addNoteFactory).get(AddNoteViewModel::class.java)

        addnoteFab.setOnClickListener {
            var title = header.text.toString()
            var des = description.text.toString()
            viewModel.setNote(title, des)
            viewModel.saveNote()
            it.findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
        }






        bottomNav.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                Timber.d("the time menu is Clicked")
                return when (item!!.itemId) {

                    R.id.time_menu -> {
                        val timePicker: DialogFragment = TimePickerFragment()
                        TimePickerFragment.getInstance().setTimePicker(this@AddNoteFragment)
                        timePicker.show(childFragmentManager, "time picker")
                        true

                    }
                    R.id.date_menu -> {

                        val builder= MaterialDatePicker.Builder.datePicker().build()
                        builder.show(childFragmentManager,"date picker")
                        return true
                    }
                    else ->false
                }
            }

        })


        return binding.root


    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

    }


}