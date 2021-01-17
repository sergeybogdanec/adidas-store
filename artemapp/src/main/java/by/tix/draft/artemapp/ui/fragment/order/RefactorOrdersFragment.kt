package by.tix.draft.artemapp.ui.fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.tix.draft.artemapp.R
import by.tix.draft.artemapp.databinding.FragmentOrdersBinding
import by.tix.draft.artemapp.databinding.FragmentRefactorOrdersBinding
import by.tix.draft.artemapp.repo.MainRepo


class RefactorOrdersFragment : Fragment() {

    private lateinit var binding: FragmentRefactorOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRefactorOrdersBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

}