package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

public class ComparisonSettingsView extends AppCompatActivity {

    private Button settingExit;
    private Button settingSave;
    private EditText textSettingYearlySalaryValue;
    private EditText textSettingYearlyBonusValue;
    private EditText textSettingRetireBenefitValue;
    private EditText textSettingRelocationStipendValue;
    private EditText textSettingRSUAwardValue;

    private MainMenuController mainMenuController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings_view);
        mainMenuController = new MainMenuController(this);
        // Initialize the two buttons
        settingExit = (Button) findViewById(R.id.settingExitButton);
        settingSave = (Button) findViewById(R.id.settingSaveButton);

        // Initialize the five weights
        textSettingYearlySalaryValue = (EditText) findViewById(R.id.settingYearlySalaryValue);
        textSettingYearlyBonusValue = (EditText) findViewById(R.id.settingYearlyBonusValue);
        textSettingRetireBenefitValue = (EditText) findViewById(R.id.settingRetireBenefitValue);
        textSettingRelocationStipendValue = (EditText) findViewById(R.id.settingRelocationStipendValue);
        textSettingRSUAwardValue= (EditText) findViewById(R.id.settingRSUAwardValue);

        // Get and show the current weights from the database

        ComparisonSettings comparisonSettings = this.mainMenuController.getComparisonSettings();

        if (comparisonSettings != null) {
            textSettingYearlySalaryValue.setText(String.format(Locale.getDefault(), "%d", comparisonSettings.getWeightYearlySalary()));
            textSettingYearlyBonusValue.setText(String.format(Locale.getDefault(), "%d", comparisonSettings.getWeightYearlyBonus()));
            textSettingRetireBenefitValue.setText(String.format(Locale.getDefault(), "%d", comparisonSettings.getWeightRetirementBenefits()));
            textSettingRelocationStipendValue.setText(String.format(Locale.getDefault(), "%d", comparisonSettings.getWeightRelocationStipend()));
            textSettingRSUAwardValue.setText(String.format(Locale.getDefault(), "%d", comparisonSettings.getWeightRSUAward()));
        }

        // Link the settingExit button to the MainMenu
        settingExit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                exitSettingClicked();
            }
        });

        // Update the settings
        settingSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                updateSettingsButtonClicked();
            }
        });

    }

    public void exitSettingClicked()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void updateSettingsButtonClicked()
    {
        // Get the settings from the user inputs
        String textYearlySalary = textSettingYearlySalaryValue.getText().toString();
        String textYearlyBonus = textSettingYearlyBonusValue.getText().toString();
        String textRetireBenefit = textSettingRetireBenefitValue.getText().toString();
        String textRelocationStipend = textSettingRelocationStipendValue.getText().toString();
        String textRSUAward = textSettingRSUAwardValue.getText().toString();


        // Convert String inputs to Integers
        // Check whether the user has typed all the settings (Assumption: No blank EditText)
        int flagYearlySalary = 1;
        int flagYearlyBonus = 1;
        int flagRetireBenefit = 1;
        int flagRelocationStipend = 1;
        int flagRSUAward = 1;

        if (textYearlySalary.length() == 0) {
            flagYearlySalary = 0;
        }

        if (textYearlyBonus.length() == 0) {
            flagYearlyBonus = 0;
        }

        if (textRetireBenefit.length() == 0) {
            flagRetireBenefit = 0;
        }

        if (textRelocationStipend.length() == 0) {
            flagRelocationStipend = 0;
        }

        if (textRSUAward.length() == 0) {
            flagRSUAward = 0;
        }

        if (flagYearlySalary==1 && flagYearlyBonus==1 && flagRetireBenefit==1 && flagRelocationStipend==1 && flagRSUAward==1) {
            Integer yearlySalaryValue = Integer.parseInt(textYearlySalary);
            Integer yearlyBonusValue = Integer.parseInt(textYearlyBonus);
            Integer retireBenefitValue = Integer.parseInt(textRetireBenefit);
            Integer relocationStipendValue = Integer.parseInt(textRelocationStipend);
            Integer RSUAwardValue = Integer.parseInt(textRSUAward);
            Context context = getApplicationContext();
            CharSequence text = "Success: settings updated!";
            int duration = Toast.LENGTH_LONG;
            if(yearlySalaryValue <= 0 || yearlyBonusValue <= 0 || yearlyBonusValue <= 0 || relocationStipendValue <= 0 ||  RSUAwardValue <= 0){
                text = "Please enter a value more than zero";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                mainMenuController.updateComparisonSettings(yearlySalaryValue, yearlyBonusValue, retireBenefitValue, relocationStipendValue, RSUAwardValue);
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Error: empty value!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }
}
