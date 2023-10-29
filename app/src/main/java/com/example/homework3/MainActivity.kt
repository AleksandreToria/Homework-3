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

        binding.btnSave.setOnClickListener {
            if (areElementsNullOrBlank(elements)) {
                Toast.makeText(
                    applicationContext,
                    "Please don't leave any field empty",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            if (binding.etUsername.text!!.length < 10) {
                Toast.makeText(
                    applicationContext,
                    "Username length is less than 10",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            if (!isEmailValid(binding.etEmail.text.toString())) {
                Toast.makeText(applicationContext, "Please enter a valid email", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (!isAgeValid(binding.etAge.text.toString().toInt())) {
                Toast.makeText(applicationContext, "Please enter valid age", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity2::class.java)
            for (element in elements) {
                intent.putExtra(element.hint.toString(), element.text.toString())
            }
            startActivity(intent)
        }

        binding.btnClear.setOnClickListener {
            Toast.makeText(applicationContext, "Please long press to clear", Toast.LENGTH_LONG)
                .show()
        }

        binding.btnClear.setOnLongClickListener {
            deleteFields(elements)

            true
        }

    }


    private fun areElementsNullOrBlank(inputs: Array<AppCompatEditText>): Boolean {
        return inputs.any { input -> input.text.isNullOrBlank() }
    }

    private fun isEmailValid(mail: String): Boolean {
        val trimmedMail = mail.trim()
        return trimmedMail.contains("@") && !trimmedMail.startsWith("@") && !trimmedMail.endsWith("@")
    }

    private fun isAgeValid(age: Int): Boolean {
        return age > 0
    }

    private fun deleteFields(inputs: Array<AppCompatEditText>) {
        for (i in inputs) {
            i.setText("")
        }
    }
}