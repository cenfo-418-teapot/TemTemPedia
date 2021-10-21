package com.teapot.temtempedia.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.teapot.temtempedia.models.Catch
import com.teapot.temtempedia.data.getAllTemtems

//fun fillUpTemtems() {
//    FirebaseFirestore.getInstance().collection("temtems")
//        .document("Normales")
//}

fun catchedList(uid: String?): Task<QuerySnapshot> {
    val ref = FirebaseFirestore.getInstance().collection("capturados")
    return ref.whereEqualTo("user_id", uid).get()
}

fun catchTemtem(newCatch: Catch) {
    val ref = FirebaseFirestore.getInstance().collection("capturados")
    ref.document(newCatch.temtemId.toString()).set(newCatch)
}

//fun catchTemtem(newCatch: Catch): Task<DocumentReference> {
//    val ref = FirebaseFirestore.getInstance().collection("capturados")
//    return ref.add(newCatch)
//}

fun uncatchTemtem(catch: Catch) {
    val ref = FirebaseFirestore.getInstance().collection("capturados")
    ref.document(catch.temtemId.toString()).delete()
}