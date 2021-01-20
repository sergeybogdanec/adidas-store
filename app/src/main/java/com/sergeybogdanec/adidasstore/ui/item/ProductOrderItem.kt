package com.sergeybogdanec.adidasstore.ui.item

import android.view.View
import com.bumptech.glide.Glide
import com.sergeybogdanec.adidasstore.R
import com.sergeybogdanec.adidasstore.databinding.ItemProductBinding
import com.sergeybogdanec.adidasstore.databinding.ItemProductOrderBinding
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder

data class ProductOrderItem(
    val name: String,
    val description: String,
    val collection: String,
    val size: Int,
    val price: Int,
    val pictureLink: String,
    val type: Int,
    val count: Int
): BindableItem<ItemProductOrderBinding>() {
    override fun bind(viewBinding: ItemProductOrderBinding, position: Int) {
        with (viewBinding) {
            tvName.text = name
            tvDescription.text = description
            tvCollection.text = collection

            with (root.context) {
                tvSize.text = getString(R.string.size_placeholder, size)
                tvPrice.text = getString(R.string.price_placeholder, price)
                tvCount.text = getString(R.string.count_placeholder, count)
            }

            Glide.with(root)
                .load(pictureLink)
                .placeholder(R.drawable.ic_baseline_photo_24)
                .into(imageView)
        }
    }

    override fun unbind(viewHolder: GroupieViewHolder<ItemProductOrderBinding>) {
        Glide.with(viewHolder.itemView).clear(viewHolder.binding.imageView)
        super.unbind(viewHolder)
    }

    override fun getLayout(): Int = R.layout.item_product_order

    override fun initializeViewBinding(view: View): ItemProductOrderBinding {
        return ItemProductOrderBinding.bind(view)
    }
}
