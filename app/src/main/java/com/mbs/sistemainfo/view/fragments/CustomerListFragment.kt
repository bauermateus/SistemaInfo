package com.mbs.sistemainfo.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.mbs.sistemainfo.databinding.FragmentCustomerListBinding
import com.mbs.sistemainfo.utils.listeners.OnCustomersListener
import com.mbs.sistemainfo.view.adapters.CustomersAdapter
import com.mbs.sistemainfo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerListFragment : Fragment() {

    private var _binding: FragmentCustomerListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CustomersAdapter()
        val recyclerView = binding.customerRecyclerView
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.uiState.collect { customers ->
                adapter.updateList(customers)
            }
        }
        adapter.setListener(listener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val listener = object: OnCustomersListener {
        override fun onClick(code: Int) {
            viewModel.deleteCustomer(code)
        }
    }
}