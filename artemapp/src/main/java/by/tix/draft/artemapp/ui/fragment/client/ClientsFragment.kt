package by.tix.draft.artemapp.ui.fragment.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.tix.draft.artemapp.R
import by.tix.draft.artemapp.databinding.FragmentClientsBinding
import by.tix.draft.artemapp.databinding.FragmentOrdersBinding
import by.tix.draft.artemapp.model.Client
import by.tix.draft.artemapp.repo.MainRepo

class ClientsFragment : Fragment() {

    private lateinit var binding: FragmentClientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientsBinding.inflate(
            inflater,
            container,
            false
        )

        loadOrders()

        binding.vClientRefresh.setOnRefreshListener {
            loadOrders()
        }

        binding.vAddClients.setOnClickListener {

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
        binding.vClientRefresh.isRefreshing = true
        MainRepo.getClients(
            {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                binding.vClientRefresh.isRefreshing = false
            },
            {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                binding.vClientRefresh.isRefreshing = false
            }
        )
    }
}