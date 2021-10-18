package com.teapot.temtempedia.models

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun teamList(uid: String?): Task<QuerySnapshot> {
    val teamsRef = FirebaseFirestore.getInstance().collection("teams")
    return teamsRef.whereEqualTo("user_id", uid).get()
}

fun addTeam(team: Team): Task<DocumentReference> {
    val teamsRef = FirebaseFirestore.getInstance().collection("teams")
    return teamsRef.add(team)
}

