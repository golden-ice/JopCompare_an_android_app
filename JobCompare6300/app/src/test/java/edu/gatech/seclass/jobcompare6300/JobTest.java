package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;


public class JobTest {

    JobManager jobManager = new JobManager();
    Job job1 = new Job("Software Engineer", "Microsoft", "Redmund", "Washington", 50, 250000,  25000, 500000, 200000,  400000, false);
    Job job2 = new Job("Software Engineer", "Google", "Mountain View", "California", 70, 350000,  45000, 500000, 200000,  500000, false);

    @Test
    public void getJobTitleAndCompanyTest() {
        assertEquals(job1.getTitle(), "Software Engineer");
        assertEquals(job2.getCompany(), "Google");
    }

    @Test
    public void getJobLocationTest() {
        assertEquals(job1.getLocationCity(), "Redmund");
        assertEquals(job2.getLocationState(), "California");
    }

    @Test
    public void getJobSalaryTest() {
        assertEquals(job1.getYearlySalary(), 250000,0.0f);
        assertEquals(job2.getYearlySalary(), 350000,0.0f);
    }

    @Test
    public void getJobBonusTest() {
        assertEquals(job1.getYearlyBonus(), 25000,0.0f);
        assertEquals(job2.getYearlyBonus(), 45000,0.0f);
    }

}