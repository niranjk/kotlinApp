package com.niranjankhatri.kotlinapp.login.standard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.login.singleTop.LoginSingleTopActivity

class LoginStandardActivity : AppCompatActivity() {

    private var isLoggedIn = false
    private var loggedInUser = ""

    private val submitButton: Button
        get() = findViewById(R.id.login_submit_btn)

    private val userName: EditText
        get() = findViewById(R.id.user_name)

    private val password: EditText
        get() = findViewById(R.id.password)

    private val header: TextView
        get() = findViewById(R.id.header)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        submitButton.setOnClickListener {
            val usernameForm = userName.text.toString().trim()
            val passwordForm = password.text.toString().trim()
            hideKeyboard()
            // validate the form and proceed
            if (usernameForm.isNotEmpty() && passwordForm.isNotEmpty()){
                // launch welcome activity
                Intent(this, LoginWelcomeActivity::class.java).also { welcomeIntent ->
                    // pass the extras
                    welcomeIntent.putExtra(AppConstants.USERNAME_KEY, usernameForm)
                    welcomeIntent.putExtra(AppConstants.PASSWORD_KEY, passwordForm)

                    // reset the edittext fiels
                    this.userName.text.clear()
                    this.password.text.clear()

                    // lauch
                    startActivity(welcomeIntent)
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.login_form_entry_error),
                    Toast.LENGTH_LONG
                ).apply { setGravity(Gravity.CENTER, 0,0) }.show()
            }
        }
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}