package com.vmoskvyak.reservations.ui.fragments.tables

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.ui.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TablesFragmentTest {
    @get:Rule
    var activityActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun init() {
        activityActivityTestRule.activity
                .supportFragmentManager.beginTransaction()
    }

    @Test
    fun selectTable() {
        onView(ViewMatchers.withId(R.id.rv_customers)).perform(
                RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                                0, ViewActions.click()))
        onView(ViewMatchers.withId(R.id.toolbar))
                .check(ViewAssertions.matches(withToolbarTitle("Marilyn Monroe")))
        onView(ViewMatchers.withId(R.id.rv_tables)).perform(
                RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                                0, ViewActions.click()))
    }

    private fun withToolbarTitle(title: CharSequence): Matcher<View> {
        return withToolbarTitle(Matchers.`is`(title))
    }

    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            public override fun matchesSafely(toolbar: Toolbar): Boolean {
                return textMatcher.matches(toolbar.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    }

}
