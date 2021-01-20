package com.sergeybogdanec.adidasstore.ui.item

import android.view.View
import com.bumptech.glide.Glide
import com.sergeybogdanec.adidasstore.R
import com.sergeybogdanec.adidasstore.databinding.ItemCartItemBinding
import com.sergeybogdanec.adidasstore.databinding.ItemProductBinding
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder

data class CartItemItem(
    val itemId: String,
    val name: String,
    val description: String,
    val collection: String,
    val size: Int,
    val price: Int,
    val pictureLink: String,
    val type: Int,
    var count: Int,
    val onCountChanged: (Int) -> Unit
): BindableItem<ItemCartItemBinding>() {

    override fun bind(binding: ItemCartItemBinding, position: Int) {
        with (binding) {

            tvName.text = name
            tvDescription.text = description
            tvCollection.text = collection
            tvCount.text = count.toString()

            with (root.context) {
                tvSize.text = getString(R.string.size_placeholder, size)
                tvPrice.text = getString(R.string.price_placeholder, price)
            }

            Glide.with(root)
                .load(pictureLink)
                .placeholder(R.drawable.ic_baseline_photo_24)
                .into(binding.imageView)

            bAdd.setOnClickListener {
                onCountChanged(++count)
                tvCount.text = count.toString()
            }

            bSub.setOnClickListener {
                if (count-- < 0 ) count = 0
                onCountChanged(count)
                tvCount.text = count.toString()
            }

        }
    }

    override fun getLayout(): Int {
        return R.layout.item_cart_item
    }

    override fun initializeViewBinding(view: View): ItemCartItemBinding {
        return ItemCartItemBinding.bind(view)
    }

    override fun unbind(viewHolder: GroupieViewHolder<ItemCartItemBinding>) {
        Glide.with(viewHolder.itemView).clear(viewHolder.binding.imageView)
        super.unbind(viewHolder)
    }

}
