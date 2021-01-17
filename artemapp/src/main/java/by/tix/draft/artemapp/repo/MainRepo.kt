package by.tix.draft.artemapp.repo

import android.util.Log
import by.tix.draft.artemapp.model.Order
import by.tix.draft.artemapp.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


object MainRepo {

    private val store by lazy {
        Firebase.firestore
    }

    fun addOrders(
        orders: List<Order>,
        onComplete: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        orders.forEach { order ->
            store.collection("orders")
                .add(order)
                .addOnSuccessListener {
                    onComplete()
                }
                .addOnFailureListener {
                    onFailure(it)
                }
        }
    }

    fun getOrders(
        onComplete: (List<Order>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        store.collection("orders")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(task.result!!.toObjects(Order::class.java))
                } else {
                    onFailure(task.exception!!)
                }
            }
    }

    fun addProducts(
        products: List<Product>,
        onComplete: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        products.forEach { product ->
            store.collection("products")
                .add(product.id to product)
                .addOnSuccessListener {
                    onComplete()
                }
                .addOnFailureListener {
                    onFailure(it)
                }
        }
    }

    fun getProducts(
        onComplete: (List<Product>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        store.collection("products")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(task.result!!.toObjects(Product::class.java))
                } else {
                    onFailure(task.exception!!)
                }
            }
    }

}