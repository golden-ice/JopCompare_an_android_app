package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class JobManager {
    private ArrayList<Job> jobs;
    private Job currentJob;
    private DatabaseController dbController;


    public JobManager() {
        this.jobs = new ArrayList<>();
        this.currentJob = null;
    }

    public JobManager(Context context) {
        this.jobs = new ArrayList<>();
        this.currentJob = null;
        dbController = new DatabaseController(context);
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }
    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }
    public ArrayList<Job> getJobs() {
        if(this.jobs == null){
            this.jobs = dbController.fetchAllJobOffers();
        }
        return this.jobs;
    }

    public Job getCurrentJob()
    {
        if(this.currentJob == null) this.currentJob = dbController.fetchCurrentJob();
        return this.currentJob;
    }

    public void addCurrentJob(String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward)
    {
        Job currentJob = new Job(title, company, locationCity, locationState, livingCost, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, RSUAward,true);
        this.currentJob = currentJob;
        dbController.saveJob(this.currentJob);
    }

    public void addJobOffer(String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward)
    {
        Job newOffer = new Job(title, company, locationCity, locationState, livingCost, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, RSUAward,false);
        addJobOffer(newOffer);
    }

    public void addJobOffer(Job job){
        Job lastJob  = dbController.getJobMaxID();
        if (lastJob != null) {
            job.setJobID(lastJob.getJobID() + 1);
        }
        this.jobs.add(job);
        dbController.saveJob(job);
    }

    public void updateCurrentJob(int jobId, String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward, float score)
    {
        this.currentJob = new Job(jobId, title, company, locationCity, locationState, livingCost, yearlySalary, yearlyBonus, retirementBenefits, relocationStipend, RSUAward, true, score);
        dbController.updateCurrentJob(this.currentJob);
    }

    public ArrayList<Job> getAllJobOffers()
    {
        return dbController.fetchAllJobOffers();
    }


    public void updateScore(Job job, int score)
    {
        job.setScore(score);
    }

    public ArrayList<Job> getRankedJobList() {
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
                public int compare(Job j1, Job j2) {
                    return j2.getScore() >= j1.getScore() ? 1 : -1;
                }
        });
        for(Job offer: this.jobs) pq.add(offer);
        ArrayList<Job> jobs = new ArrayList<>();
        while(!pq.isEmpty()){
            jobs.add(pq.poll());
        }
        return jobs;
    }

    public Job getJobById(Integer jobId){
        if(this.currentJob.getJobID() == jobId) return this.currentJob;
        for(Job job : this.jobs){
            if(job.getJobID() == jobId) return job;
        }
        return null;
    }

}
