package com.sergeybogdanec.adidasstore.ui.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sergeybogdanec.adidasstore.databinding.FragmentCartBinding

class CartFragment: Fragment() {

    private lateinit var binding: FragmentCartBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        return binding.root
    }

}
