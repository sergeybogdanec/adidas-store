package by.tix.draft.artemapp.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.tix.draft.artemapp.R
import by.tix.draft.artemapp.databinding.FragmentAuthBinding
import by.tix.draft.artemapp.databinding.FragmentOrdersBinding
import by.tix.draft.artemapp.model.Order
import by.tix.draft.artemapp.model.Product
import by.tix.draft.artemapp.repo.MainRepo


class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(
            inflater,
            container,
            false
        )

        setUpOrders()

        MainRepo.addOrders(
            listOf(Order(), Order()),
            {

            },
            {

            }
        )


        MainRepo.getOrders(
            {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        )

        return binding.root
    }

    private fun setUpOrders() {
        binding.run {
            vOrders.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )

                // todo set vup adapter
            }
        }
    }
}