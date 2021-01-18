package com.sergeybogdanec.adidasstore.ui.fragment.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeybogdanec.adidasstore.repo.ProductsRepo
import com.sergeybogdanec.adidasstore.ui.item.ProductItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _items: MutableLiveData<List<ProductItem>> = MutableLiveData()
    val items: LiveData<List<ProductItem>> = _items

    val errorEvent = MutableSharedFlow<String>()

    init {
        viewModelScope.launch() {
            try {
                _items.postValue(
                    ProductsRepo.getAllProducts().map { product ->
                        ProductItem(
                            product.name,
                            product.description,
                            product.collection,
                            product.size,
                            product.price,
                            product.pictureUrl
                        ) {

                        }
                    }
                )
            } catch (e: Throwable) {
                errorEvent.emit(e.message ?: "Неизвестная ошибка!")
            }
        }
    }

}
