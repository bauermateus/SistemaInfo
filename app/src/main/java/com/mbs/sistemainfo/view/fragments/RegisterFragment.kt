package com.mbs.sistemainfo.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.mbs.sistemainfo.databinding.FragmentRegisterBinding
import com.mbs.sistemainfo.utils.models.CustomerModel
import com.mbs.sistemainfo.utils.hideKeyboard
import com.mbs.sistemainfo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

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
        binding.registerButton.setOnClickListener {
            val name = binding.name.text
            val cpf = binding.cpf.text
            val phone = binding.phone.text
            val address = binding.address.text
            hideKeyboard(requireContext(), binding.root)
            binding.root.clearFocus()

            if (!name.isNullOrBlank() && !cpf.isNullOrBlank()) {
                if (cpf.length == 11) {
                    viewModel.insertCustomer(
                        CustomerModel(
                            code = null,
                            name = name.toString(),
                            CPF = cpf.toString(),
                            phone = phone.toString(),
                            address = address.toString()
                        )
                    )
                    Snackbar.make(
                        binding.root,
                        "Usuário $name cadastrado com sucesso.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    name.clear()
                    cpf.clear()
                    phone?.clear()
                    address?.clear()
                } else Snackbar.make(binding.root, "CPF deve conter 11 dígitos", Snackbar.LENGTH_LONG).show()
            } else Snackbar.make(binding.root, "Por favor, preencha todos os campos obrigatórios.", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}