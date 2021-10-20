package com.teapot.temtempedia.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.teapot.temtempedia.models.Catch

fun catchedList(uid: String?): Task<QuerySnapshot> {
    val ref = FirebaseFirestore.getInstance().collection("catched")
    return ref.whereEqualTo("user_id", uid).get()
}

fun catchTemtem(newCatch: Catch): Task<DocumentReference> {
    val ref = FirebaseFirestore.getInstance().collection("catched")
    return ref.add(newCatch)
}
