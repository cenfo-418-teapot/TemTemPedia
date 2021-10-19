package com.teapot.temtempedia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.teapot.temtempedia.models.Temtem
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)


        findViewById<Button>(R.id.logout).setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent= Intent(this, LoginScreen::class.java)
                Toast.makeText(this,"Logging Out",Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }

        findViewById<Button>(R.id.viewTeams).setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
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

            var temtemFragmentInstance = TemtemListItemMainActivity.newInstance(id, name, thumb)
            var transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.list_item_fragment_container, temtemFragmentInstance)
            transaction.commit()
        }
    }
}