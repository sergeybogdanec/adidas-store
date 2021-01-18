package com.sergeybogdanec.adidasstore.ui.item

import android.view.View
import com.bumptech.glide.Glide
import com.sergeybogdanec.adidasstore.R
import com.sergeybogdanec.adidasstore.databinding.ItemProductBinding
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder

data class ProductItem(
    val name: String,
    val description: String,
    val collection: String,
    val size: Int,
    val price: Int,
    val pictureLink: String,
    val type: Int,
    val onButtonClick: () -> Unit
): BindableItem<ItemProductBinding>() {

    override fun bind(binding: ItemProductBinding, position: Int) {
        with (binding) {

            tvName.text = name
            tvDescription.text = description
            tvCollection.text = collection

            with (root.context) {
                tvSize.text = getString(R.string.size_placeholder, size)
                tvPrice.text = getString(R.string.price_placeholder, price)
            }

            Glide.with(root)
                .load(pictureLink)
                .placeholder(R.drawable.ic_baseline_photo_24)
                .into(binding.imageView)

            bAdd.setOnClickListener {
                onButtonClick()
            }

        }
    }

    override fun getLayout(): Int {
        return R.layout.item_product
    }

    override fun initializeViewBinding(view: View): ItemProductBinding {
        return ItemProductBinding.bind(view)
    }

    override fun unbind(viewHolder: GroupieViewHolder<ItemProductBinding>) {
        Glide.with(viewHolder.itemView).clear(viewHolder.binding.imageView)
        super.unbind(viewHolder)
    }

}
