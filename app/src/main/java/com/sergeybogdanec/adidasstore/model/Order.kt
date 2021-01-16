package com.sergeybogdanec.adidasstore.model

import com.google.firebase.firestore.DocumentReference
import java.util.*

data class Order(
    var date: Date = Date(),
    var clientId: String = "",
    var couponReference: DocumentReference? = null,
    var isPayed: Boolean = false,
    var products: List<DocumentReference> = listOf()
)
