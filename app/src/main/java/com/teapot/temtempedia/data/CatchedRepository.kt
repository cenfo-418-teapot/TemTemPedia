package com.teapot.temtempedia.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.teapot.temtempedia.models.Catch

//fun fillUpTemtems() {
//    FirebaseFirestore.getInstance().collection("temtems")
//        .document("Normales")
//}

fun catchedList(uid: String?): Task<QuerySnapshot> {
    val ref = FirebaseFirestore.getInstance().collection("capturados")

    return ref.whereEqualTo("user_id", uid).get()
}

fun catchedList2(uid: String?, callback: (List<Catch>) -> Unit) {
    val ref = FirebaseFirestore.getInstance().collection("capturados")
    val places = ArrayList<Catch>()
    ref.whereEqualTo("userId", uid).get()
        .addOnSuccessListener { task ->
            //Log.d("TAG", "Inside onComplete function!");
            for (document in task) {
                val capturados: Catch = document.toObject(Catch::class.java)
                capturados.id = document.id
                places.add(capturados)
            }
            callback(places)
        }
}

fun catchTemtem(newCatch: Catch): Task<DocumentReference> {
    val ref = FirebaseFirestore.getInstance().collection("capturados")
    return ref.add(newCatch)
}

//fun uncatchTemtem(catch: Catch) {
//    val ref = FirebaseFirestore.getInstance().collection("capturados")
//    val updates = hashMapOf<String, Any>(
//        catch.id!! to FieldValue.delete()
//    )
//    ref.document(catch.id).update(updates)
//}
fun updateCatchedList(catch: Catch) {
    val ref = FirebaseFirestore.getInstance().collection("capturados")
    ref.document(catch.id!!)
        .update("temtemIds", catch.temtemIds)
}