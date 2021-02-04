package com.android.basicecommerce.presentation.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.basicecommerce.R
import com.android.basicecommerce.presentation.withCustomHint
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun test_isActivityInView() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.screen_login))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_labelLogin() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.tv_label_login))
            .check(matches(isDisplayed()))

        // or you can use this
//        onView(withId(R.id.tv_label_login))
//            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_visibility_textInputLayoutUsername() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.text_input_user_name))
            .check(matches(isDisplayed()))
        onView(withId(R.id.et_user_name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_textInputLayoutPassword() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.text_input_password))
            .check(matches(isDisplayed()))
        onView(withId(R.id.et_password))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_checkBoxRememberMe() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.cb_remember_me))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_buttonLogin() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.btn_login))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_buttonGoogleLogin() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.btn_google_login))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_buttonFacebookLogin() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.btn_facebook_login))
            .check(matches(isDisplayed()))
    }

//    @Test
//    fun test_visibility_progressBar() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
//        onView(withId(R.id.progress_bar))
//            .check(matches(isDisplayed()))
//    }

    @Test
    fun test_isLabelLoginDisplayed() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.tv_label_login))
            .check(matches(withText(R.string.label_login)))
    }

    @Test
    fun test_isHintTextInputUsernameDisplayed() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        var expectedText = ""
        activityRule.scenario.onActivity{
            expectedText = it.getString(R.string.hint_user_name)
        }
        onView(withId(R.id.text_input_user_name))
            .check(matches(withCustomHint(expectedText)))
    }

    @Test
    fun test_isHintTextInputPasswordDisplayed() {
        var expectedText = ""
        activityRule.scenario.onActivity {
            expectedText = it.getString(R.string.hint_password)
        }
        onView(withId(R.id.text_input_password))
            .check(matches(withCustomHint(expectedText)))
    }

    @Test
    fun test_isLabelRememberMeDisplayed() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.cb_remember_me))
            .check(matches(withText(R.string.label_remember_me)))
    }

    @Test
    fun test_isLabelLoginOnButtonDisplayed() {
//        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.btn_login))
            .check(matches(withText(R.string.label_login)))
    }
    

}