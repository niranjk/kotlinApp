package com.niranjankhatri.kotlinapp.saverestorestate

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.R
import java.util.UUID

class SaveRestoreActivity : AppCompatActivity() {

    private val discountBtn : Button
        get() = findViewById(R.id.discount_btn)

    private val firstName : EditText
        get() = findViewById(R.id.first_name_et)

    private val lastName : EditText
        get() = findViewById(R.id.lastname_et)

    private val email : EditText
        get() = findViewById(R.id.email_et)

    private val discountCodeConfirmation : TextView
        get() = findViewById(R.id.discount_code_confirmation)

    private val discountCode : TextView
        get() = findViewById(R.id.discount_code)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_restore)
        Log.d(TAG, "onCreate")
        // here we handle the button click
        discountBtn.setOnClickListener {
            val firstName = firstName.text.toString().trim()
            val lastName = lastName.text.toString().trim()
            val email = email.text.toString()
            // form validation
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){
                Toast.makeText(this, getString(R.string.add_text_validations), Toast.LENGTH_LONG).show()
            } else {
                val fullName = firstName.plus(" ").plus(lastName)
                discountCodeConfirmation.text = getString(R.string.discount_code_confirmation, fullName)
                // generate the discount code
                discountCode.text = UUID.randomUUID().toString().take(8).uppercase()
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard(){
        if (currentFocus != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
        // get the discount code or an emtpy strin gif it hasn't be set yet
        // get the discount confirmation message or the empty string if it hasn't be set yet
        if (savedInstanceState != null) {
            discountCode.text = savedInstanceState.getString(DISCOUNT_CODE, "")
            discountCodeConfirmation.text = savedInstanceState.getString(
                DISCOUNT_CONFIRMATION_MESSAGE, "")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putString(DISCOUNT_CODE, discountCode.text.toString())
        outState.putString(DISCOUNT_CONFIRMATION_MESSAGE, discountCodeConfirmation.text.toString())
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object{
        private const val TAG = "SaveRestoreActivity"
        private const val DISCOUNT_CONFIRMATION_MESSAGE = "DISCOUNT_CONFIRMATION_MESSAGE"
        private const val DISCOUNT_CODE = "DISCOUNT_CODE"
    }
}