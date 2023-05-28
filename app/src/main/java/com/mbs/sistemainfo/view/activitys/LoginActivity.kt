package com.mbs.sistemainfo.view.activitys

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mbs.sistemainfo.R
import com.mbs.sistemainfo.viewmodels.LoginViewModel
import com.mbs.sistemainfo.databinding.ActivityLoginBinding
import com.mbs.sistemainfo.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupContactUsButton()
        setupSignInButton()
    }

    private fun setupSignInButton() {
        binding.signInButton.setOnClickListener {
            val user = binding.userEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (loginViewModel.validateLogin(user = user, password = password)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                hideKeyboard(this, binding.root)
                Snackbar.make(
                    binding.root,
                    getString(R.string.invalid_login_text),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupContactUsButton() {
        binding.contactUs.setOnClickListener {
            val uri = Uri.parse(getString(R.string.contact_me_url))
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        }
    }
}