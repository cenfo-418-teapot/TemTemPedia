package com.teapot.temtempedia

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.teapot.temtempedia.models.Team
import com.teapot.temtempedia.models.addTeam

class SaveTeam : AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    private var team = TeamLocal()
    private lateinit var editTextHello: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_team)

        editTextHello = findViewById(R.id.editTextTextPersonName)


        findViewById<Button>(R.id.btnSave).setOnClickListener {
            team.nombre = editTextHello.text.toString()

            addTeam(
                Team(
                    userId = auth.uid,
                    temtemIds = listOf(59, 12, 9),
                    title = team.nombre
                )
            ).addOnSuccessListener { ref ->
                run {
                    Snackbar.make(it, "Team saved ${ref.path}", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }

}