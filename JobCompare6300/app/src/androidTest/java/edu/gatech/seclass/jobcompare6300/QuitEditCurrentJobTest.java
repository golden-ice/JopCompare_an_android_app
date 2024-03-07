package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class QuitEditCurrentJobTest {

    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    public static final String jobTitleValueTyped = "Back-end Developer";
    private static final String jobCompanyValueTyped = "Google";
    private static final String jobLocation1ValueTyped = "NYC";
    private static final String jobLocation2ValueTyped = "NYC";
    private static final String jobCostLivingValueTyped = "267";
    private static final String jobYearlySalaryValueTyped = "120000";
    private static final String jobYearlyBonusTyped = "20000";
    private static final String jobRetireBenefitValueTyped = "5";
    private static final String jobRelocationStipendValueTyped = "5000";
    private static final String jobRSUAwardValueTyped = "40000";

    @Rule
    public ActivityTestRule<EnterCurrentJobView> activityTestRule  = new ActivityTestRule<> (EnterCurrentJobView.class);

    @BeforeClass
    public static void beforeClass() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("comparejobs.db");
    }

    @AfterClass
    public static void afterClass() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("comparejobs.db");
    }

    @Before
    public void setUp() {
        mainMenuController = new MainMenuController(context);
        mainMenuController.saveCurrentJob("MLE", "Tik Tok", "Los Angeles", "California", 221, 120000, 22000, 5, 11000, 33000);

    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }

    @Test
    public void quitEditCurrentJob() {
        onView(withId(R.id.jobTitleValue)).perform(clearText(), typeText(jobTitleValueTyped));
        onView(withId(R.id.jobCompanyValue)).perform(clearText(), typeText(jobCompanyValueTyped));
        onView(withId(R.id.jobLocation1Value)).perform(clearText(), typeText(jobLocation1ValueTyped));
        onView(withId(R.id.jobLocation2Value)).perform(clearText(), typeText(jobLocation2ValueTyped));
        onView(withId(R.id.jobCostLivingValue)).perform(clearText(), typeText(jobCostLivingValueTyped));
        onView(withId(R.id.jobYearlySalaryValue)).perform(clearText(), typeText(jobYearlySalaryValueTyped));
        onView(withId(R.id.jobYearlyBonusValue)).perform(clearText(), typeText(jobYearlyBonusTyped));
        onView(withId(R.id.jobRetireBenefitValue)).perform(scrollTo(), clearText(), typeText(jobRetireBenefitValueTyped));
        onView(withId(R.id.jobRelocationStipendValue)).perform(scrollTo(), clearText(), typeText(jobRelocationStipendValueTyped));
        onView(withId(R.id.jobRSUAwardValue)).perform(scrollTo(), clearText(), typeText(jobRSUAwardValueTyped));

        onView(withId(R.id.jobExitButton)).perform(scrollTo(), click());
        onView(withId(R.id.mainaddCurrentJobButton)).check(matches(withText("CURRENT JOB")));
        onView(withId(R.id.maincomparisonSettings)).check(matches(withText("SETTINGS")));
        onView(withId(R.id.mainAddJobOfferButton)).check(matches(withText("JOB OFFERS")));
        onView(withId(R.id.mainCompareJobsButton)).check(matches(withText("COMPARE JOBS")));

        onView(withId(R.id.mainaddCurrentJobButton)).perform(click());
        onView(withId(R.id.jobTitleValue)).check(matches(withText("MLE")));
        onView(withId(R.id.jobCompanyValue)).check(matches(withText("Tik Tok")));
        onView(withId(R.id.jobLocation1Value)).check(matches(withText("California")));
        onView(withId(R.id.jobLocation2Value)).check(matches(withText("Los Angeles")));
        onView(withId(R.id.jobCostLivingValue)).check(matches(withText("221.000000")));
        onView(withId(R.id.jobYearlySalaryValue)).check(matches(withText("120000.000000")));
        onView(withId(R.id.jobYearlyBonusValue)).check(matches(withText("22000.000000")));
        onView(withId(R.id.jobRetireBenefitValue)).check(matches(withText("5")));
        onView(withId(R.id.jobRelocationStipendValue)).check(matches(withText("11000.000000")));
        onView(withId(R.id.jobRSUAwardValue)).check(matches(withText("33000.000000")));

    }
}
