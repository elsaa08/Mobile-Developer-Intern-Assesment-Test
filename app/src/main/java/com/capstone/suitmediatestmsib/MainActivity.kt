package com.capstone.suitmediatestmsib

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "user_prefs"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val etName = findViewById<TextInputEditText>(R.id.et_name)
        val etClass = findViewById<TextInputEditText>(R.id.et_class)
        val btnNext = findViewById<Button>(R.id.btn_next)
        val btnCheck = findViewById<Button>(R.id.btn_check)
        val tvResult = findViewById<TextView>(R.id.tv_result)

        btnCheck.setOnClickListener{
            val inputText = etClass.text.toString().trim()
            val isPalindrome = checkPalindrome(inputText)
            if(inputText.isEmpty()){
                etClass.error = "Palindrome cannot be empty"
            }else {
                val resultText = if (isPalindrome) {
                    "isPalindrome"
                } else {
                    "not Palindrome"
                }
                tvResult.text = resultText
            }

        }

        btnNext.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Name cannot be empty"
            } else {

                savepref(name)
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }
    }
    private fun savepref(name: String) {
        val sharedPreferences = getSharedPreferences("user_Prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.apply()
    }
    private fun checkPalindrome(text: String): Boolean {
        val cleanText = text.replace("\\s".toRegex(), "")
        return cleanText == cleanText.reversed()
    }
}