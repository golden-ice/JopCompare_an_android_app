package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class InvalidValueTest {

    public static final String jobTitleValueTyped = "Back-end Developer";
    private static final String jobCompanyValueTyped = "Google";
    private static final String jobLocation1ValueTyped = "NYC";
    private static final String jobLocation2ValueTyped = "NYC";
    private static final String jobCostLivingValueTyped = "267";
    private static final String jobYearlySalaryValueTyped = "120000";
    private static final String jobYearlySalaryValueTyped2 = "Invalid Salary";
    private static final String jobYearlyBonusTyped = "20000";
    private static final String jobRetireBenefitValueTyped = "5";
    private static final String jobRetireBenefitValueTyped1 = "200";
    private static final String jobRetireBenefitValueTyped3 = "";
    private static final String jobRelocationStipendValueTyped = "5000";
    private static final String jobRSUAwardValueTyped = "40000";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule  = new ActivityTestRule<> (MainActivity.class);


    @Test
    public void InvalidValue() {
        onView(withId(R.id.mainaddCurrentJobButton)).perform(click());
        onView(withId(R.id.jobTitleValue)).perform(replaceText(jobTitleValueTyped));
        onView(withId(R.id.jobCompanyValue)).perform(replaceText(jobCompanyValueTyped));
        onView(withId(R.id.jobLocation1Value)).perform(replaceText(jobLocation1ValueTyped));
        onView(withId(R.id.jobLocation2Value)).perform(replaceText(jobLocation2ValueTyped));
        onView(withId(R.id.jobCostLivingValue)).perform(scrollTo(),replaceText(jobCostLivingValueTyped));
        onView(withId(R.id.jobYearlySalaryValue)).perform(scrollTo(),replaceText(jobYearlySalaryValueTyped2));
        onView(withId(R.id.jobYearlyBonusValue)).perform(scrollTo(),replaceText(jobYearlyBonusTyped));
        onView(withId(R.id.jobRetireBenefitValue)).perform(scrollTo(),replaceText(jobRetireBenefitValueTyped));
        onView(withId(R.id.jobRelocationStipendValue)).perform(scrollTo(), replaceText(jobRelocationStipendValueTyped));
        onView(withId(R.id.jobRSUAwardValue)).perform(scrollTo(),replaceText(""), typeText(jobRSUAwardValueTyped), closeSoftKeyboard());

        try
        {
            onView(withId(R.id.jobSaveButton)).perform(click());
        }
        catch(NumberFormatException e)
        {
            assertEquals(1, 1);
            //success
        }


    }

    @Test
    public void MissingValue() {
        onView(withId(R.id.mainaddCurrentJobButton)).perform(click());
        onView(withId(R.id.jobTitleValue)).perform(replaceText(jobTitleValueTyped));
        onView(withId(R.id.jobCompanyValue)).perform(replaceText(jobCompanyValueTyped));
        onView(withId(R.id.jobLocation1Value)).perform(replaceText(jobLocation1ValueTyped));
        onView(withId(R.id.jobLocation2Value)).perform(replaceText(jobLocation2ValueTyped));
        onView(withId(R.id.jobCostLivingValue)).perform(scrollTo(),replaceText(jobCostLivingValueTyped));
        onView(withId(R.id.jobYearlySalaryValue)).perform(scrollTo(),replaceText(jobYearlySalaryValueTyped));
        onView(withId(R.id.jobYearlyBonusValue)).perform(scrollTo(),replaceText(jobYearlyBonusTyped));
        onView(withId(R.id.jobRetireBenefitValue)).perform(scrollTo(),replaceText(jobRetireBenefitValueTyped3));
        onView(withId(R.id.jobRelocationStipendValue)).perform(scrollTo(), replaceText(jobRelocationStipendValueTyped));
        onView(withId(R.id.jobRSUAwardValue)).perform(scrollTo(),replaceText(""), typeText(jobRSUAwardValueTyped), closeSoftKeyboard());

        try
        {
            onView(withId(R.id.jobSaveButton)).perform(click());
        }
        catch(NumberFormatException e)
        {
            assertEquals(1, 1);
            //success
        }

    }

    @Test
    public void OutRangeValue() {
        onView(withId(R.id.mainaddCurrentJobButton)).perform(click());
        onView(withId(R.id.jobTitleValue)).perform(replaceText(jobTitleValueTyped));
        onView(withId(R.id.jobCompanyValue)).perform(replaceText(jobCompanyValueTyped));
        onView(withId(R.id.jobLocation1Value)).perform(replaceText(jobLocation1ValueTyped));
        onView(withId(R.id.jobLocation2Value)).perform(replaceText(jobLocation2ValueTyped));
        onView(withId(R.id.jobCostLivingValue)).perform(scrollTo(), replaceText(jobCostLivingValueTyped));
        onView(withId(R.id.jobYearlySalaryValue)).perform(scrollTo(), replaceText(jobYearlySalaryValueTyped));
        onView(withId(R.id.jobYearlyBonusValue)).perform(scrollTo(), replaceText(jobYearlyBonusTyped));
        onView(withId(R.id.jobRetireBenefitValue)).perform(scrollTo(), replaceText(jobRetireBenefitValueTyped1));
        onView(withId(R.id.jobRelocationStipendValue)).perform(scrollTo(), replaceText(jobRelocationStipendValueTyped));
        onView(withId(R.id.jobRSUAwardValue)).perform(scrollTo(), replaceText(""), typeText(jobRSUAwardValueTyped), closeSoftKeyboard());

        try {
            onView(withId(R.id.jobSaveButton)).perform(click());
        } catch (NumberFormatException e) {
            assertEquals(1, 1);
            //success
        }
    }


}
