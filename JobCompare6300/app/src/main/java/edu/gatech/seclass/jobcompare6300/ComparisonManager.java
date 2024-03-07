package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

public class ComparisonManager {
    private ComparisonSettings comparisonSettings;
    private DatabaseController dbController;

    public ComparisonManager() {
        this.comparisonSettings = new ComparisonSettings();
    }

    public ComparisonManager(Context context) {
        this.comparisonSettings = new ComparisonSettings();
        this.dbController = new DatabaseController(context);
    }

    public void updateSettings(Integer weightYearlySalary, Integer weightYearlyBonus, Integer weightRetirementBenefits, Integer weightRelocationStipend, Integer weightRSUAward) {
        this.comparisonSettings = new ComparisonSettings(weightYearlySalary, weightYearlyBonus, weightRetirementBenefits, weightRelocationStipend, weightRSUAward);
        dbController.updateComparisonWeights(this.comparisonSettings);
    }

    private void setComparisonSettings() {
        if(dbController.fetchComparisonSettings() == null) {
            this.comparisonSettings = new ComparisonSettings();
            dbController.saveComparisonWeights(this.comparisonSettings);
        } else {
            this.comparisonSettings = dbController.fetchComparisonSettings();
        }
    }

    public ComparisonSettings getComparisonSettings() {
        setComparisonSettings();
        return this.comparisonSettings;
    }
}