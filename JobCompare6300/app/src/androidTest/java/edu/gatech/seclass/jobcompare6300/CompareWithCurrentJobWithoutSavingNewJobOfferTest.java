package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
public class CompareWithCurrentJobWithoutSavingNewJobOfferTest {
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

    @Rule
    public ActivityTestRule<AddJobView> activityTestRule  = new ActivityTestRule<> (AddJobView.class);

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
        mainMenuController.saveCurrentJob("Software Developer", "Apple", "Los Angeles", "California", 221, 110000, 20000, 5, 10000, 30000);
    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }

    @Test
    public void compareWithCurrentJobShow() throws InterruptedException {
        onView(withId(R.id.offerTitleValue)).perform(typeText(offerTitleValueTyped));
        onView(withId(R.id.offerCompanyValue)).perform(typeText(offerCompanyValueTyped));
        onView(withId(R.id.offerLocation1Value)).perform(typeText(offerLocation1ValueTyped));
        onView(withId(R.id.offerLocation2Value)).perform(typeText(offerLocation2ValueTyped));
        onView(withId(R.id.offerCostLivingValue)).perform(typeText(offerCostLivingValueTyped));
        onView(withId(R.id.offerYearlySalaryValue)).perform(typeText(offerYearlySalaryValueTyped));
        onView(withId(R.id.offerYearlyBonusValue)).perform(typeText(offerYearlyBonusTyped));
        onView(withId(R.id.offerRetireBenefitValue)).perform(scrollTo(), typeText(offerRetireBenefitValueTyped));
        onView(withId(R.id.offerRelocationStipendValue)).perform(scrollTo(), typeText(offerRelocationStipendValueTyped));
        onView(withId(R.id.offerRSUAwardValue)).perform(scrollTo(), typeText(offerRSUAwardValueTyped));

        onView(withId(R.id.offerCompareWithCurrentButton)).perform(scrollTo(), click());
    }
}
