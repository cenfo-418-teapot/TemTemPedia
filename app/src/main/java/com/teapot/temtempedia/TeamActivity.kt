package com.teapot.temtempedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.teapot.temtempedia.adapters.TeamAdapter
import com.teapot.temtempedia.models.Team
import com.teapot.temtempedia.models.addTeam

class TeamActivity : AppCompatActivity() {
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val teamCollection = db.collection("teams")
    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        setUpRecyclerView()
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            addTeam(Team(userId = auth.uid, temtemIds = listOf(59,12,9), title = "${auth.currentUser?.displayName}'s Team")).addOnSuccessListener { ref ->
                run {
                    Snackbar.make(it, "Team saved ${ref.path}", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        val query = teamCollection.whereEqualTo("userId", auth.uid)
        val opts = FirestoreRecyclerOptions.Builder<Team>().setQuery(query, Team::class.java).build()
        adapter = TeamAdapter(opts, applicationContext)
        val rvTeam = findViewById<RecyclerView>(R.id.rvTeamDisplay)
        rvTeam.setHasFixedSize(false)
        rvTeam.layoutManager = LinearLayoutManager(this)
        rvTeam.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}