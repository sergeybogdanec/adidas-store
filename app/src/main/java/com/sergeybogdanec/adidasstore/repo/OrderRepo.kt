package com.sergeybogdanec.adidasstore.repo

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sergeybogdanec.adidasstore.model.Order
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object OrderRepo {

    private const val COLLECTION_ORDERS = "orders"
    private const val COLLECTION_ORDERS_PRODUCTS = "products"

    private val firestore by lazy {
        Firebase.firestore
    }

    private val auth by lazy {
        Firebase.auth
    }

    suspend fun addOrder(order: Order) = suspendCoroutine<Unit> { continuation ->
        firestore.collection(COLLECTION_ORDERS)
            .add(order)
            .addOnFailureListener(continuation::resumeWithException)
            .addOnSuccessListener {
                continuation.resume(Unit)
            }
    }

    suspend fun getAll(): List<Order> = suspendCoroutine { continuation ->
        firestore.collection(COLLECTION_ORDERS)
            .whereEqualTo("clientId", auth.currentUser?.uid)
            .get()
            .addOnFailureListener(continuation::resumeWithException)
            .addOnSuccessListener { snapshot ->
                snapshot.toObjects(Order::class.java).let(continuation::resume)
            }
    }

}
