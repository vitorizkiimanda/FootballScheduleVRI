package com.example.vitorizkiimanda.footballschedulevri

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.vitorizkiimanda.footballschedulevri.R.id.*
import com.example.vitorizkiimanda.footballschedulevri.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BasicTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testAll() {
        Thread.sleep(1500)
        onView(withId(rvMatchesLast))
                .check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(rvMatchesLast)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))
        onView(withId(rvMatchesLast)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(14, click()))
        Thread.sleep(1500)

        //matchDetail
        onView(withId(image_home)).check(matches(isDisplayed()))
        onView(withId(image_away)).check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1500)

        //swipeTo nextmatch
        pressBack()
        Thread.sleep(1500)
        onView(withId(viewpager_main)).perform(swipeLeft())
        Thread.sleep(1500)

        //matchdetail Next
        onView(withId(rvMatchesNext)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(1500)

        //swipe to favoutrite
        pressBack()
        Thread.sleep(1500)
        onView(withId(viewpager_main)).perform(swipeLeft())
        Thread.sleep(1500)

        //favourite detail
        onView(withId(rvMatchesFav)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        //unfav - fav again
        Thread.sleep(1500)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1500)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1500)

        //erase from fav
        pressBack()
        Thread.sleep(1500)
        onView(withId(rvMatchesFav)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(1500)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1500)
        pressBack()
        Thread.sleep(1500)
    }
}