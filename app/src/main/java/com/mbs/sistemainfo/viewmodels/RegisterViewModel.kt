package com.mbs.sistemainfo.viewmodels

import androidx.lifecycle.ViewModel
import com.mbs.sistemainfo.R
import com.mbs.sistemainfo.utils.models.RegisterScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        RegisterScreenState(
            nameField = false,
            nameFieldColor = R.color.red,
            nameDrawableRes = R.drawable.error_24_red,
            cpfField = false,
            cpfFieldColor = R.color.red,
            cpfDrawableRes = R.drawable.error_24_red,
            phoneField = true,
            phoneFieldColor = R.color.green,
            phoneDrawableRes = R.drawable.check_24_green,
            addressField = true,
            registerButtonColor = R.color.light_gray
        )
    )

    val uiState = _uiState.asStateFlow()

    fun validateName(name: String) {
        _uiState.value = _uiState.value.copy(nameField = name.isNotBlank())
        _uiState.value =
            _uiState.value.copy(nameFieldColor = if (uiState.value.nameField) R.color.green else R.color.red)
        _uiState.value =
            _uiState.value.copy(nameDrawableRes = if (uiState.value.nameField) R.drawable.check_24_green else R.drawable.error_24_red)
        validateButton()
    }

    fun validateCpf(cpf: String) {
        _uiState.value = _uiState.value.copy(cpfField = cpf.length == 11)
        _uiState.value =
            _uiState.value.copy(cpfFieldColor = if (uiState.value.cpfField) R.color.green else R.color.red)
        _uiState.value =
            _uiState.value.copy(cpfDrawableRes = if (uiState.value.cpfField) R.drawable.check_24_green else R.drawable.error_24_red)
        validateButton()
    }

    fun validatePhone(phone: String) {
        _uiState.value = _uiState.value.copy(phoneField = phone.length == 11 || phone.isBlank())
        _uiState.value =
            _uiState.value.copy(phoneFieldColor = if (uiState.value.phoneField) R.color.green else R.color.red)
        _uiState.value =
            _uiState.value.copy(phoneDrawableRes = if (uiState.value.phoneField) R.drawable.check_24_green else R.drawable.error_24_red)
        validateButton()
    }

    private fun validateButton() {
        _uiState.value = _uiState.value.copy(
            registerButtonColor =
            if (_uiState.value.nameField &&
                _uiState.value.phoneField &&
                _uiState.value.cpfField &&
                _uiState.value.addressField) R.color.red
            else R.color.light_gray
        )
    }
}