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

//        Getting data from MainActivity
        val email = intent.getStringExtra("Email")
        val age = intent.getStringExtra("Age")
        val userName = intent.getStringExtra("Username")
        val firstName = intent.getStringExtra("First Name")
        val lastName = intent.getStringExtra("Last Name")

//        Entering texts in the fields
        binding.etAge.text = "Age: $age"
        binding.etEmail.text = "Email: $email"
        binding.etName.text = "Full name: $firstName $lastName"
        binding.etUsername.text = "Username: $userName"

//        After the click, returning to MainActivity and also sending back the data
//        To refill the fields
        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("Age", age)
            intent.putExtra("Email", email)
            intent.putExtra("Username", userName)
            intent.putExtra("First Name", firstName)
            intent.putExtra("Last Name", lastName)

            startActivity(intent)
        }
    }
}