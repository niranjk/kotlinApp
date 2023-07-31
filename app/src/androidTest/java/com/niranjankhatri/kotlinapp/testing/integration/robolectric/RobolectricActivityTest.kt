package com.niranjankhatri.kotlinapp.testing.integration.robolectric

import android.app.Application
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import com.niranjankhatri.kotlinapp.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RobolectricActivityTest{

    private val context = getApplicationContext<Application>()
    @Test
    fun show_factorial_result_in_textView_test(){
        val scenario = launch(RobolectricActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.rb_editText).setText(5.toString())
            activity.findViewById<Button>(R.id.rb_button).performClick()
            assertEquals(
                context.getString(R.string.result, "120"),
                activity.findViewById<TextView>(R.id.rb_textView).text
            )
        }
    }
}