package edu.gatech.seclass.jobcompare6300;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

public class ComparisonSettingTest {

    private ComparisonSettings comparisonSettings;

    @Test
    public void generateDefaultComparisonSettings() {
        comparisonSettings = new ComparisonSettings();
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightRelocationStipend()), Optional.ofNullable(1));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightRetirementBenefits()), Optional.ofNullable(1));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightRSUAward()), Optional.ofNullable(1));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightYearlyBonus()), Optional.ofNullable(1));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightYearlySalary()), Optional.ofNullable(1));
    }

    @Test
    public void generateComparisonSettings() {
        comparisonSettings = new ComparisonSettings(2,3,4,5,8);
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightYearlySalary()), Optional.ofNullable(2));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightYearlyBonus()), Optional.ofNullable(3));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightRetirementBenefits()), Optional.ofNullable(4));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightRelocationStipend()), Optional.ofNullable(5));
        assertEquals(java.util.Optional.ofNullable(comparisonSettings.getWeightRSUAward()), Optional.ofNullable(8));
    }

}
