package com.mbs.sistemainfo.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbs.sistemainfo.databinding.RecyclerContentBinding
import com.mbs.sistemainfo.utils.listeners.OnCustomersListener
import com.mbs.sistemainfo.utils.models.CustomerModel

class CustomersAdapter : RecyclerView.Adapter<CustomersAdapter.ViewHolder>() {

    private var customerList: AsyncListDiffer<CustomerModel> = AsyncListDiffer(this, DiffCallBack)
    private lateinit var listener: OnCustomersListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = RecyclerContentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(customerList.currentList[position])
    }

    override fun getItemCount(): Int {
        return customerList.currentList.size
    }

    fun updateList(list: List<CustomerModel>) {
        customerList.submitList(list)
    }

    fun setListener(listener: OnCustomersListener) {
        this.listener = listener
    }

    object DiffCallBack : DiffUtil.ItemCallback<CustomerModel>() {
        override fun areItemsTheSame(oldItem: CustomerModel, newItem: CustomerModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CustomerModel, newItem: CustomerModel): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: RecyclerContentBinding, private val listener: OnCustomersListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customer: CustomerModel) {
            binding.name.text = customer.name
            binding.cpf.text = customer.CPF
            binding.code.text = customer.code.toString()
            binding.phone.text = customer.phone
            binding.address.text = customer.address
            binding.deleteButton.setOnClickListener {
                customer.code?.let { code -> listener.onClick(code) }
            }
        }
    }
}
