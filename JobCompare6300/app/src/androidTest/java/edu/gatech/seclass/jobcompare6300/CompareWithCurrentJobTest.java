package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

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
public class CompareWithCurrentJobTest {
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
    public void compareWithCurrentJobShow(){
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

        onView(withId(R.id.offerSaveOfferButton)).perform(scrollTo(), click());
        onView(withId(R.id.offerCompareWithCurrentButton)).perform(scrollTo(), click());

        onView(withId(R.id.job_offer_table)).check(matches(not(isDisplayed())));
        onView(withId(R.id.compareJobLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.job_compare_table)).check(matches(isDisplayed()));

        onView(withId(R.id.offerCompareTitleCurrent)).check(matches(withText("Software Developer")));
        onView(withId(R.id.offerCompareTitleOffer)).check(matches(withText(offerTitleValueTyped)));
        onView(withId(R.id.offerCompareCompanyNameCurrent)).check(matches(withText("Apple")));
        onView(withId(R.id.offerCompareCompanyNameOffer)).check(matches(withText(offerCompanyValueTyped)));
        onView(withId(R.id.offerCompareLocation1Current)).check(matches(withText("California")));
        onView(withId(R.id.offerCompareLocation1Offer)).check(matches(withText(offerLocation1ValueTyped)));
        onView(withId(R.id.offerCompareLocation2Current)).check(matches(withText("Los Angeles")));
        onView(withId(R.id.offerCompareLocation2Offer)).check(matches(withText(offerLocation2ValueTyped)));
        onView(withId(R.id.offerCompareSalaryAdjustedCurrent)).check(matches(withText("49773.754")));
        onView(withId(R.id.offerCompareSalaryAdjustedOffer)).check(matches(withText("44943.82")));
        onView(withId(R.id.offerCompareBonusAdjustedCurrent)).check(matches(withText("9049.773")));
        onView(withId(R.id.offerCompareBonusAdjustedOffer)).check(matches(withText("7490.636")));
        onView(withId(R.id.offerCompareRetireBenefitCurrent)).check(matches(withText("2488.6877")));
        onView(withId(R.id.offerCompareRetireBenefitOffer)).check(matches(withText("2247.191")));
        onView(withId(R.id.offerCompareRelocationStipendCurrent)).check(matches(withText("10000.0")));
        onView(withId(R.id.offerCompareRelocationStipendOffer)).check(matches(withText("5000.0")));
        onView(withId(R.id.offerCompareRSUAwardCurrent)).check(matches(withText("30000.0")));
        onView(withId(R.id.offerCompareRSUAwardOffer)).check(matches(withText("40000.0")));
    }



}
