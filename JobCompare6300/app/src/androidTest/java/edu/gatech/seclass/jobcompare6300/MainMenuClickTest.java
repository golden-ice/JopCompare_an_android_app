package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class MainMenuClickTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule  = new ActivityTestRule<> (MainActivity.class);

    @Test
    public void mainMenuClickAddCurrentJobButton() {
        onView(withId(R.id.mainaddCurrentJobButton)).perform(click());
        onView(withId(R.id.addJobViewTitle)).check(matches(withText("Add/Edit Current Job Details")));

    }

    public void mainMenuClickAddJobOfferButton() {
        onView(withId(R.id.mainAddJobOfferButton)).perform(click());
        onView(withId(R.id.offerViewTitleText)).check(matches(withText("Add New Offer")));

    }

    public void mainMenuClickComparisonSettingsButton() {
        onView(withId(R.id.maincomparisonSettings)).perform(click());
        onView(withId(R.id.settingViewTitle)).check(matches(withText("Adjust Comparison Settings")));

    }

    public void mainMenuClickCompareJobsButton() {
        onView(withId(R.id.mainCompareJobsButton)).perform(click());
        onView(withId(R.id.compareViewTitleText)).check(matches(withText("Compare Offers")));

    }
}