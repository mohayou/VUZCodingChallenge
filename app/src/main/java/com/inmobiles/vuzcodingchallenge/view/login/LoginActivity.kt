package com.inmobiles.vuzcodingchallenge.view.login

import android.content.Intent
import android.content.res.Resources.ID_NULL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputLayout
import com.inmobiles.vuzcodingchallenge.R
import com.inmobiles.vuzcodingchallenge.databinding.ActivityLoginBinding
import com.inmobiles.vuzcodingchallenge.util.FieldValidator
import com.inmobiles.vuzcodingchallenge.util.Status
import com.inmobiles.vuzcodingchallenge.view.MainActivity
import com.inmobiles.vuzcodingchallenge.view.registration.RegistrationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    val loginViewModel : LoginViewModel by viewModels()


    companion object{
        @JvmStatic
        @BindingAdapter("error")
        internal fun TextInputLayout.setError(@StringRes errorRes: Int) {
            error = errorRes.takeUnless { it == ID_NULL }?.let { resources.getString(it) }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        )
        binding.lifecycleOwner = this
        binding.viewmodel = loginViewModel

//        btn_login.setOnClickListener {
//
//            validateFields()
//        }

        btn_register.setOnClickListener {
            startActivity(Intent(baseContext,RegistrationActivity::class.java))
        }

        observeLogin()
    }

    private fun observeLogin() {
        loginViewModel.loginLiveData.observe(this) {
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

    private fun validateFields() {
        if (!Patterns.EMAIL_ADDRESS.matcher(et_email.text).matches()) {
            et_email.error = resources.getString(R.string.invalid_email)
        } else if (!FieldValidator.isPasswordValid(et_password.text.toString().trim())) {
            et_password.error = resources.getString(R.string.wrong_password)
        }
        else{
            startActivity(Intent(baseContext,MainActivity::class.java))
            finishAffinity()
        }
    }
}