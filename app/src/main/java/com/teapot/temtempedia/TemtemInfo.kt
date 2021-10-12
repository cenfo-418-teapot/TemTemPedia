package com.teapot.temtempedia

import android.animation.ObjectAnimator
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.teapot.temtempedia.databinding.ActivityTemtemInfoBinding

class TemtemInfo : AppCompatActivity() {

    private lateinit var binding: ActivityTemtemInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTemtemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpStatsBar();

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setUpStatsBar() {
        progressBar.max = 100
        val currentProgress = 50

        ObjectAnimator.ofInt(progressBar,"progress", currentProgress)
            .setDuration(2000)
            .start()
    }
}