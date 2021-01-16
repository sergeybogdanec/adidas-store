package com.sergeybogdanec.adidasstore.model

import java.util.*

data class Coupon(
    var value: String = "",
    var discount: Int = 0
) {
    var id: String = UUID.randomUUID().toString()
}
