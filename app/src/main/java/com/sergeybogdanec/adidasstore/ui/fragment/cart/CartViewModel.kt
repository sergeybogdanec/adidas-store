package com.sergeybogdanec.adidasstore.ui.fragment.cart

import androidx.lifecycle.*
import com.sergeybogdanec.adidasstore.model.CartItem
import com.sergeybogdanec.adidasstore.repo.CartRepo
import com.sergeybogdanec.adidasstore.repo.CouponRepo
import com.sergeybogdanec.adidasstore.ui.item.CartItemItem
import kotlinx.coroutines.launch

class CartViewModel: ViewModel() {

    private val _items = MutableLiveData<List<CartItemItem>>()
    val items: LiveData<List<CartItemItem>> = _items

    val coupon: MutableLiveData<String> = MutableLiveData()

    private val discount: MutableLiveData<Int> = MutableLiveData(0)

    val price = MediatorLiveData<Int>().apply {
        val priceCalculator: (List<CartItemItem>, Int) -> Int = { list, discount ->
            val basePrice = list.sumBy { it.count * it.price }
            (basePrice - basePrice * discount.toFloat() / 100f).toInt()
        }
        addSource(_items) { list ->
            value = priceCalculator(list, discount.value ?: 0)
        }
        addSource(discount) { discount ->
            value = priceCalculator(_items.value ?: listOf(), discount)
        }
    }

    init {
        viewModelScope.launch {
            _items.value = CartRepo.getAll().map { item ->
                CartItemItem(
                    item.id,
                    item.name,
                    item.description,
                    item.collection,
                    item.size,
                    item.price,
                    item.pictureUrl,
                    item.type,
                    item.count
                ) {
                    viewModelScope.launch {
                        CartRepo.update(item.copy(count = it).apply { id = item.id })
                        if (it == 0) {
                            _items.value = _items.value?.toMutableList()?.apply {
                                removeAll { it.itemId == item.id }
                            }
                        } else {
                            _items.value = _items.value
                        }
                    }
                }
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            CartRepo.clearCart()
            _items.value = listOf()
        }
    }

    fun applyCoupon() {
        viewModelScope.launch {
            coupon.value?.let { couponValue ->
                CouponRepo.getCoupon(couponValue)
            }?.let { coupon ->
                discount.value = coupon.discount
            }
        }
    }

}
