package com.niranjankhatri.kotlinapp.login.standard

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R

class LoginWelcomeActivity : AppCompatActivity() {

    var isLoggedIn = false
    var loggedInUser = ""

    private val header: TextView
        get() = findViewById(R.id.header)

    private val backButton: Button
        get() = findViewById(R.id.back_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_welcome)

        //Get the intent which started this activity
        intent.let { loginIntent ->
            val useNameForm = loginIntent.getStringExtra(AppConstants.USERNAME_KEY) ?: ""
            val passwordForm = loginIntent.getStringExtra(AppConstants.PASSWORD_KEY) ?: ""
            // check validity of the user
            if (checkUserValidity(useNameForm, passwordForm)){
                // login the user
                updateView(useNameForm)
                isLoggedIn = true
            } else {
                // show login error message
                header.text = getString(R.string.login_error)
                backButton.isVisible = true
                backButton.setOnClickListener {
                    // finishes the current activity and go back to previous activity
                    finish()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(AppConstants.IS_LOGGED_IN, isLoggedIn)
        outState.putString(AppConstants.LOGGED_IN_USERNAME, loggedInUser)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isLoggedIn = savedInstanceState.getBoolean(AppConstants.IS_LOGGED_IN, false)
        loggedInUser = savedInstanceState.getString(AppConstants.LOGGED_IN_USERNAME, "")
        if (isLoggedIn && loggedInUser.isNotEmpty()){
            updateView(loggedInUser)
        }
    }

    private fun updateView(userName: String){
        loggedInUser = userName
        val welcomeMessage = getString(R.string.welcome_login_text, loggedInUser)
        backButton.isVisible = false
        header.text = welcomeMessage
    }
    private fun checkUserValidity(
        userName: String,
        pass: String
    ) = userName.contentEquals(AppConstants.CORRECT_USERNAME_VALUE) && pass.contentEquals(AppConstants.CORRECT_PASSWORD_VALUE)

}
