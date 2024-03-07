package edu.gatech.seclass.jobcompare6300;

public class ComparisonSettings {
    private Integer weightYearlySalary;
    private Integer weightYearlyBonus;
    private Integer weightRetirementBenefits;
    private Integer weightRelocationStipend;
    private Integer weightRSUAward;
    private Integer id;

    public ComparisonSettings(Integer weightYearlySalary, Integer weightYearlyBonus, Integer weightRetirementBenefits, Integer weightRelocationStipend, Integer weightRSUAward) {
        this.weightYearlySalary = weightYearlySalary;
        this.weightYearlyBonus = weightYearlyBonus;
        this.weightRetirementBenefits = weightRetirementBenefits;
        this.weightRelocationStipend = weightRelocationStipend;
        this.weightRSUAward = weightRSUAward;
        this.id = 0;
    }

    public ComparisonSettings() {
        this.weightYearlySalary = 1;
        this.weightYearlyBonus = 1;
        this.weightRetirementBenefits = 1;
        this.weightRelocationStipend = 1;
        this.weightRSUAward = 1;
        this.id = 0;
    }


    public Integer getWeightYearlySalary() {
        return weightYearlySalary;
    }

    public void setWeightYearlySalary(Integer weightYearlySalary) {
        this.weightYearlySalary = weightYearlySalary;
    }

    public Integer getWeightYearlyBonus() {
        return weightYearlyBonus;
    }

    public void setWeightYearlyBonus(Integer weightYearlyBonus) {
        this.weightYearlyBonus = weightYearlyBonus;
    }

    public Integer getWeightRetirementBenefits() {
        return weightRetirementBenefits;
    }

    public void setWeightRetirementBenefits(Integer weightRetirementBenefits) {
        this.weightRetirementBenefits = weightRetirementBenefits;
    }

    public Integer getWeightRelocationStipend() {
        return weightRelocationStipend;
    }

    public void setWeightRelocationStipend(Integer weightRelocationStipend) {
        this.weightRelocationStipend = weightRelocationStipend;
    }

    public Integer getWeightRSUAward() {
        return weightRSUAward;
    }

    public void setWeightRSUAward(Integer weightRSUAward) {
        this.weightRSUAward = weightRSUAward;
    }

    public Integer getId() {
        return id;
    }
}
