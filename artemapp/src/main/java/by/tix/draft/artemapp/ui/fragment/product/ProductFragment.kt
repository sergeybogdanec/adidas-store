package by.tix.draft.artemapp.ui.fragment.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.tix.draft.artemapp.databinding.FragmentProductBinding
import by.tix.draft.artemapp.model.Product
import by.tix.draft.artemapp.repo.MainRepo

class ProductFragment: Fragment() {

    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)



        loadOrders()

        binding.vProductsRefresh.setOnRefreshListener {
            loadOrders()
        }

        binding.vAddOrders.setOnClickListener {

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
        binding.vProductsRefresh.isRefreshing = true
        MainRepo.getProducts(
            {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                binding.vProductsRefresh.isRefreshing = false
            },
            {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                binding.vProductsRefresh.isRefreshing = false
            }
        )
    }
}
