package edu.gatech.seclass.jobcompare6300;

public class Job {
    private String title;
    private String company;
    private String locationCity;
    private String locationState;
    private float livingCost;
    private float yearlySalary;
    private float yearlyBonus;
    private Integer retirementBenefits;
    private float relocationStipend;
    private float RSUAward;
    private static int jobIDIndex;
    private int jobID;
    private float score;
    private boolean isCurrentJob;

    public Job(String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward, boolean isCurrentJob) {
        this.title = title;
        this.company = company;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.livingCost = livingCost;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationStipend = relocationStipend;
        this.RSUAward = RSUAward;
        this.jobID = this.jobIDIndex++;
        this.score = 0;
        this.isCurrentJob = isCurrentJob;
    }

    public Job(int jobId, String title, String company, String locationCity, String locationState, float livingCost, float yearlySalary, float yearlyBonus, Integer retirementBenefits, float relocationStipend, float RSUAward, boolean isCurrentJob, float score) {
        this.title = title;
        this.company = company;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.livingCost = livingCost;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationStipend = relocationStipend;
        this.RSUAward = RSUAward;
        this.jobID = jobId;
        this.score = score;
        this.isCurrentJob = isCurrentJob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public float getLivingCost() {
        return livingCost;
    }

    public void setLivingCost(Integer livingCost) {
        this.livingCost = livingCost;
    }

    public float getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(float yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public float getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(float yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public Integer getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(Integer retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public float getRelocationStipend() {
        return relocationStipend;
    }

    public void setRelocationStipend(float relocationStipend) {
        this.relocationStipend = relocationStipend;
    }

    public float getRSUAward() {
        return RSUAward;
    }

    public void setRSUAward(float RSUAward) {
        this.RSUAward = RSUAward;
    }

    public Integer getJobID() {
        return jobID;
    }

    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }
}
