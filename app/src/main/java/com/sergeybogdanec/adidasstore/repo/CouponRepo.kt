package com.sergeybogdanec.adidasstore.repo

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sergeybogdanec.adidasstore.model.Coupon
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CouponRepo {

    private const val COLLECTION_COUPONS = "coupons"

    private val firestore by lazy {
        Firebase.firestore
    }

    suspend fun getCoupon(value: String): Coupon? = suspendCoroutine { continuation ->
        firestore.collection(COLLECTION_COUPONS)
            .whereEqualTo("value", value)
            .get()
            .addOnFailureListener(continuation::resumeWithException)
            .addOnSuccessListener { snapshot ->
                snapshot.firstOrNull()?.toObject(Coupon::class.java).let(continuation::resume)
            }
    }

}
