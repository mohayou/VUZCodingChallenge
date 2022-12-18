package com.inmobiles.vuzcodingchallenge.util

import android.util.Log

object FieldValidator {

    fun isPasswordValid(string: String):Boolean= string.length in 6..12

}