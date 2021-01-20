package com.sergeybogdanec.adidasstore.ui.fragment.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeybogdanec.adidasstore.repo.CartRepo
import com.sergeybogdanec.adidasstore.repo.ProductsRepo
import com.sergeybogdanec.adidasstore.ui.item.ProductItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductsViewModel : ViewModel() {

    private val _items: MutableLiveData<List<ProductItem>> = MutableLiveData()
    val items: LiveData<List<ProductItem>> = _items

    private var baseItems: List<ProductItem> = listOf()

    val errorEvent = MutableSharedFlow<String>()

    init {
        viewModelScope.launch() {
            try {
                baseItems = ProductsRepo.getAllProducts().map { product ->
                    ProductItem(
                        product.name,
                        product.description,
                        product.collection,
                        product.size,
                        product.price,
                        product.pictureUrl,
                        product.type
                    ) {
                        viewModelScope.launch {
                            try {
                                CartRepo.addToCart(product)
                                errorEvent.emit("Товар добавлен в корзину!")
                            } catch (e: Exception) {
                                errorEvent.emit(e.message ?: "Неизвестная ошибка")
                            }
                        }
                    }
                }.also(_items::postValue)
            } catch (e: Throwable) {
                errorEvent.emit(e.message ?: "Неизвестная ошибка!")
            }
        }
    }

    fun filter(tabIndex: Int) {
        if (tabIndex == 0) {
            _items.postValue(baseItems)
        } else {
            _items.postValue(baseItems.filter { it.type == tabIndex - 1 })
        }
    }

}
