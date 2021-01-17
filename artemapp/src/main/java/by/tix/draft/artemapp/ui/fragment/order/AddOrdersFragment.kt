package by.tix.draft.artemapp.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.tix.draft.artemapp.databinding.FragmentAddOrdersBinding
import by.tix.draft.artemapp.databinding.FragmentRefactorOrdersBinding


class AddOrdersFragment : Fragment() {

    private lateinit var binding: FragmentAddOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddOrdersBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    companion object {
        private const val REQUEST_IMAGE = 1
    }

}