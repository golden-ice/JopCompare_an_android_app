package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;

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
public class CompareJobOfferWithCurrentJobAndJobOffersAddedTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    @Rule
    public ActivityTestRule<EnterCurrentJobView> activityTestRule = new ActivityTestRule<>(EnterCurrentJobView.class);

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
    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }

    @Test
    public void compareJobsShow() {
        mainMenuController.saveCurrentJob("Software Developer", "Apple", "Los Angeles", "California", 221, 110000, 20000, 5, 10000, 30000);
        onView(withId(R.id.jobExitButton)).perform(click());
        onView(withId(R.id.mainAddJobOfferButton)).perform(click());
        mainMenuController.saveJobOffer("MLE", "TwoSigma", "Los Angeles", "California", 221, 220000, 40000, 5, 20000, 35000);
        mainMenuController.saveJobOffer("Data Analyst", "Fake Bank", "Los Angeles", "California", 221, 80000, 20000, 3, 10000, 25000);
        onView(withId(R.id.offerBackButton)).perform(click());
        onView(withId(R.id.mainCompareJobsButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.jobList)).atPosition(0).onChildView(withId(R.id.jobTitle)).check(matches(withText("MLE")));
        onData(anything()).inAdapterView(withId(R.id.jobList)).atPosition(0).onChildView(withId(R.id.jobCompany)).check(matches(withText("TwoSigma")));
        onData(anything()).inAdapterView(withId(R.id.jobList)).atPosition(1).onChildView(withId(R.id.jobTitle)).check(matches(withText("Software Developer")));
        onData(anything()).inAdapterView(withId(R.id.jobList)).atPosition(1).onChildView(withId(R.id.jobCompany)).check(matches(withText("Apple")));
        onData(anything()).inAdapterView(withId(R.id.jobList)).atPosition(2).onChildView(withId(R.id.jobTitle)).check(matches(withText("Data Analyst")));
        onData(anything()).inAdapterView(withId(R.id.jobList)).atPosition(2).onChildView(withId(R.id.jobCompany)).check(matches(withText("Fake Bank")));
    }
}
