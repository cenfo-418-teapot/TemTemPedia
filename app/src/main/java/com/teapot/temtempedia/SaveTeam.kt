package com.teapot.temtempedia

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.common.reflect.TypeToken
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.teapot.temtempedia.models.Team
import com.teapot.temtempedia.models.Temtem
import com.teapot.temtempedia.models.addTeam
import org.json.JSONArray
import java.io.IOException

class SaveTeam : AppCompatActivity()  {
    private val auth by lazy { FirebaseAuth.getInstance() }
    private var team = TeamLocal()
    private lateinit var editTextHello: EditText
    private val fragmentManager = supportFragmentManager
    private var temtemList : MutableList<Int?> = mutableListOf()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_team)

        editTextHello = findViewById(R.id.editTextTextPersonName)


        findViewById<Button>(R.id.btnSave).setOnClickListener {
            team.nombre = editTextHello.text.toString()

            addTeam(
                Team(
                    userId = auth.uid,
                    temtemIds = temtemList,
                    title = team.nombre
                )
            ).addOnSuccessListener { ref ->
                run {
                    Snackbar.make(it, "Team saved ${ref.path}", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }

        printTemtemList()

    }


    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String

        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return jsonString
    }

    private fun printTemtemList() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "TemtemSource.json")
        val gson = Gson()
        val listaTemtem = object : TypeToken<List<Temtem>>() {}.type
        var temtem: List<Temtem> = gson.fromJson(jsonFileString, listaTemtem)





        temtem.forEachIndexed { _, temp ->
            var id: Int = temp.id
            var name: String = temp.nombre
            var thumb: String = temp.fotoNormal
            var show  = true;

            var temtemFragmentInstance = TemtemListItemMainActivity.newInstance(id, name, thumb, show)
            var transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.list_item_fragment_container, temtemFragmentInstance)
            transaction.commit()

        }



    }

     fun passDataCom(data: MutableList<Int?>) {
         temtemList = data
    }


}




