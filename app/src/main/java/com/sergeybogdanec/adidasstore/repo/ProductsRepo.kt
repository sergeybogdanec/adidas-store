package com.sergeybogdanec.adidasstore.repo

import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.sergeybogdanec.adidasstore.model.Product
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object ProductsRepo {

    private const val PRODUCTS_COLLECTION = "products"

    private val firestore by lazy {
        Firebase.firestore
    }

    suspend fun getAllProducts() = suspendCoroutine<List<Product>> { continuation ->
        firestore.collection(PRODUCTS_COLLECTION).get()
            .addOnSuccessListener { snapshot ->
                snapshot.map { document ->
                    document.toObject<Product>().apply {
                        id = document.id
                    }
                }.let(continuation::resume)
            }
            .addOnFailureListener (continuation::resumeWithException)
    }

}
