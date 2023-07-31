package com.niranjankhatri.kotlinapp.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.niranjankhatri.kotlinapp.R


class MyScreenRobot {
    fun setText(): MyScreenRobot{
        onView(ViewMatchers.withId(R.id.rb_editText)).perform(ViewActions.replaceText("5"))
        return this
    }
    fun pressButton(): MyScreenRobot{
        onView(ViewMatchers.withId(R.id.rb_button)).perform(ViewActions.click())
        return this
    }
    fun assertText(): MyScreenRobot{
        onView(ViewMatchers.withId(R.id.rb_textView)).check(
            ViewAssertions.matches(ViewMatchers.withText("my test"))
        )
        return this
    }
}