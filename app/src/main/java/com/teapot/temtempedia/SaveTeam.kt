package com.teapot.temtempedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SaveTeam() : AppCompatActivity() {

    private var team =  TeamLocal()
    private lateinit var editTextHello :  EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_team)

        editTextHello = findViewById(R.id.editTextTextPersonName)


        findViewById<Button>(R.id.btnSave).setOnClickListener{
            saveTeam()

        }




    }

    fun saveTeam(){
        team.nombre = editTextHello.text.toString()


    }
}