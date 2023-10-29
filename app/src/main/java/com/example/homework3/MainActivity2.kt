package com.example.homework3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("Email")
        val age = intent.getStringExtra("Age")
        val userName = intent.getStringExtra("Username")
        val firstName = intent.getStringExtra("First Name")
        val lastName = intent.getStringExtra("Last Name")

        binding.etAge.text = age
        binding.etEmail.text = email
        binding.etName.text = "$firstName $lastName"
        binding.etUsername.text = userName

        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}