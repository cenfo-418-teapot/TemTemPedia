package com.teapot.temtempedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Team(
    val user_id: String? = null,
    val temtem_ids: List<Int>? = null,
)

class MainActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    fun createTeam(){

        findViewById<Button>(R.id.createTeam).setOnClickListener{
            val intent = Intent(this, SaveTeam::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.createTeam).setOnClickListener{
            val intent = Intent(this, TeamLocal::class.java)
            startActivity(intent)
            finish()
        }

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

        // Run tem tem info preview (Isaac Feature)
        findViewById<Button>(R.id.temtemInfo).setOnClickListener {
            val intent = Intent(this, TemtemInfo::class.java)
            val infoEmpaquetada = Bundle()
            infoEmpaquetada.putInt("temtem", 3) //Your id
            intent.putExtras(infoEmpaquetada) //Put your id to your next Intent
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.newTeam).setOnClickListener {
            val max = 100;
            val min = 1
            val randIds = IntArray(5){ (min..max).random()}.asList();
            saveTeam(randIds);
        }

        findViewById<Button>(R.id.createTeam).setOnClickListener{
            val intent = Intent(this, SaveTeam::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun saveTeam(teamMembers: List<Int>) {
        val db = Firebase.firestore
        db.collection("teams")
            .add( Team( auth.uid, teamMembers ) )
            .addOnSuccessListener { documentReference ->
                Toast.makeText(applicationContext, "Team saved to ${auth.currentUser?.email}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext, "There was an internet issue, try again", Toast.LENGTH_SHORT).show()
            }
    }

    fun showDetail(view: View) {
        Toast.makeText(this, "Show the detail MF!", Toast.LENGTH_SHORT).show()
    }


}