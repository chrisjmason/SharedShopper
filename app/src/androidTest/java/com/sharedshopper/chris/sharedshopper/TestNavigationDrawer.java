package com.sharedshopper.chris.sharedshopper;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dalvik.annotation.TestTargetClass;
import itemsoverview.ItemOverviewActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Chris on 22/03/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestNavigationDrawer {

    @Rule
    public ActivityTestRule<ItemOverviewActivity> testRule = new ActivityTestRule<>(ItemOverviewActivity.class);

    @Test
    public void clickNavMenu_openNavDrawer() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));

        //onView(withContentDescription("Navigate up")).perform(click());

        //onView(withId(R.id.drawer)).check(matches(isOpen(Gravity.LEFT)));
    }

}
