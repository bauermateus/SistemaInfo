package com.mbs.sistemainfo.view.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.mbs.sistemainfo.R
import com.mbs.sistemainfo.databinding.FragmentRegisterBinding
import com.mbs.sistemainfo.utils.hideKeyboard
import com.mbs.sistemainfo.utils.models.CustomerModel
import com.mbs.sistemainfo.viewmodels.MainViewModel
import com.mbs.sistemainfo.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiSetup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /** Configura os ícones e cores iniciais imutáveis. */
    private fun setupIconVisibility() {
        with(binding) {
            nameLayout.isEndIconVisible = true
            cpfLayout.isEndIconVisible = true
            phoneLayout.isEndIconVisible = true
            addressLayout.isEndIconVisible = true
        }
    }

    /** Configura o viewModel para ir validando os campos em tempo real. */
    private fun realTimeValidate() {
        binding.name.doAfterTextChanged {
            registerViewModel.validateName(it.toString())
        }
        binding.cpf.doAfterTextChanged {
            registerViewModel.validateCpf(it.toString())
        }
        binding.phone.doAfterTextChanged {
            registerViewModel.validatePhone(it.toString())
        }
    }

    /** Faça a inserção do cliente, exiba a mensagem de sucesso e limpe todos os campos de input. */
    private fun insertCustomerAndClearFields() {
        mainViewModel.insertCustomer(
            CustomerModel(
                code = null,
                name = binding.name.text.toString(),
                CPF = binding.cpf.text.toString(),
                phone = binding.phone.text.toString(),
                address = binding.address.text.toString()
            )
        )
        showValidationSnackBar(getString(R.string.user_field_hint) + " ${binding.name.text} " +
                getString(R.string.registered_success_text) + binding.cpf.text?.substring(7, 11))
        with(binding) {
            name.text?.clear()
            cpf.text?.clear()
            phone.text?.clear()
            address.text?.clear()
        }
    }

    /** Observa e coleta os dados conforme validação da função realTimeValidate()
     *  e ajusta as cores da UI de acordo com a validação */
    private fun observeAndStyleUi() {
        lifecycleScope.launch {
            registerViewModel.uiState.collect { uiState ->
                with(binding.nameLayout) {
                    hintTextColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), uiState.nameFieldColor))
                    boxStrokeColor = ContextCompat.getColor(requireContext(), uiState.nameFieldColor)
                    setEndIconDrawable(uiState.nameDrawableRes)
                }

                with(binding.cpfLayout) {
                    hintTextColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), uiState.cpfFieldColor))
                    boxStrokeColor = ContextCompat.getColor(requireContext(), uiState.cpfFieldColor)
                    setEndIconDrawable(uiState.cpfDrawableRes)
                }


                with(binding.phoneLayout) {
                    hintTextColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), uiState.phoneFieldColor))
                    boxStrokeColor = ContextCompat.getColor(requireContext(), uiState.phoneFieldColor)
                    setEndIconDrawable(uiState.phoneDrawableRes)
                }

                binding.registerButton.setBackgroundColor(ContextCompat.getColor(requireContext(), uiState.registerButtonColor))

                //Evento de clique do botão cadastrar
                binding.registerButton.setOnClickListener {
                    hideKeyboard(requireContext(), binding.root)
                    binding.root.clearFocus()

                    // Verifica se todos os campos foram preenchidos corretamente e exibe alertas caso algo estiver errado.
                    if (!uiState.nameField) {
                        showValidationSnackBar(getString(R.string.name_warn))
                        return@setOnClickListener
                    }

                    if (!uiState.cpfField) {
                        showValidationSnackBar(getString(R.string.cpf_length_warn))
                        return@setOnClickListener
                    }

                    if (!uiState.phoneField) {
                        showValidationSnackBar(getString(R.string.phone_warn))
                        return@setOnClickListener
                    }
                    // Se tudo estiver certo, insere o cliente e limpa os campos.
                    insertCustomerAndClearFields()
                }
            }
        }
    }

    private fun uiSetup() {
        setupIconVisibility()
        realTimeValidate()
        observeAndStyleUi()
    }

    /** Mostra a snackBar de validação dos campos. */
    private fun showValidationSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}