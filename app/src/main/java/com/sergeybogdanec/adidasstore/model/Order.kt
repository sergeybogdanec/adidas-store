package com.sergeybogdanec.adidasstore.model

import java.util.*

data class Order(
    var date: Date = Date(),
    var clientId: String = "",
    var discount: Int = 0,
    var products: List<CartItem> = listOf()
)
