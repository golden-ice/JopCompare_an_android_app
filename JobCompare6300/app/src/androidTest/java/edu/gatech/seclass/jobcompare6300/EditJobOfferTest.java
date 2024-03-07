package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

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

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EditJobOfferTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    public static final String offerTitleValueTyped = "Back-end Developer";
    private static final String offerCompanyValueTyped = "Google";
    private static final String offerLocation1ValueTyped = "NYC";
    private static final String offerLocation2ValueTyped = "NYC";
    private static final String offerCostLivingValueTyped = "267";
    private static final String offerYearlySalaryValueTyped = "120000";
    private static final String offerYearlyBonusTyped = "20000";
    private static final String offerRetireBenefitValueTyped = "5";
    private static final String offerRelocationStipendValueTyped = "5000";
    private static final String offerRSUAwardValueTyped = "40000";

    @Before
    public void setUp() {
        mainMenuController = new MainMenuController(context);
    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule  = new ActivityTestRule<> (MainActivity.class);


    @Test
    public void quitEditCurrentJob() {
        ArrayList<Job> prev_job_list = mainMenuController.getJobManager().getRankedJobList();
        onView(withId(R.id.mainAddJobOfferButton)).perform(click());
        onView(withId(R.id.offerEnterNewOfferButton)).perform(click());

        onView(withId(R.id.offerTitleValue)).perform(typeText(offerTitleValueTyped));
        onView(withId(R.id.offerCompanyValue)).perform(typeText(offerCompanyValueTyped));
        onView(withId(R.id.offerLocation1Value)).perform(typeText(offerLocation1ValueTyped));
        onView(withId(R.id.offerLocation2Value)).perform(scrollTo(),typeText(offerLocation2ValueTyped));
        onView(withId(R.id.offerCostLivingValue)).perform(scrollTo(),typeText(offerCostLivingValueTyped));
        onView(withId(R.id.offerYearlySalaryValue)).perform(scrollTo(),typeText(offerYearlySalaryValueTyped));
        onView(withId(R.id.offerYearlyBonusValue)).perform(scrollTo(),typeText(offerYearlyBonusTyped));
        onView(withId(R.id.offerRetireBenefitValue)).perform(scrollTo(),typeText(offerRetireBenefitValueTyped));
        onView(withId(R.id.offerRelocationStipendValue)).perform(scrollTo(), typeText(offerRelocationStipendValueTyped));
        onView(withId(R.id.offerRSUAwardValue)).perform(scrollTo(),typeText(offerRSUAwardValueTyped), closeSoftKeyboard());

        onView(withId(R.id.offerBackButton)).perform(click());

        onView(withId(R.id.mainaddCurrentJobButton)).check(matches(withText("CURRENT JOB")));
        onView(withId(R.id.maincomparisonSettings)).check(matches(withText("SETTINGS")));
        onView(withId(R.id.mainAddJobOfferButton)).check(matches(withText("JOB OFFERS")));
        onView(withId(R.id.mainCompareJobsButton)).check(matches(withText("COMPARE JOBS")));

        assertEquals(mainMenuController.getJobManager().getRankedJobList(), prev_job_list);

    }

    @Test
    public void SaveEditCurrentJob() {
        onView(withId(R.id.mainAddJobOfferButton)).perform(click());
        onView(withId(R.id.offerEnterNewOfferButton)).perform(click());
        onView(withId(R.id.offerTitleValue)).perform(typeText(offerTitleValueTyped));
        onView(withId(R.id.offerCompanyValue)).perform(typeText(offerCompanyValueTyped));
        onView(withId(R.id.offerLocation1Value)).perform(typeText(offerLocation1ValueTyped));
        onView(withId(R.id.offerLocation2Value)).perform(scrollTo(),typeText(offerLocation2ValueTyped));
        onView(withId(R.id.offerCostLivingValue)).perform(scrollTo(),typeText(offerCostLivingValueTyped));
        onView(withId(R.id.offerYearlySalaryValue)).perform(scrollTo(),typeText(offerYearlySalaryValueTyped));
        onView(withId(R.id.offerYearlyBonusValue)).perform(scrollTo(),typeText(offerYearlyBonusTyped));
        onView(withId(R.id.offerRetireBenefitValue)).perform(scrollTo(),typeText(offerRetireBenefitValueTyped));
        onView(withId(R.id.offerRelocationStipendValue)).perform(scrollTo(), typeText(offerRelocationStipendValueTyped));
        onView(withId(R.id.offerRSUAwardValue)).perform(scrollTo(),typeText(offerRSUAwardValueTyped), closeSoftKeyboard());

        onView(withId(R.id.offerSaveOfferButton)).perform(click());
        onView(withId(R.id.offerBackButton)).perform(click());

        onView(withId(R.id.mainaddCurrentJobButton)).check(matches(withText("CURRENT JOB")));
        onView(withId(R.id.maincomparisonSettings)).check(matches(withText("SETTINGS")));
        onView(withId(R.id.mainAddJobOfferButton)).check(matches(withText("JOB OFFERS")));
        onView(withId(R.id.mainCompareJobsButton)).check(matches(withText("COMPARE JOBS")));


        ArrayList<Job> allJobList = mainMenuController.getJobManager().getAllJobOffers();
        Job offerAdded = allJobList.get(allJobList.size() - 1);
        assertEquals(offerAdded.getTitle(), "Back-end Developer");
        assertEquals(offerAdded.getCompany(), "Google");
        assertEquals(offerAdded.getLocationCity(), "NYC");
        assertEquals(offerAdded.getLocationState(), "NYC");
        assertEquals(offerAdded.getLivingCost(), 267, 0.001);
        assertEquals(offerAdded.getYearlySalary(), 120000, 0.001);
        assertEquals(offerAdded.getYearlyBonus(), 20000, 0.001);
        assertEquals(offerAdded.getRetirementBenefits(), 5, 0.001);
        assertEquals(offerAdded.getRelocationStipend(), 5000, 0.001);
        assertEquals(offerAdded.getRSUAward(), 40000, 0.001);
        assertEquals(offerAdded.isCurrentJob(), false);

    }
}
