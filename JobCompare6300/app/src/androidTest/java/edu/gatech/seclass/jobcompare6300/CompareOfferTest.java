package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CompareOfferTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule  = new ActivityTestRule<> (MainActivity.class);

    @Before
    public void setUp() {
        mainMenuController = new MainMenuController(context);
    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }


    @Test
    public void RankedJobsDisplayed() {
        onView(withId(R.id.mainCompareJobsButton)).perform(click());

        onView(withId(R.id.jobList)).check(matches(isDisplayed()));

        onView(withId(R.id.compareExitButton)).perform(click());

    }



    @Test
    public void CompareOffersTwoTicked() {
        ArrayList<Job> cur_job_list = mainMenuController.getJobManager().getAllJobOffers();
        if(cur_job_list.size() < 2){
            System.out.print("No Enough Offers Added");
        } else{
            onView(withId(R.id.mainCompareJobsButton)).perform(click());

            onView(withId(R.id.jobList)).check(matches(isDisplayed()));

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(0).onChildView(withId(R.id.checkBox)).perform(click());

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(1).onChildView(withId(R.id.checkBox)).perform(click());

            onView(withId(R.id.compareNewComparisonButton)).perform(scrollTo(),click());

            onView(withId(R.id.compareTable)).check(matches(isDisplayed()));

            onView(withId(R.id.compareExitButton)).perform(click());
        }


    }

    @Test
    public void CompareOffersOneTicked() {
        ArrayList<Job> cur_job_list = mainMenuController.getJobManager().getAllJobOffers();
        if(cur_job_list.size() < 2){
            System.out.print("No Enough Offers Added");
        } else{
            onView(withId(R.id.mainCompareJobsButton)).perform(click());

            onView(withId(R.id.jobList)).check(matches(isDisplayed()));

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(0).onChildView(withId(R.id.checkBox)).perform(click());

            onView(withId(R.id.compareNewComparisonButton)).perform(scrollTo(),click());

            onView(withId(R.id.compareTable)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

            onView(withId(R.id.compareExitButton)).perform(click());
        }

    }

    @Test
    public void CompareOffersTwoTickedMultipleTimes() {
        ArrayList<Job> cur_job_list = mainMenuController.getJobManager().getAllJobOffers();
        if(cur_job_list.size() < 3){
            System.out.print("No Enough Offers Added");
        } else{
            onView(withId(R.id.mainCompareJobsButton)).perform(click());

            onView(withId(R.id.jobList)).check(matches(isDisplayed()));

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(0).onChildView(withId(R.id.checkBox)).perform(click());

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(1).onChildView(withId(R.id.checkBox)).perform(click());

            onView(withId(R.id.compareNewComparisonButton)).perform(scrollTo(),click());

            onView(withId(R.id.compareNewComparisonButton)).perform(click());


            onView(withId(R.id.jobList)).check(matches(isDisplayed()));

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(2).onChildView(withId(R.id.checkBox)).perform(click());

            onData(anything()).inAdapterView(allOf(withId(R.id.jobList), isDisplayed()))
                    .atPosition(0).onChildView(withId(R.id.checkBox)).perform(click());

            onView(withId(R.id.compareNewComparisonButton)).perform(scrollTo(),click());

            onView(withId(R.id.compareTable)).check(matches(isDisplayed()));

            onView(withId(R.id.compareExitButton)).perform(click());
        }

    }

}
