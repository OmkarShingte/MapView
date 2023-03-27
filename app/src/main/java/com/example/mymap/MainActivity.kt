package com.example.mymap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.isEnabled = false

        // Validate email and password fields on text change
        verifyFields(passwordEditText)
        verifyFields(emailEditText)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    private fun validateFields() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

        val isValidPassword = password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}".toRegex())

        loginButton.isEnabled = isValidEmail && isValidPassword
        loginButton.alpha = if(isValidEmail && isValidPassword) 1.0f else 0.6f
    }

    private fun verifyFields(textView: TextView){
        textView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}