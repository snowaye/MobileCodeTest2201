package com.codigo.mobilecodetest.ui

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codigo.mobilecodetest.util.LiveDataResolver
import com.codigo.mobilecodetest.util.LiveDataValidator


class SignUpViewModel: ViewModel() {
    val firstNameLiveData = MutableLiveData<String>()
    val firstNameValidator = LiveDataValidator(firstNameLiveData).apply {
        addRule("First Name is required!") {it.isNullOrBlank()}
    }

    val lastNameLiveData = MutableLiveData<String>()
    val lastNameValidator = LiveDataValidator(lastNameLiveData).apply {
        addRule("Last Name is required!") {it.isNullOrBlank()}
    }

    val emailLiveData = MutableLiveData<String>()
     val emailValidator = LiveDataValidator(emailLiveData).apply {
        addRule("Email is required!") {it.isNullOrBlank()}
    }

    val dobLiveData = MutableLiveData<String>()
     val dobValidator = LiveDataValidator(dobLiveData).apply {
        addRule("Date of Birth is required!") {it.isNullOrBlank()}
    }

    val nationalityLiveData = MutableLiveData<String>()
     val nationalityValidator = LiveDataValidator(nationalityLiveData).apply {
        addRule("Nationality is required!") {it.isNullOrBlank()}
    }

    val residenceLiveData = MutableLiveData<String>()
     val residenceValidator = LiveDataValidator(residenceLiveData).apply {
        addRule("Residence Country is required!") {it.isNullOrBlank()}
    }

    val mobileNoLiveData = MutableLiveData<String>()
     val mobileNoValidator = LiveDataValidator(mobileNoLiveData).apply {
        addRule("Mobile Number is required!") {it.isNullOrBlank()}
    }

    val isSignUpForValidMediator = MediatorLiveData<Boolean>()

    init {
        isSignUpForValidMediator.value = false
        isSignUpForValidMediator.addSource(firstNameLiveData) {validateForm()}
        isSignUpForValidMediator.addSource(lastNameLiveData) {validateForm()}
        isSignUpForValidMediator.addSource(emailLiveData) {validateForm()}
        isSignUpForValidMediator.addSource(dobLiveData) {validateForm()}
        isSignUpForValidMediator.addSource(nationalityLiveData) {validateForm()}
        isSignUpForValidMediator.addSource(residenceLiveData) {validateForm()}
        isSignUpForValidMediator.addSource(mobileNoLiveData) {validateForm()}
    }

    fun validateForm() {
        val validators = listOf(firstNameValidator, lastNameValidator, emailValidator, dobValidator,
            nationalityValidator, residenceValidator, mobileNoValidator)
        val validatorResolver = LiveDataResolver(validators)
        isSignUpForValidMediator.value = validatorResolver.isValid()
    }

    fun firstNameEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                firstNameValidator.isValid()
            }
            true
        }
    }

    fun lastNameEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                lastNameValidator.isValid()
            }
            true
        }
    }

    fun emailEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                emailValidator.isValid()
            }
            true
        }
    }

    fun dobEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                dobValidator.isValid()
            }
            true
        }
    }

    fun nationalityEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                nationalityValidator.isValid()
            }
            true
        }
    }

    fun residenceEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                residenceValidator.isValid()
            }
            true
        }
    }

    fun mobileEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                mobileNoValidator.isValid()
            }
            true
        }
    }


}