package com.inmobiles.vuzcodingchallenge.view.registration

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputLayout
import com.inmobiles.vuzcodingchallenge.R
import com.inmobiles.vuzcodingchallenge.databinding.ActivityRegistrationBinding
import com.inmobiles.vuzcodingchallenge.util.DateUtils
import com.inmobiles.vuzcodingchallenge.util.Status
import com.inmobiles.vuzcodingchallenge.view.MainActivity
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    val registrationViewModel: RegistrationViewModel by viewModels()

    companion object {
        @JvmStatic
        @BindingAdapter("error")
        internal fun TextInputLayout.setError(@StringRes errorRes: Int) {
            error = errorRes.takeUnless { it == Resources.ID_NULL }?.let { resources.getString(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityRegistrationBinding>(
            this,
            R.layout.activity_registration
        ) // 1
        binding.lifecycleOwner = this
        binding.viewmodel = registrationViewModel


        et_dob.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                showDatePickerDialog()
                return v?.onTouchEvent(event) ?: true
            }
        })

        observeLogin()
    }

    private fun observeLogin() {
        registrationViewModel.registrationLiveData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    startActivity(Intent(baseContext, MainActivity::class.java))
                    finishAffinity()
                }

                Status.ERROR -> {
                    Toast.makeText(baseContext, it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

                Status.LOADING -> {

                }
            }

        }
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DateUtils.setLimitInDatePicker(this)

        datePickerDialog.show()
    }
}