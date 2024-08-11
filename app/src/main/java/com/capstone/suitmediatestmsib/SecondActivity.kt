package com.capstone.suitmediatestmsib

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvName = findViewById<TextView>(R.id.tv_name)
        val ivBack = findViewById<ImageView>(R.id.iv_back)
        val btn = findViewById<Button>(R.id.btn_action)
        val tvSelectedUser1 = findViewById<TextView>(R.id.tv_first_name)
        val tvSelectedUser2 = findViewById<TextView>(R.id.tv_last)

        val userFirstName = intent.getStringExtra("EXTRA_USER_FIRSTNAME")
        val userLastName = intent.getStringExtra("EXTRA_USER_LASTNAME")

        val sharedPreferences = getSharedPreferences("user_Prefs", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")

        tvName.text = name


        if (userFirstName.isNullOrEmpty() || userLastName.isNullOrEmpty()) {
            tvSelectedUser1.visibility = View.VISIBLE
            tvSelectedUser2.visibility = View.VISIBLE

        } else {

            tvSelectedUser1.text = userFirstName
            tvSelectedUser2.text = userLastName
            tvSelectedUser1.visibility = View.VISIBLE
            tvSelectedUser2.visibility = View.VISIBLE
        }


        ivBack.setOnClickListener {
           // startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btn.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
        tvName.text = name
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}