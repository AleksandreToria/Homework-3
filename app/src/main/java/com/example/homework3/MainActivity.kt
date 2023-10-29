package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.homework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var elements: Array <AppCompatEditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elements = arrayOf(
            binding.etAge,
            binding.etEmail,
            binding.etFirstName,
            binding.etLastName,
            binding.etUsername
        )

//        Save button press logic

        binding.btnSave.setOnClickListener {

//            Checking for Null or Blank
            if (areElementsNullOrBlank(elements)) {
                Toast.makeText(
                    applicationContext,
                    "Please don't leave any field empty",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

//            Checking for username length
            if (binding.etUsername.text!!.length < 10) {
                Toast.makeText(
                    applicationContext,
                    "Username length is less than 10",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

//            Checking for email validation
            if (!isEmailValid(binding.etEmail.text.toString())) {
                Toast.makeText(applicationContext, "Please enter a valid email", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

//            Checking for valid age
            if (!isAgeValid(binding.etAge.text.toString().toInt())) {
                Toast.makeText(applicationContext, "Please enter valid age", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

//            After every validation passing, program goes to second activity
//            and sends the field data
            val intent = Intent(this, MainActivity2::class.java)
            for (element in elements) {
                intent.putExtra(element.hint.toString(), element.text.toString())
            }
            startActivity(intent)
        }


//        Reminder to long press
        binding.btnClear.setOnClickListener {
            Toast.makeText(applicationContext, "Please long press to clear", Toast.LENGTH_LONG)
                .show()
        }

//        Long click to empty the fields
        binding.btnClear.setOnLongClickListener {
            deleteFields(elements)

            true
        }
    }


//    OnResume function, after which the fields are refilled with data
    override fun onResume() {
        super.onResume()

        val email = intent.getStringExtra("Email")
        val age = intent.getStringExtra("Age")
        val userName = intent.getStringExtra("Username")
        val firstName = intent.getStringExtra("First Name")
        val lastName = intent.getStringExtra("Last Name")

        binding.etAge.setText(age)
        binding.etEmail.setText(email)
        binding.etFirstName.setText(firstName)
        binding.etLastName.setText(lastName)
        binding.etUsername.setText(userName)
    }

//    Function for checking Null or Blank
    private fun areElementsNullOrBlank(inputs: Array<AppCompatEditText>): Boolean {
        return inputs.any { input -> input.text.isNullOrBlank() }
    }

//    Email validation
    private fun isEmailValid(mail: String): Boolean {
        val trimmedMail = mail.trim()
        return trimmedMail.contains("@") && !trimmedMail.startsWith("@") && !trimmedMail.endsWith("@")
    }

//    Age validation
    private fun isAgeValid(age: Int): Boolean {
        return age > 0
    }

//    Field deletion
    private fun deleteFields(inputs: Array<AppCompatEditText>) {
        for (i in inputs) {
            i.setText("")
        }
    }
}