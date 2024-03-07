package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class MainMenuControllerTest {

    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private MainMenuController mainMenuController;

    @Before
    public void setUp() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("comparejobs.db");
        mainMenuController = new MainMenuController(context);
    }

    @After
    public void tearDown() {
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("comparejobs.db");
        mainMenuController = null;
    }


    @Test
    public void saveCurrentJobFirstTimeSuccessTest() {
        mainMenuController.saveCurrentJob("Software Engineer", "Apple", "Cupertino", "California", 50, 200000, 15000, 500000, 200000, 400000);
        assertEquals(mainMenuController.getJobManager().getCurrentJob().getTitle(), "Software Engineer");
        assertEquals(mainMenuController.getJobManager().getCurrentJob().getCompany(), "Apple");
    }

    @Test
    public void saveCurrentJobSecondTimeSuccessTest() {
        mainMenuController.saveCurrentJob("Software Engineer", "Apple", "Cupertino", "California", 50, 200000, 15000, 500000, 200000, 400000);
        assertEquals(mainMenuController.getJobManager().getCurrentJob().getTitle(), "Software Engineer");
        assertEquals(mainMenuController.getJobManager().getCurrentJob().getCompany(), "Apple");
        mainMenuController.saveCurrentJob("Senior Software Engineer", "Apple", "Cupertino", "California", 50, 220000, 15000, 500000, 200000, 400000);
        assertEquals(mainMenuController.getJobManager().getCurrentJob().getTitle(), "Senior Software Engineer");
        assertTrue(mainMenuController.getJobManager().getCurrentJob().getYearlySalary() == 220000.0);
    }

    @Test
    public void saveFirstJobOfferSuccessTest() {
        mainMenuController.saveJobOffer("Software Engineer", "Microsoft", "Redmund", "Washington", 50, 250000, 25000, 500000, 200000, 400000);
        assertEquals(mainMenuController.getJobManager().getJobs().size(), 1);
        assertEquals(mainMenuController.getJobManager().getJobs().get(0).getTitle(), "Software Engineer");
        assertEquals(mainMenuController.getJobManager().getJobs().get(0).getCompany(), "Microsoft");
    }

    @Test
    public void saveSecondJobOfferSuccessTest() {
        mainMenuController.saveJobOffer("Software Engineer", "Microsoft", "Redmund", "Washington", 50, 250000, 25000, 500000, 200000, 400000);
        assertEquals(mainMenuController.getJobManager().getJobs().size(), 1);
        assertEquals(mainMenuController.getJobManager().getJobs().get(0).getTitle(), "Software Engineer");
        assertEquals(mainMenuController.getJobManager().getJobs().get(0).getCompany(), "Microsoft");
        mainMenuController.saveJobOffer("Software Engineer", "Google", "Mountain View", "California", 70, 350000, 45000, 500000, 200000, 500000);
        assertEquals(mainMenuController.getJobManager().getJobs().size(), 2);
        assertEquals(mainMenuController.getJobManager().getJobs().get(1).getTitle(), "Software Engineer");
        assertEquals(mainMenuController.getJobManager().getJobs().get(1).getCompany(), "Google");
    }


    @Test
    public void getRankedJobSuccessTest() {
        JobManager jobManager = mainMenuController.getJobManager();
        jobManager.addCurrentJob("Software Engineer", "Apple", "Cupertino", "California", 50, 200000, 15000, 500000, 200000, 400000);
        jobManager.addJobOffer("Software Engineer", "Microsoft", "Redmund", "Washington", 50, 250000, 25000, 500000, 200000, 400000);
        jobManager.addJobOffer("Software Engineer", "Google", "Mountain View", "California", 70, 350000, 45000, 500000, 200000, 500000);
        ArrayList<Job> rankedJobs = mainMenuController.getRankedJobs();
        assertEquals(rankedJobs.size(), 3);
        assertEquals(rankedJobs.get(0).getCompany(), "Google");
        assertEquals(rankedJobs.get(1).getCompany(), "Microsoft");
        assertEquals(rankedJobs.get(2).getCompany(), "Apple");
    }

    @Test
    public void compareJobsSuccessTest() {
        mainMenuController.getJobManager().addCurrentJob("Software Engineer", "Apple", "Cupertino", "California", 50, 200000, 15000, 500000, 200000, 400000);
        mainMenuController.getJobManager().addJobOffer("Software Engineer", "Microsoft", "Redmund", "Washington", 50, 250000, 25000, 500000, 200000, 400000);
        mainMenuController.getJobManager().addJobOffer("Software Engineer", "Google", "Mountain View", "California", 70, 350000, 45000, 500000, 200000, 500000);
        ArrayList<Job> rankedJobs = mainMenuController.compareTwoJobs(1, 2);
        assertEquals(rankedJobs.size(), 2);
        //assertEquals(rankedJobs.get(0).getCompany(), "Microsoft");
        //assertEquals(rankedJobs.get(1).getCompany(), "Google");
    }
}
