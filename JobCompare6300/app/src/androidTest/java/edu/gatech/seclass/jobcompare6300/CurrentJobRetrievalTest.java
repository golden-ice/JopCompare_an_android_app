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

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrentJobRetrievalTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

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
    }

    @After
    public void tearDown() {
        mainMenuController = null;
    }

    @Test
    public void enterNewCurrentJobTest(){
        mainMenuController.saveCurrentJob("Software Developer", "Apple", "Los Angeles", "California", 221, 110000, 20000, 5, 10000, 30000);
        assertEquals(mainMenuController.getCurrentJob().getTitle(), "Software Developer");
        assertEquals(mainMenuController.getCurrentJob().getCompany(), "Apple");
        assertEquals(mainMenuController.getCurrentJob().getLocationCity(), "Los Angeles");
        assertEquals(mainMenuController.getCurrentJob().getLocationState(), "California");
        assertEquals(mainMenuController.getCurrentJob().getLivingCost(), 221.0, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().getYearlySalary(), 110000.0,  0.0f);
        assertEquals(mainMenuController.getCurrentJob().getYearlyBonus(), 20000.0,  0.0f);
        assertEquals(mainMenuController.getCurrentJob().getRetirementBenefits(), 5.0,  0.0f);
        assertEquals(mainMenuController.getCurrentJob().getRelocationStipend(), 10000.0,  0.0f);
        assertEquals(mainMenuController.getCurrentJob().getRSUAward(), 30000.0,  0.0f);
        assertEquals(mainMenuController.getCurrentJob().isCurrentJob(), true);
    }


    @Test
    public void editCurrentJobTest(){
        mainMenuController.saveCurrentJob("Software Developer", "Apple", "Los Angeles", "California", 221, 110000, 20000, 5, 10000, 30000);
        mainMenuController.saveCurrentJob("Data Scientist", "Bain", "New York City", "New York City", 271, 80000, 15000, 6, 8000, 40000);
        assertEquals(mainMenuController.getCurrentJob().getTitle(), "Data Scientist");
        assertEquals(mainMenuController.getCurrentJob().getCompany(), "Bain");
        assertEquals(mainMenuController.getCurrentJob().getLocationCity(), "New York City");
        assertEquals(mainMenuController.getCurrentJob().getLocationState(), "New York City");
        assertEquals(mainMenuController.getCurrentJob().getLivingCost(), 271, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().getYearlySalary(), 80000, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().getYearlyBonus(), 15000, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().getRetirementBenefits(), 6, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().getRelocationStipend(), 8000, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().getRSUAward(), 40000, 0.0f);
        assertEquals(mainMenuController.getCurrentJob().isCurrentJob(), true);
    }
}
