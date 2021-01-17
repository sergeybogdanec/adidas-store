package by.tix.draft.artemapp.model

import com.google.firebase.firestore.DocumentReference
import java.util.*

data class Order(
    var date: Date = Date(),
    var clientId: String = "",
    var isPayed: Boolean = false,
    var products: List<DocumentReference> = listOf()
)
