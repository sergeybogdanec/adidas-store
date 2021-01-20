package com.sergeybogdanec.adidasstore.ui.fragment.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sergeybogdanec.adidasstore.repo.OrderRepo
import com.sergeybogdanec.adidasstore.ui.item.OrderItem
import kotlinx.coroutines.launch

class OrdersViewModel: ViewModel() {

    val items: MutableLiveData<List<OrderItem>> = MutableLiveData()

    val name = MutableLiveData<String>()

    val email = MutableLiveData<String>()

    val auth = Firebase.auth

    init {
        viewModelScope.launch {
            items.value = OrderRepo.getAll().map {
                OrderItem(it.date, it.discount, it.products)
            }.sortedBy { it.date }
            name.value = auth.currentUser?.displayName
            email.value = auth.currentUser?.email
        }
    }

}
