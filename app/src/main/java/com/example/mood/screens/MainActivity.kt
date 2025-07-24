package com.example.mood.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mood.R
import com.example.mood.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedMood: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChipGroup()
        setupButtonListener()
    }

    private fun setupChipGroup() {
        binding.moodChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            selectedMood = when (checkedIds.firstOrNull()) {
                R.id.chipHappy -> "happy"
                R.id.chipSad -> "sad"
                R.id.chipInspired -> "inspired"
                else -> null
            }
            Log.d("MoodSelection", "Selected mood: $selectedMood") // Для отладки
        }
    }

    private fun setupButtonListener() {
        binding.btnFindMovies.setOnClickListener {
            if (selectedMood != null) {
                openMovieList(selectedMood!!)
            } else {
                Toast.makeText(
                    this,
                    "Пожалуйста, выберите настроение!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openMovieList(mood: String) {
        val intent = Intent(this, ActivityList::class.java).apply {
            putExtra("MOOD_KEY", mood)
        }
        startActivity(intent)
    }
}