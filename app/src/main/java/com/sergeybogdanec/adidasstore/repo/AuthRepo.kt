package com.sergeybogdanec.adidasstore.repo

import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object AuthRepo {

    private const val COLLECTION_USERS = "users"

    private val auth by lazy {
        Firebase.auth
    }

    private val store by lazy {
        Firebase.firestore
    }

    fun register(
        name: String,
        email: String,
        password: String,
        onComplete: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                result.user?.run {
                    updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                    )
                    sendEmailVerification()
                }
                onComplete()
            }
            .addOnFailureListener(onFailure)
    }

    fun auth(
        email: String,
        password: String,
        onComplete: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete()
            }
            .addOnFailureListener(onFailure)
    }

    fun signOut() = auth.signOut()

}
