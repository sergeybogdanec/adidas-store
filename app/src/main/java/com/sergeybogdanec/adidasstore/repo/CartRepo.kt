package com.sergeybogdanec.adidasstore.repo

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sergeybogdanec.adidasstore.model.CartItem
import com.sergeybogdanec.adidasstore.model.Product
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CartRepo {

    private const val COLLECTION_USERS = "users"
    private const val COLLECTION_CART = "cart"

    private val auth by lazy {
        Firebase.auth
    }

    private val firestore by lazy {
        Firebase.firestore
    }

    suspend fun addToCart(product: Product) = suspendCoroutine<Unit> { continuation ->
        firestore.collection("$COLLECTION_USERS/${auth.currentUser?.uid}/$COLLECTION_CART")
            .document(product.id)
            .set(CartItem(
                product.name,
                product.description,
                product.size,
                product.collection,
                product.price,
                product.pictureUrl,
                product.type,
                1
            ))
            .addOnSuccessListener { continuation.resume(Unit) }
            .addOnFailureListener(continuation::resumeWithException)
    }

    suspend fun getAll() = suspendCoroutine<List<CartItem>> { continuation ->
        firestore.collection("$COLLECTION_USERS/${auth.currentUser?.uid}/$COLLECTION_CART")
            .get()
            .addOnFailureListener(continuation::resumeWithException)
            .addOnSuccessListener { snapshot ->
                continuation.resume(
                    snapshot.documents.mapNotNull {
                        it.toObject(CartItem::class.java)?.takeIf { it.count > 0 }?.apply {
                            id = it.id
                        }
                    }
                )
            }
    }

    suspend fun update(cartItem: CartItem) = suspendCoroutine<Unit> { continuation ->
        val document =
            firestore.collection("$COLLECTION_USERS/${auth.currentUser?.uid}/$COLLECTION_CART")
                .document(cartItem.id)
        if (cartItem.count > 0) {
            document.set(cartItem)
                .addOnSuccessListener {
                    continuation.resume(Unit)
                }
                .addOnFailureListener(continuation::resumeWithException)
        } else {
            document.delete()
                .addOnSuccessListener {
                    continuation.resume(Unit)
                }
                .addOnFailureListener(continuation::resumeWithException)
        }
    }

    suspend fun clearCart() = suspendCoroutine<Unit> { continuation ->
        firestore.collection("$COLLECTION_USERS/${auth.currentUser?.uid}/$COLLECTION_CART")
            .get()
            .addOnFailureListener(continuation::resumeWithException)
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach {
                    it.reference.delete()
                }
                continuation.resume(Unit)
            }
    }

}
