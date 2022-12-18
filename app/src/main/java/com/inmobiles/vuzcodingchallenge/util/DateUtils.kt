package com.inmobiles.vuzcodingchallenge.util

import android.app.DatePickerDialog
import android.content.Context
import java.util.*


object DateUtils {

    fun setLimitInDatePicker(context: Context): DatePickerDialog {
        val calendar: Calendar = Calendar.getInstance()

        val mDialog = DatePickerDialog(context, { _, mYear, mMonth, mDay ->
            calendar[Calendar.YEAR] = mYear
            calendar[Calendar.MONTH] = mMonth
            calendar[Calendar.DAY_OF_MONTH] = mDay
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])

        val currentYear: Int = calendar.get(Calendar.YEAR)
        val currentMonth: Int = calendar.get(Calendar.MONTH)
        val currentDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val minYear = currentYear - 99
        calendar.set(minYear, currentMonth, currentDay)

        mDialog.datePicker.minDate = calendar.timeInMillis

        val maxYear = currentYear - 18

        calendar.set(maxYear, currentMonth, currentDay)
        mDialog.datePicker.maxDate = calendar.timeInMillis

        return mDialog
    }
}