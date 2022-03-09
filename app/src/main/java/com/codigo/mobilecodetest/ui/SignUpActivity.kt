package com.codigo.mobilecodetest.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.bind
import com.codigo.mobilecodetest.R
import com.codigo.mobilecodetest.databinding.ActivitySignUpBinding
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this@SignUpActivity)[SignUpViewModel::class.java]
        binding.viewModel =viewModel

        binding.toolbar.setOnClickListener {
            navigateBackToStartup()
        }
        binding.btnCreateNewAccount.setOnClickListener {
            viewModel.validateForm()
        }
    }

    private fun navigateBackToStartup() {
        finish()
    }


}