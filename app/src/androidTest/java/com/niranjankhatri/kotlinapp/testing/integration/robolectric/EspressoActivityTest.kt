package com.niranjankhatri.kotlinapp.testing.integration.robolectric

import android.app.Application
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import com.niranjankhatri.kotlinapp.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoActivityTest {

    @Test
    fun showFactorialResultInTextViewTest() {
        val scenario = launch(RobolectricActivity::class.java)
        scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED)
        onView(withId(R.id.rb_editText)).perform(
            typeText("6")
        )
        onView(withId(R.id.rb_button)).perform(click())
        onView(withId(R.id.rb_textView)).check(
            matches(
                withText(
                    getApplicationContext<Application>().getString(
                        R.string.result,
                        "720"
                    )
                )
            )
        )
    }
}