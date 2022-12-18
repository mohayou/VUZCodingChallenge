package com.inmobiles.vuzcodingchallenge.view.registration

import android.util.Patterns
import androidx.lifecycle.*
import com.inmobiles.vuzcodingchallenge.R
import com.inmobiles.vuzcodingchallenge.model.RegistrationResponse
import com.inmobiles.vuzcodingchallenge.repo.MainRepository
import com.inmobiles.vuzcodingchallenge.util.FieldValidator
import com.inmobiles.vuzcodingchallenge.util.Resource
import com.inmobiles.vuzcodingchallenge.view.livedata_validator.LiveDataValidator
import com.inmobiles.vuzcodingchallenge.view.livedata_validator.LiveDataValidatorResolver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor (private val mainRepository: MainRepository) : ViewModel() {


    private val _registrationLiveData = MutableLiveData<Resource<RegistrationResponse>>()
    val registrationLiveData : LiveData<Resource<RegistrationResponse>>
        get() = _registrationLiveData

    val usernameLiveData = MutableLiveData<String>()
    val usernameValidator = LiveDataValidator(usernameLiveData).apply {
        addRule(R.string.invalid_email) { !it.isNullOrBlank() && !Patterns.EMAIL_ADDRESS.matcher(it).matches() }
        addRule(R.string.field_required) { it.isNullOrBlank() }
    }
    val passwordLiveData = MutableLiveData<String>()
    val passwordValidator = LiveDataValidator(passwordLiveData).apply {
        addRule(R.string.wrong_password) { !it.isNullOrBlank() && !FieldValidator.isPasswordValid(it!!) }
                addRule(R.string.field_required) { it.isNullOrBlank() }
    }
//
    val isLoginFormValidMediator = MediatorLiveData<Boolean>()
//
    init {
        isLoginFormValidMediator.value = false
        isLoginFormValidMediator.addSource(usernameLiveData) { validateForm() }
        isLoginFormValidMediator.addSource(passwordLiveData) { validateForm() }
    }

    fun validateForm() {
        val validators = listOf(usernameValidator, passwordValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        isLoginFormValidMediator.value = validatorResolver.isValid()

        if (validatorResolver.isValid())

            performRegistration()

    }

    private fun performRegistration()  = viewModelScope.launch {
        _registrationLiveData.value =   mainRepository.registration()
    }
}