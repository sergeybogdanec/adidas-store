package by.tix.draft.artemapp.ui.fragment.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.tix.draft.artemapp.R
import by.tix.draft.artemapp.databinding.FragmentAuthBinding
import by.tix.draft.artemapp.databinding.FragmentOrdersBinding
import by.tix.draft.artemapp.model.Order
import by.tix.draft.artemapp.model.Product
import by.tix.draft.artemapp.repo.MainRepo
import by.tix.draft.artemapp.ui.activity.RefactorOrdersActivity


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

        loadOrders()

        binding.vOrdersRefresh.setOnRefreshListener {
            loadOrders()
        }

        binding.vAddOrders.setOnClickListener {
            startActivity(Intent(requireContext(),RefactorOrdersActivity::class.java))
        }

        setUpOrders()




        return binding.root
    }

    private fun setUpOrders() {
//        binding.run {
//            vOrders.apply {
//                layoutManager = LinearLayoutManager(
//                    requireContext(),
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//
//                // todo set vup adapter
//            }
//        }
    }


    private fun loadOrders(){
        binding.vOrdersRefresh.isRefreshing = true
        MainRepo.getOrders(
            {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                binding.vOrdersRefresh.isRefreshing = false
            },
            {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                binding.vOrdersRefresh.isRefreshing = false
            }
        )
    }
}