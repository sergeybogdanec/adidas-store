package com.sergeybogdanec.adidasstore.model

import java.util.*

data class Product(
    var name: String = "",
    var description: String = "",
    var size: Int = 0,
    var collection: String = "",
    var price: Int = 0
) {
    var id: String = UUID.randomUUID().toString()
}
