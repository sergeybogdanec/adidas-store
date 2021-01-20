package com.sergeybogdanec.adidasstore.ui.fragment.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sergeybogdanec.adidasstore.databinding.FragmentOrdersBinding

class OrdersFragment: Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[OrdersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        return binding.root
    }

}
