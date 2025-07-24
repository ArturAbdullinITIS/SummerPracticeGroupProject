package com.example.mood.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mood.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChipSelection()
        setupButtonListener()
    }

    private fun setupChipSelection() {
        binding.chipHappy.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) handleMoodSelection("Радостное")
        }

        binding.chipSad.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) handleMoodSelection("Грустное")
        }

        binding.chipInspired.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) handleMoodSelection("Вдохновляющее")
        }
    }

    private fun handleMoodSelection(mood: String) {
        selectedMood = mood
    }

    private fun setupButtonListener() {
        binding.btnFindMovies.setOnClickListener {
            if (selectedMood != null) {
                openMovieList(selectedMood!!)
            } else {
                showError("Выберите настроение")
            }
        }
    }

    private fun openMovieList(mood: String) {
        Intent(this, ActivityList::class.java).apply {
            putExtra("MOOD_KEY", mood)
            startActivity(this)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private var selectedMood: String? = null
    }
}