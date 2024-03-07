package edu.gatech.seclass.jobcompare6300;

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

import java.util.Optional;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdjustComparisonSettingsTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    @Rule
    public ActivityTestRule<ComparisonSettingsView> activityTestRule  = new ActivityTestRule<> (ComparisonSettingsView.class);

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
    public void generateDefaultComparisonSettingsTest(){
        assertEquals(Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightRelocationStipend()), Optional.ofNullable(1));
        assertEquals(Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightRetirementBenefits()), Optional.ofNullable(1));
        assertEquals(Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightRSUAward()), Optional.ofNullable(1));
        assertEquals(Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightYearlyBonus()), Optional.ofNullable(1));
        assertEquals(Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightYearlySalary()), Optional.ofNullable(1));

    }

    @Test
    public void tuneComparisonSettingsTest() {
        mainMenuController.updateComparisonSettings(2,3,4,5,8);
        assertEquals(java.util.Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightYearlySalary()), Optional.ofNullable(2));
        assertEquals(java.util.Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightYearlyBonus()), Optional.ofNullable(3));
        assertEquals(java.util.Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightRetirementBenefits()), Optional.ofNullable(4));
        assertEquals(java.util.Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightRelocationStipend()), Optional.ofNullable(5));
        assertEquals(java.util.Optional.ofNullable(mainMenuController.getComparisonSettings().getWeightRSUAward()), Optional.ofNullable(8));
    }

}
