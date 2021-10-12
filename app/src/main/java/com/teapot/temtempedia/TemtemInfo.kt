package com.teapot.temtempedia

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.teapot.temtempedia.databinding.ActivityTemtemInfoBinding

class TemtemInfo : AppCompatActivity() {

    private lateinit var binding: ActivityTemtemInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTemtemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpStatsBar()

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setUpStatsBar() {
        val pgHP = findViewById<ProgressBar>(R.id.pg_HP)
        val pgSTA = findViewById<ProgressBar>(R.id.pg_STA)
        val pgSPE = findViewById<ProgressBar>(R.id.pg_SPE)
        val pgATK = findViewById<ProgressBar>(R.id.pg_ATK)
        val pgDEF = findViewById<ProgressBar>(R.id.pg_DEF)
        val pgSPATK = findViewById<ProgressBar>(R.id.pg_SPATK)
        val pgSPDEF = findViewById<ProgressBar>(R.id.pg_SPDEF)
        setUpStatBar(pgHP, 77)
        setUpStatBar(pgSTA, 49)
        setUpStatBar(pgSPE, 60)
        setUpStatBar(pgATK, 77)
        setUpStatBar(pgDEF, 77)
        setUpStatBar(pgSPATK, 51)
        setUpStatBar(pgSPDEF, 50)
    }

    private fun setUpStatBar(bar: ProgressBar, stat: Int) {
        ObjectAnimator.ofInt(bar,"progress", stat)
            .setDuration(2000)
            .start()
    }
}