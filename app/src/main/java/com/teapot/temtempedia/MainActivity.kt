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

class MainActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient

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

        // Run tem tem info preview (Isaac Feature)
        findViewById<Button>(R.id.temtemInfo).setOnClickListener {
            val intent = Intent(this, TemtemInfo::class.java)
            val infoEmpaquetada = Bundle()
            infoEmpaquetada.putInt("temtem", 3) //Your id
            intent.putExtras(infoEmpaquetada) //Put your id to your next Intent
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.viewTeams).setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }


    }

    fun showDetail(view: View) {
        Toast.makeText(this, "Show the detail MF!", Toast.LENGTH_SHORT).show()
    }


}
