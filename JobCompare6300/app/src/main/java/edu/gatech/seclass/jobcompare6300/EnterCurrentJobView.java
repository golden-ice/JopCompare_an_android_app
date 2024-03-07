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

public class EnterCurrentJobView extends AppCompatActivity {
    private Button jobExit;
    private Button jobSave;

    private EditText textJobTitleValue;
    private EditText textJobCompanyValue;
    private EditText textJobLocation1Value;
    private EditText textJobLocation2Value;
    private EditText textJobCostLivingValue;
    private EditText textJobYearlySalaryValue;
    private EditText textJobYearlyBonusValue;
    private EditText textJobRetireBenefitValue;
    private EditText textJobRelocationStipendValue;
    private EditText textJobRSUAwardValue;

    private MainMenuController mainMenuController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_current_job_view);
        mainMenuController = new MainMenuController(this);
        // Initialize the two buttons
        jobExit = (Button) findViewById(R.id.jobExitButton);
        jobSave = (Button) findViewById(R.id.jobSaveButton);

        // Initialize the current job details
        // Location1 is State, Location2 is City
        textJobTitleValue = (EditText) findViewById(R.id.jobTitleValue);
        textJobCompanyValue = (EditText) findViewById(R.id.jobCompanyValue);
        textJobLocation1Value = (EditText) findViewById(R.id.jobLocation1Value);
        textJobLocation2Value = (EditText) findViewById(R.id.jobLocation2Value);
        textJobCostLivingValue = (EditText) findViewById(R.id.jobCostLivingValue);
        textJobYearlySalaryValue = (EditText) findViewById(R.id.jobYearlySalaryValue);
        textJobYearlyBonusValue = (EditText) findViewById(R.id.jobYearlyBonusValue);
        textJobRetireBenefitValue = (EditText) findViewById(R.id.jobRetireBenefitValue);
        textJobRelocationStipendValue = (EditText) findViewById(R.id.jobRelocationStipendValue);
        textJobRSUAwardValue = (EditText) findViewById(R.id.jobRSUAwardValue);

        //Get and show the details of the current job from the database
//        //To Modify
        Job currentJob = mainMenuController.getCurrentJob();
        // Test
//        Job currentJob = new Job(0,"Software Developer", "Apple", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false, 0);
        if (currentJob != null) {
            textJobTitleValue.setText(currentJob.getTitle());
            textJobCompanyValue.setText(currentJob.getCompany());
            textJobLocation1Value.setText(currentJob.getLocationState());
            textJobLocation2Value.setText(currentJob.getLocationCity());
            textJobCostLivingValue.setText(String.format(Locale.getDefault(), "%f", currentJob.getLivingCost()));
            textJobYearlySalaryValue.setText(String.format(Locale.getDefault(), "%f", currentJob.getYearlySalary()));
            textJobYearlyBonusValue.setText(String.format(Locale.getDefault(), "%f", currentJob.getYearlyBonus()));
            textJobRetireBenefitValue.setText(String.format(Locale.getDefault(), "%d", currentJob.getRetirementBenefits()));
            textJobRelocationStipendValue.setText(String.format(Locale.getDefault(), "%f", currentJob.getRelocationStipend()));
            textJobRSUAwardValue.setText(String.format(Locale.getDefault(), "%f", currentJob.getRSUAward()));
        }

        // Link the jobExit button to the MainMenu
        jobExit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                exitCurrentJobButtonClicked();
            }
        });

        // Save the job details
        jobSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                saveCurrentJobButtonClicked();
            }
        });

    }

    public void exitCurrentJobButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveCurrentJobButtonClicked() {
        // Get the job details from the user inputs
        String textJobTitle = textJobTitleValue.getText().toString();
        String textJobCompany = textJobCompanyValue.getText().toString();
        String textJobLocation1 = textJobLocation1Value.getText().toString();
        String textJobLocation2 = textJobLocation2Value.getText().toString();
        String textJobCostLiving = textJobCostLivingValue.getText().toString();
        String textJobYearlySalary = textJobYearlySalaryValue.getText().toString();
        String textJobYearlyBonus = textJobYearlyBonusValue.getText().toString();
        String textJobRetireBenefit = textJobRetireBenefitValue.getText().toString();
        String textJobRelocationStipend = textJobRelocationStipendValue.getText().toString();
        String textJobRSUAward = textJobRSUAwardValue.getText().toString();

        // Convert String inputs to Integers
        // Check whether the user has typed all the details (Assumption: No blank EditText)
        // Also conduct necessary data format check;
        // 1-pass check; 0-empty error; 2-not an integer; 3-out of range
        int flagJobTitle = 1;
        int flagJobCompany = 1;
        int flagJobLocation1 = 1;
        int flagJobLocation2 = 1;
        int flagJobCostLiving = 1;
        int flagJobYearlySalary = 1;
        int flagJobYearlyBonus = 1;
        int flagJobRetireBenefit = 1;
        int flagJobRelocationStipend = 1;
        int flagJobRSUAward = 1;

        if (textJobTitle.length() == 0) {
            flagJobTitle = 0;
        }

        if (textJobCompany.length() == 0) {
            flagJobCompany = 0;
        }

        if (textJobLocation1.length() == 0) {
            flagJobLocation1 = 0;
        }

        if (textJobLocation2.length() == 0) {
            flagJobLocation2 = 0;
        }

        if (textJobCostLiving.length() == 0) {
            flagJobCostLiving = 0;
        }

        if (textJobYearlySalary.length() == 0) {
            flagJobYearlySalary = 0;
        }

        if (textJobYearlyBonus.length() == 0) {
            flagJobYearlyBonus = 0;
        }

        if (textJobRetireBenefit.length() == 0) {
            flagJobRetireBenefit = 0;
        } else if (isInteger(textJobRetireBenefit) == false) {
            flagJobRetireBenefit = 2;
            Context context = getApplicationContext();
            CharSequence text = "Error: Retire benefits should be an integer!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (Integer.parseInt(textJobRetireBenefit) < 0 || Integer.parseInt(textJobRetireBenefit) > 100) {
            flagJobRetireBenefit = 3;
            Context context = getApplicationContext();
            CharSequence text = "Error: Retire benefits should be in [0, 100]!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        if (textJobRelocationStipend.length() == 0) {
            flagJobRelocationStipend = 0;
        }

        if (textJobRSUAward.length() == 0) {
            flagJobRSUAward = 0;
        }

        if (flagJobTitle==1 && flagJobCompany==1 && flagJobLocation1==1 && flagJobLocation2==1 && flagJobCostLiving==1 && flagJobYearlySalary==1 && flagJobYearlyBonus==1 && flagJobRetireBenefit==1 && flagJobRelocationStipend==1 && flagJobRSUAward==1) {
            Float jobCostLiving ;
            Float jobYearlySalary ;
            Float jobYearlyBonus ;
            Integer jobRetireBenefit ;
            Float jobRelocationStipend ;
            Float jobRSUAward ;
            boolean jobSaved = false;
            int duration = Toast.LENGTH_LONG;
            CharSequence text = "Success: current job updated!";
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, text, duration);
            try {
                jobCostLiving = Float.parseFloat(textJobCostLiving);
                jobYearlySalary = Float.parseFloat(textJobYearlySalary);
                jobYearlyBonus = Float.parseFloat(textJobYearlyBonus);
                jobRetireBenefit = Integer.parseInt(textJobRetireBenefit);
                jobRelocationStipend = Float.parseFloat(textJobRelocationStipend);
                jobRSUAward = Float.parseFloat(textJobRSUAward);
                mainMenuController.saveCurrentJob(textJobTitle, textJobCompany, textJobLocation2, textJobLocation1, jobCostLiving, jobYearlySalary, jobYearlyBonus, jobRetireBenefit, jobRelocationStipend, jobRSUAward);
                jobSaved = true;
            }catch (NumberFormatException exception){
                text = "Please enter correct value for salary fields";
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            if(jobSaved){
                text = "Success: current job updated!";
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else if (flagJobTitle==0 || flagJobCompany==0 || flagJobLocation1==0 || flagJobLocation2==0 || flagJobCostLiving==0 || flagJobYearlySalary==0 && flagJobYearlyBonus==0 || flagJobRetireBenefit==0 || flagJobRelocationStipend==0 || flagJobRSUAward==0) {
            Context context = getApplicationContext();
            CharSequence text = "Error: empty value!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private boolean isInteger(String string) {
        Integer intValue;
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}