package com.sergeybogdanec.adidasstore.ui.fragment.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sergeybogdanec.adidasstore.collectIn
import com.sergeybogdanec.adidasstore.databinding.FragmentProductsBinding

class ProductsFragment: Fragment() {

    private lateinit var binding: FragmentProductsBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[ProductsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        viewModel.errorEvent.collectIn(viewLifecycleOwner.lifecycleScope) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}
