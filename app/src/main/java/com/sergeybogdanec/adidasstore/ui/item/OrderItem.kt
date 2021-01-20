package com.sergeybogdanec.adidasstore.ui.item

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergeybogdanec.adidasstore.R
import com.sergeybogdanec.adidasstore.databinding.ItemOrderBinding
import com.sergeybogdanec.adidasstore.model.CartItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

data class OrderItem(
    val date: Date,
    val discount: Int,
    val products: List<CartItem>
): BindableItem<ItemOrderBinding>() {

    private val groupieAdapter: GroupieAdapter = GroupieAdapter()

    override fun bind(viewBinding: ItemOrderBinding, position: Int) {
        with (viewBinding) {
            with (root.context) {
                tvDate.text = SimpleDateFormat.getDateTimeInstance().format(date)
                tvDiscount.text = getString(R.string.discount, discount)
                val basePrice = products.sumBy { it.price * it.count }
                val price = (basePrice - basePrice * discount.toFloat() / 100f).toInt()
                tvPrice.text =getString(R.string.common_price, price)
                groupieAdapter.update(products.map {
                    ProductOrderItem(
                        it.name, it.description, it.collection, it.size, it.price, it.pictureUrl, it.type, it.count
                    )
                })
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_order

    override fun initializeViewBinding(view: View): ItemOrderBinding {
        return ItemOrderBinding.bind(view).apply {
            with (rvProductsList) {
                layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = groupieAdapter
            }
        }
    }

}
