package com.niranjankhatri.kotlinapp.login.singleTop

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

class LoginSingleTopActivity : AppCompatActivity() {

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
            // form validations
            if (usernameForm.isNotEmpty() && passwordForm.isNotEmpty() && submitButton.isVisible){
                // set the launching activity
                Intent(this, LoginSingleTopActivity::class.java).also { loginIntent->
                    // put extas to be passed by the intent
                    loginIntent.putExtra(AppConstants.USERNAME_KEY, usernameForm)
                    loginIntent.putExtra(AppConstants.PASSWORD_KEY, passwordForm)
                    startActivity(loginIntent)
                }
            }else {
               Toast.makeText(
                   this,
                   getString(R.string.login_form_entry_error),
                   Toast.LENGTH_LONG
               ).apply { setGravity(Gravity.CENTER, 0,0) }.show()
            }
        }
    }

    override fun onNewIntent(newIntent: Intent?) {
        super.onNewIntent(newIntent)
        // set the new intent to current intent in the process
        intent = newIntent
        intent?.let { loginInent ->
            val userNameForm = loginInent.getStringExtra(AppConstants.USERNAME_KEY) ?:""
            val passForm = loginInent.getStringExtra(AppConstants.PASSWORD_KEY) ?:""

            val loggedInCorrectly = hasEnteredCorrectlyCredentials(
                userNameForm,
                passForm
            )

            if (loggedInCorrectly){
                updateLoggedView(userNameForm)
                isLoggedIn = true
            } else {
                // show error
                Toast.makeText(
                    this,
                    getString(R.string.login_error),
                    Toast.LENGTH_LONG
                ).apply { setGravity(Gravity.CENTER, 0,0) }.show()
            }
        }

    }

    private fun updateLoggedView(userNameForm: String){
        loggedInUser = userNameForm
        val welcomeMessage = getString(R.string.welcome_login_text, loggedInUser)
        userName.isVisible = false
        password.isVisible = false
        submitButton.isVisible = false
        header.text = welcomeMessage
    }

    private fun hasEnteredCorrectlyCredentials(
        userNameForm: String,
        passwordForm: String
    ): Boolean{
        return userNameForm.contentEquals(AppConstants.CORRECT_USERNAME_VALUE) && passwordForm.contentEquals(AppConstants.CORRECT_PASSWORD_VALUE)
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}