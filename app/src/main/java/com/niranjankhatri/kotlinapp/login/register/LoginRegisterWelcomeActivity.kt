package com.niranjankhatri.kotlinapp.login.register

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R

class LoginRegisterWelcomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register_welcome)

        //Get the intent which started this activity
        intent.let { loginIntent ->
            val useNameForm = loginIntent.getStringExtra(AppConstants.USERNAME_KEY) ?: ""
            val passwordForm = loginIntent.getStringExtra(AppConstants.PASSWORD_KEY) ?: ""
            val checkValidityOfUser = checkUserValidity(useNameForm, passwordForm)
            if (checkValidityOfUser){
                setResult(Activity.RESULT_OK, loginIntent)
            } else {
                setResult(Activity.RESULT_CANCELED, loginIntent)
            }
            finish()
        }
    }

    private fun checkUserValidity(
        userName: String,
        pass: String
    ) = userName.contentEquals(AppConstants.CORRECT_USERNAME_VALUE) && pass.contentEquals(
        AppConstants.CORRECT_PASSWORD_VALUE)

}
