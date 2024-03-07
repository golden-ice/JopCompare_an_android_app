package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
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

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class QuitEditSettingTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    @Before
    public void setUp() {
        mainMenuController = new MainMenuController(context);
    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }

    public static final String weightYearlySalaryTyped = "3";
    private static final String weightYearlyBonusTyped = "2";
    private static final String weightRetirementBenefits = "4";
    private static final String weightRelocationStipend = "6";
    private static final String weightRSUAward = "5";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule  = new ActivityTestRule<> (MainActivity.class);


    @Test
    public void quitEditSetting() {

        Integer prevWeightYearlySalary = mainMenuController.getComparisonSettings().getWeightYearlySalary();
        Integer prevWeightYearlyBonus = mainMenuController.getComparisonSettings().getWeightYearlyBonus();
        Integer prevWeightRetirementBenefits = mainMenuController.getComparisonSettings().getWeightRetirementBenefits();
        Integer prevWeightRelocationStipend = mainMenuController.getComparisonSettings().getWeightRelocationStipend();
        Integer prevWeightRSUAward = mainMenuController.getComparisonSettings().getWeightRSUAward();

        onView(withId(R.id.maincomparisonSettings)).perform(click());
        onView(withId(R.id.settingYearlySalaryValue)).perform(replaceText(""), typeText(weightYearlySalaryTyped));
        onView(withId(R.id.settingYearlyBonusValue)).perform(replaceText(""), typeText(weightYearlyBonusTyped));
        onView(withId(R.id.settingRetireBenefitValue)).perform(replaceText(""), typeText(weightRetirementBenefits));
        onView(withId(R.id.settingRelocationStipendValue)).perform(replaceText(""), typeText(weightRelocationStipend));
        onView(withId(R.id.settingRSUAwardValue)).perform(replaceText(""), typeText(weightRSUAward), closeSoftKeyboard());
        onView(withId(R.id.settingExitButton)).perform(click());
        onView(withId(R.id.mainaddCurrentJobButton)).check(matches(withText("CURRENT JOB")));
        onView(withId(R.id.maincomparisonSettings)).check(matches(withText("SETTINGS")));
        onView(withId(R.id.mainAddJobOfferButton)).check(matches(withText("JOB OFFERS")));
        onView(withId(R.id.mainCompareJobsButton)).check(matches(withText("COMPARE JOBS")));

        assertEquals(mainMenuController.getComparisonSettings().getWeightYearlySalary(), prevWeightYearlySalary);
        assertEquals(mainMenuController.getComparisonSettings().getWeightYearlyBonus(), prevWeightYearlyBonus);
        assertEquals(mainMenuController.getComparisonSettings().getWeightRetirementBenefits(), prevWeightRetirementBenefits);
        assertEquals(mainMenuController.getComparisonSettings().getWeightRelocationStipend(), prevWeightRelocationStipend);
        assertEquals(mainMenuController.getComparisonSettings().getWeightRSUAward(), prevWeightRSUAward);

    }


}
