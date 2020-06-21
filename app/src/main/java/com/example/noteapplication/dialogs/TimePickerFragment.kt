package com.example.noteapplication.dialogs

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {

    private var timePickerInterface : TimePickerDialog.OnTimeSetListener? = null


    companion object {

        fun getInstance() : TimePickerFragment{
            return TimePickerFragment()
        }

    }

    fun setTimePicker(timePicker: TimePickerDialog.OnTimeSetListener) {
        this.timePickerInterface = timePicker
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val userFormat = DateFormat.is24HourFormat(activity)


       return TimePickerDialog(activity,timePickerInterface, hour,minute,userFormat)

    }








}