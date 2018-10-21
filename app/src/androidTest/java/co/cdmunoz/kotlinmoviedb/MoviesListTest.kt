package co.cdmunoz.kotlinmoviedb

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import co.cdmunoz.kotlinmoviedb.list.ListActivity
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MoviesListTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<ListActivity>(ListActivity::class.java)

    @Test
    fun listIsDisplayed() {
        onView(withId(R.id.movies_list)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun scrollToitem() {
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
    }

    @Test
    fun scrollToItemAndClickOnIt() {
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.movies_list)).perform(click())
    }

    @Test
    fun scrollToItemClickOnItAndCheckInfo() {
        val TEXT_TO_MATCH = "Ant-Man and the Wasp" //this value may change ... so be careful
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.movies_list)).perform(click())
        onView(withId(R.id.detail_title)).check(matches(withText(TEXT_TO_MATCH)))
    }

    @Test
    fun checkLabelOnDetailsScreen() {
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        onView(withId(R.id.detail_release_date))
                .check(matches(
                        withText(containsString(activityTestRule.getActivity().getResources().getString(R.string.release_date_lbl)))))
    }

    @Test
    fun backButtonTest() {
        scrollToItemAndClickOnIt()
        Espresso.pressBack()
        listIsDisplayed()
    }
}