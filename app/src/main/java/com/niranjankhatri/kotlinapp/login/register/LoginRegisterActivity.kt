package com.niranjankhatri.kotlinapp.login.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.login.singleTop.LoginSingleTopActivity

class LoginRegisterActivity  : AppCompatActivity() {

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

    private val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){  activityResult ->
        if (activityResult.resultCode == RESULT_OK){
            val data = activityResult.data
            val userNameForm = data?.getStringExtra(AppConstants.USERNAME_KEY) ?: ""
            updateLoggedView(userNameForm)
            isLoggedIn = true
        } else if(activityResult.resultCode == RESULT_CANCELED){
            Toast.makeText(
                this,
                getString(R.string.login_error),
                Toast.LENGTH_LONG
            ).apply { setGravity(Gravity.CENTER, 0,0) }.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        submitButton.setOnClickListener {
            val usernameForm = userName.text.toString().trim()
            val passwordForm = password.text.toString().trim()
            hideKeyboard()
            // form validations
            if (usernameForm.isNotEmpty() && passwordForm.isNotEmpty()){
               // register and start activity for result
                Intent(this, LoginRegisterWelcomeActivity::class.java).also { welcomeIntent ->
                    // pass the data
                    welcomeIntent.putExtra(AppConstants.USERNAME_KEY, usernameForm)
                    welcomeIntent.putExtra(AppConstants.PASSWORD_KEY, passwordForm)

                    // reset your edittext fiels
                    this.userName.text.clear()
                    this.password.text.clear()
                    // lauch activity for result
                    startActivityForResult.launch(welcomeIntent)
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
            updateLoggedView(loggedInUser)
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

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}