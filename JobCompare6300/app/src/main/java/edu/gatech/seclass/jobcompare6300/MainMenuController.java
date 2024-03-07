package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import java.util.ArrayList;

public class MainMenuController {
    private Context context;
    private JobManager jobManager;
    private ComparisonManager comparisonManager;
    private DatabaseController dbController;

    public MainMenuController() {
        this.jobManager = new JobManager();
        this.comparisonManager = new ComparisonManager();
    }

    public MainMenuController(Context context) {
        this.jobManager = new JobManager(context);
        this.comparisonManager = new ComparisonManager(context);
        this.dbController = new DatabaseController(context);
    }

    private void calculateScore(Job job)
    {
        float AYS = job.getYearlySalary() / job.getLivingCost() * 100;
        float AYB = job.getYearlyBonus() / job.getLivingCost() * 100;
        float yearlyRSU = job.getRSUAward() / 4;
        float RS = job.getRelocationStipend();
        float RB = (job.getRetirementBenefits() * AYS) / 100;
        float score =  AYB + AYS + RS + yearlyRSU + RB;
        job.setScore(score);
    }

    // Interact with JobManager
    public ArrayList<Job> getRankedJobs()
    {
        jobManager.setJobs(getJobOffersDB());
        for(Job offer : jobManager.getJobs()){
            calculateScore(offer);
        }
        return jobManager.getRankedJobList();
    }

    public ArrayList<Job> compareTwoJobs(Integer jobID1, Integer jobID2)
    {
        ArrayList<Job> comparedJobs = new ArrayList<>();
        jobManager.setCurrentJob(getCurrentJobDB());
        jobManager.setJobs(getJobOffersDB());
        comparedJobs.add(jobManager.getJobById(jobID1));
        comparedJobs.add(jobManager.getJobById(jobID2));
        return comparedJobs;
    }

    public void saveCurrentJob(String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward){
        if(dbController.fetchCurrentJob() == null){
            jobManager.addCurrentJob(title, company, locationCity, locationState, livingCost, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, RSUAward);
        } else {
            jobManager.updateCurrentJob(getCurrentJob().getJobID(), title, company, locationCity, locationState, livingCost, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, RSUAward, jobManager.getCurrentJob().getScore());
        }
        calculateScore(jobManager.getCurrentJob());
        // if there exists a current job, db should update; else, db should create a new current job
        dbController.saveJob(jobManager.getCurrentJob());
    }

    // When the user is trying to add a job offer, although the private attribute jobs in the JobManager is an ArrayList<Job>, there is only one Job there
    public void saveJobOffer(String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward){
        Job job = new Job(title, company, locationCity, locationState, livingCost, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, RSUAward, false);
        calculateScore(job);
        jobManager.addJobOffer(job);
    }

    public JobManager getJobManager() {
        return this.jobManager;
    }


    public Job getLatestJobOfferDB(){
        return dbController.getJobMaxID();
    }

    public Job getCurrentJobDB() {
        return dbController.fetchCurrentJob();
    }

    // It seems that Ayushi defines Jobs as the Job Offers not including the current job; Bin defines the Job Offers here with the same meaning
    public ArrayList<Job> getJobOffersDB() {
        return dbController.fetchAllJobOffers();
    }

    public Job getCurrentJob() {
        return this.jobManager.getCurrentJob();
    }

    // Interact with ComparisonManager
    public ComparisonManager getComparisonManager() {
        return this.comparisonManager;
    }

    public void updateComparisonSettings(Integer weightYearlySalary, Integer weightYearlyBonus, Integer weightRetirementBenefits, Integer weightRelocationStipend, Integer weightRSUAward){
        comparisonManager.updateSettings(weightYearlySalary, weightYearlyBonus, weightRetirementBenefits, weightRelocationStipend, weightRSUAward);
    }

    public ComparisonSettings getComparisonSettings() {
        return this.comparisonManager.getComparisonSettings();
    }
}
