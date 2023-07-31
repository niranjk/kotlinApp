package com.niranjankhatri.kotlinapp.composetesting

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.niranjankhatri.kotlinapp.testing.ui.Activity1
import org.junit.Rule
import org.junit.Test


// ------------ TESTING WITH COMPOSE ----------------
class MyComposeTest{
    @get: Rule
    var composeTestRuleForActivity =
        createAndroidComposeRule(Activity1::class.java)
    @get: Rule
    var composeTestRuleForNoActivity =
        createComposeRule()

    @Test
    fun testNoActivityFunction(){
        composeTestRuleForNoActivity.setContent {
            // set methods you want to test here.
        }
    }

    @Test
    fun testActivityFunction(){
        composeTestRuleForActivity.onNodeWithText("My test text")
        composeTestRuleForActivity.
        onNodeWithContentDescription("My content description")
        composeTestRuleForActivity.onNodeWithTag("My test tag")

        composeTestRuleForActivity.onNodeWithText("My test text")
            .performClick()
            .performScrollTo()
            .performTextInput("MY new test text")


        composeTestRuleForActivity.onNodeWithText("My test text")
            .assertIsDisplayed()
            .assertIsNotDisplayed()
            .assertIsEnabled()
            .assertIsNotEnabled()
            .assertIsSelected()
            .assertIsNotSelected()


        composeTestRuleForActivity.onAllNodesWithText("My text")
        composeTestRuleForActivity.
        onAllNodesWithContentDescription("My content description")[0]
            .assertIsDisplayed()


        composeTestRuleForActivity.onAllNodesWithText("My text")
            .assertCountEquals(3)
            .assertAll(SemanticsMatcher.expectValue(SemanticsProperties.Selected, true))
            .assertAny(SemanticsMatcher.expectValue(SemanticsProperties.Selected, true))
    }
}