package com.sharedshopper.chris.sharedshopper;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;

import itemsoverview.ItemOverviewActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestItemOverview {

    private static final String TEST_TITLE = "test title overview";

    @Rule
    public ActivityTestRule<ItemOverviewActivity> testRule =
            new ActivityTestRule<>(ItemOverviewActivity.class);

    @Before
    public void setUp(){
        onView(withId(R.id.fab_new_item)).perform(click());

        onView(withId(R.id.item_add_titletext)).perform(typeText(TEST_TITLE),closeSoftKeyboard());
        onView(withId(R.id.fab_add_ok)).perform(click());
    }

    @Test
    public void startRecycler_checkItemVisible(){
        onView(withId(R.id.item_recycler_view)).check(matches(hasDescendant(withText(TEST_TITLE))));
    }
}
