package com.sergeybogdanec.adidasstore.model

import java.util.*

data class Order(
    var date: Date = Date(),
    var clientId: String = "",
    var products: List<Product> = listOf()
)
