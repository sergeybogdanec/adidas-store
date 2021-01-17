package by.tix.draft.artemapp.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.tix.draft.artemapp.collectIn
import by.tix.draft.artemapp.createProgressDialog
import by.tix.draft.artemapp.databinding.FragmentAuthBinding
import by.tix.draft.artemapp.repo.MainRepo
import by.tix.draft.artemapp.ui.activity.MainActivity

class AuthFragment: Fragment() {

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    private lateinit var binding: FragmentAuthBinding

    private val progress by lazy { createProgressDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        viewModel.errorEvent.collectIn(viewLifecycleOwner.lifecycleScope) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.progress.collectIn(viewLifecycleOwner.lifecycleScope) { isDialogVisible ->
            if (isDialogVisible) {
                progress.show()
            } else {
                progress.dismiss()
            }
        }

        viewModel.completion.collectIn(viewLifecycleOwner.lifecycleScope) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }


        return binding.root
    }

}
