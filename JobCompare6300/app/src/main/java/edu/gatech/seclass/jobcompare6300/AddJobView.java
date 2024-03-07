package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddJobView extends AppCompatActivity {

    private Button offerBack;
    private Button offerSaveOffer;
    private Button offerEnterNewOffer;
    private Button offerCompareWithCurrent;

    private EditText textOfferTitleValue;
    private EditText textOfferCompanyValue;
    private EditText textOfferLocation1Value;
    private EditText textOfferLocation2Value;
    private EditText textOfferCostLivingValue;
    private EditText textOfferYearlySalaryValue;
    private EditText textOfferYearlyBonusValue;
    private EditText textOfferRetireBenefitValue;
    private EditText textOfferRelocationStipendValue;
    private EditText textOfferRSUAwardValue;

    private TextView textOfferCompareTitleCurrent;
    private TextView textOfferCompareCompanyNameCurrent;
    private TextView textOfferCompareLocation1Current;
    private TextView textOfferCompareLocation2Current;
    private TextView textOfferCompareSalaryAdjustedCurrent;
    private TextView textOfferCompareBonusAdjustedCurrent;
    private TextView textOfferCompareRetireBenefitCurrent;
    private TextView textOfferCompareRelocationStipendCurrent;
    private TextView textOfferCompareRSUAwardCurrent;

    private TextView textOfferCompareTitleOffer;
    private TextView textOfferCompareCompanyNameOffer;
    private TextView textOfferCompareLocation1Offer;
    private TextView textOfferCompareLocation2Offer;
    private TextView textOfferCompareSalaryAdjustedOffer;
    private TextView textOfferCompareBonusAdjustedOffer;
    private TextView textOfferCompareRetireBenefitOffer;
    private TextView textOfferCompareRelocationStipendOffer;
    private TextView textOfferCompareRSUAwardOffer;

    private TableLayout jobOfferTable;
    private TableLayout jobCompareTable;
    private LinearLayout newOfferButtons;
    private LinearLayout newJobLayout;
    private LinearLayout compareJobLayout;

    private MainMenuController mainMenuController = new MainMenuController();

    // Flag indicating whether the user has saved the job offer
    private int offerSaved = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_job_offer_view);
        mainMenuController = new MainMenuController(this);
        // Initialize the four buttons
        jobOfferTable = (TableLayout) findViewById(R.id.job_offer_table);
        jobCompareTable = (TableLayout) findViewById(R.id.job_compare_table);
        newOfferButtons = (LinearLayout) findViewById(R.id.new_offer_buttons);
        offerBack = (Button) findViewById(R.id.offerBackButton);
        offerSaveOffer = (Button) findViewById(R.id. offerSaveOfferButton);
        offerEnterNewOffer = (Button) findViewById(R.id.offerEnterNewOfferButton);
        offerCompareWithCurrent = (Button) findViewById(R.id.offerCompareWithCurrentButton);
//        newJobLayout = (LinearLayout) findViewById(R.id.newJobLayout);
        compareJobLayout = (LinearLayout) findViewById(R.id.compareJobLayout);

        // Initialize the job offer details
        // Location1 is State, Location2 is City
        textOfferTitleValue = (EditText) findViewById(R.id.offerTitleValue);
        textOfferCompanyValue = (EditText) findViewById(R.id.offerCompanyValue);
        textOfferLocation1Value = (EditText) findViewById(R.id.offerLocation1Value);
        textOfferLocation2Value = (EditText) findViewById(R.id.offerLocation2Value);
        textOfferCostLivingValue = (EditText) findViewById(R.id.offerCostLivingValue);
        textOfferYearlySalaryValue = (EditText) findViewById(R.id.offerYearlySalaryValue);
        textOfferYearlyBonusValue = (EditText) findViewById(R.id.offerYearlyBonusValue);
        textOfferRetireBenefitValue = (EditText) findViewById(R.id.offerRetireBenefitValue);
        textOfferRelocationStipendValue = (EditText) findViewById(R.id.offerRelocationStipendValue);
        textOfferRSUAwardValue = (EditText) findViewById(R.id.offerRSUAwardValue);

        // Link the offerBack button to the MainMenu
        offerBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                newJobLayout.setVisibility(View.VISIBLE);
//                compareJobLayout.setVisibility(View.VISIBLE);
                offerBackButtonClicked();
            }
        });

        // Save the job offer
        offerSaveOffer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                offerSaveOfferButtonClicked();
            }
        });

        // Compare the job offer(just saved) with the current job(if present)
        offerCompareWithCurrent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                jobOfferTable.setVisibility(View.GONE);
                compareJobLayout.setVisibility(View.VISIBLE);
                jobCompareTable.setVisibility(View.VISIBLE);
                offerCompareWithCurrentButtonClicked();
            }
        });

        // Enter a new offer
        offerEnterNewOffer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                jobCompareTable.setVisibility(View.GONE);
                jobOfferTable.setVisibility(View.VISIBLE);
                newOfferButtons.setVisibility(View.VISIBLE);
                offerEnterNewOfferButtonClicked();
            }
        });
    }

    public void offerBackButtonClicked()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void offerSaveOfferButtonClicked()
    {
        // Get the job offer details from the user inputs
        String textOfferTitle = textOfferTitleValue.getText().toString();
        String textOfferCompany = textOfferCompanyValue.getText().toString();
        String textOfferLocation1 = textOfferLocation1Value.getText().toString();
        String textOfferLocation2 = textOfferLocation2Value.getText().toString();
        String textOfferCostLiving = textOfferCostLivingValue.getText().toString();
        String textOfferYearlySalary = textOfferYearlySalaryValue.getText().toString();
        String textOfferYearlyBonus = textOfferYearlyBonusValue.getText().toString();
        String textOfferRetireBenefit = textOfferRetireBenefitValue.getText().toString();
        String textOfferRelocationStipend = textOfferRelocationStipendValue.getText().toString();
        String textOfferRSUAward = textOfferRSUAwardValue.getText().toString();

        // Convert String inputs to Integers
        // Check whether the user has typed all the details (Assumption: No blank EditText)
        // Also conduct necessary data format check;
        // 1-pass check; 0-empty error; 2-not an integer; 3-out of range
        int flagOfferTitle = 1;
        int flagOfferCompany = 1;
        int flagOfferLocation1 = 1;
        int flagOfferLocation2 = 1;
        int flagOfferCostLiving = 1;
        int flagOfferYearlySalary = 1;
        int flagOfferYearlyBonus = 1;
        int flagOfferRetireBenefit = 1;
        int flagOfferRelocationStipend = 1;
        int flagOfferRSUAward = 1;

        if (textOfferTitle.length() == 0) {
            flagOfferTitle = 0;
        }

        if (textOfferCompany.length() == 0) {
            flagOfferCompany = 0;
        }

        if (textOfferLocation1.length() == 0) {
            flagOfferLocation1 = 0;
        }

        if (textOfferLocation2.length() == 0) {
            flagOfferLocation2 = 0;
        }

        if (textOfferCostLiving.length() == 0) {
            flagOfferCostLiving = 0;
        }

        if (textOfferYearlySalary.length() == 0) {
            flagOfferYearlySalary = 0;
        }

        if (textOfferYearlyBonus.length() == 0) {
            flagOfferYearlyBonus = 0;
        }

        if (textOfferRetireBenefit.length() == 0) {
            flagOfferRetireBenefit = 0;
        } else if (isInteger(textOfferRetireBenefit) == false) {
            flagOfferRetireBenefit = 2;
            Context context = getApplicationContext();
            CharSequence text = "Error: Retire benefits should be an integer!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (Integer.parseInt(textOfferRetireBenefit) < 0 || Integer.parseInt(textOfferRetireBenefit) > 100) {
            flagOfferRetireBenefit = 3;
            Context context = getApplicationContext();
            CharSequence text = "Error: Retire benefits should be in [0, 100]!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        if (textOfferRelocationStipend.length() == 0) {
            flagOfferRelocationStipend = 0;
        }

        if (textOfferRSUAward.length() == 0) {
            flagOfferRSUAward = 0;
        }

        if (flagOfferTitle==1 && flagOfferCompany==1 && flagOfferLocation1==1 && flagOfferLocation2==1 && flagOfferCostLiving==1 && flagOfferYearlySalary==1 && flagOfferYearlyBonus==1 && flagOfferRetireBenefit==1 && flagOfferRelocationStipend==1 && flagOfferRSUAward==1) {
            Float offerCostLiving = null, offerYearlySalary, offerYearlyBonus, offerRelocationStipend, offerRSUAward;
            Integer offerRetireBenefit;

            Boolean rangeFlag = false;
            try{
                offerRetireBenefit = Integer.parseInt(textOfferRetireBenefit);
                if(offerRetireBenefit > 100 || offerRetireBenefit < 0) rangeFlag = true;

            }catch (NumberFormatException exception){
               rangeFlag = true;
            }
            Context context = getApplicationContext();
            CharSequence text = "Please enter value in range [0-100] for retirement benefits";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            if(rangeFlag){
                toast.show();
            }

            try {
                offerCostLiving = Float.parseFloat(textOfferCostLiving);
                offerYearlySalary = Float.parseFloat(textOfferYearlySalary);
                offerYearlyBonus = Float.parseFloat(textOfferYearlyBonus);
                offerRetireBenefit = Integer.parseInt(textOfferRetireBenefit);
                offerRelocationStipend = Float.parseFloat(textOfferRelocationStipend);
                offerRSUAward = Float.parseFloat(textOfferRSUAward);
                mainMenuController.saveJobOffer(textOfferTitle, textOfferCompany, textOfferLocation2, textOfferLocation1, offerCostLiving,  offerYearlySalary,  offerYearlyBonus,  offerRetireBenefit,  offerRelocationStipend,  offerRSUAward);
                offerSaved = 1;
            }catch (NumberFormatException exception){
                text = "Please enter correct value for salary fields";
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            if(offerSaved == 1){
                text = "Success: job offer added!";
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else if (flagOfferTitle==0 || flagOfferCompany==0 || flagOfferLocation1==0 || flagOfferLocation2==0 || flagOfferCostLiving==0 || flagOfferYearlySalary==0 && flagOfferYearlyBonus==0 || flagOfferRetireBenefit==0 || flagOfferRelocationStipend==0 || flagOfferRSUAward==0) {
            Context context = getApplicationContext();
            CharSequence text = "Error: empty value!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void offerCompareWithCurrentButtonClicked()
    {
        //Get the current job  from the database
        Job currentJob = mainMenuController.getCurrentJobDB();
        if (currentJob == null) {
            Context context = getApplicationContext();
            CharSequence text = "Error: please add the current job first!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if(offerSaved == 0) {
                Context context = getApplicationContext();
                CharSequence text = "Error: please save the job offer first!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Job latestOffer = mainMenuController.getLatestJobOfferDB();
                if (latestOffer != null) {
                    // Show the current job details
                    textOfferCompareTitleCurrent = (TextView) findViewById(R.id.offerCompareTitleCurrent);
                    textOfferCompareCompanyNameCurrent = (TextView) findViewById(R.id.offerCompareCompanyNameCurrent);
                    textOfferCompareLocation1Current = (TextView) findViewById(R.id.offerCompareLocation1Current);
                    textOfferCompareLocation2Current = (TextView) findViewById(R.id.offerCompareLocation2Current);
                    textOfferCompareSalaryAdjustedCurrent = (TextView) findViewById(R.id.offerCompareSalaryAdjustedCurrent);
                    textOfferCompareBonusAdjustedCurrent = (TextView) findViewById(R.id.offerCompareBonusAdjustedCurrent);
                    textOfferCompareRetireBenefitCurrent = (TextView) findViewById(R.id.offerCompareRetireBenefitCurrent);
                    textOfferCompareRelocationStipendCurrent = (TextView) findViewById(R.id.offerCompareRelocationStipendCurrent);
                    textOfferCompareRSUAwardCurrent = (TextView) findViewById(R.id.offerCompareRSUAwardCurrent);
                    //companyRSUAwardValue  offerRSUAwardValue1
                    textOfferCompareTitleCurrent.setText(currentJob.getTitle());
                    textOfferCompareCompanyNameCurrent.setText(currentJob.getCompany());
                    textOfferCompareLocation1Current.setText(currentJob.getLocationState());
                    textOfferCompareLocation2Current.setText(currentJob.getLocationCity());
                    float currentAYS = currentJob.getYearlySalary() / currentJob.getLivingCost() * 100;
                    float currentAYB = currentJob.getYearlyBonus() / currentJob.getLivingCost() * 100;
                    float currentRetireBenefit = currentJob.getRetirementBenefits() * currentAYS / 100;
                    textOfferCompareSalaryAdjustedCurrent.setText(String.valueOf(currentAYS));
                    textOfferCompareBonusAdjustedCurrent.setText(String.valueOf(currentAYB));
                    textOfferCompareRetireBenefitCurrent.setText(String.valueOf(currentRetireBenefit));
                    textOfferCompareRelocationStipendCurrent.setText(String.valueOf(currentJob.getRelocationStipend()));
                    textOfferCompareRSUAwardCurrent.setText(String.valueOf(currentJob.getRSUAward()));

                    // Show the job offer details
                    textOfferCompareTitleOffer = (TextView) findViewById(R.id.offerCompareTitleOffer);
                    textOfferCompareCompanyNameOffer = (TextView) findViewById(R.id.offerCompareCompanyNameOffer);
                    textOfferCompareLocation1Offer = (TextView) findViewById(R.id.offerCompareLocation1Offer);
                    textOfferCompareLocation2Offer = (TextView) findViewById(R.id.offerCompareLocation2Offer);
                    textOfferCompareSalaryAdjustedOffer = (TextView) findViewById(R.id.offerCompareSalaryAdjustedOffer);
                    textOfferCompareBonusAdjustedOffer = (TextView) findViewById(R.id.offerCompareBonusAdjustedOffer);
                    textOfferCompareRetireBenefitOffer = (TextView) findViewById(R.id.offerCompareRetireBenefitOffer);
                    textOfferCompareRelocationStipendOffer = (TextView) findViewById(R.id.offerCompareRelocationStipendOffer);
                    textOfferCompareRSUAwardOffer = (TextView) findViewById(R.id.offerCompareRSUAwardOffer);

                    textOfferCompareTitleOffer.setText(latestOffer.getTitle());
                    textOfferCompareCompanyNameOffer.setText(latestOffer.getCompany());
                    textOfferCompareLocation1Offer.setText(latestOffer.getLocationState());
                    textOfferCompareLocation2Offer.setText(latestOffer.getLocationCity());
                    float offerAYS = latestOffer.getYearlySalary() / latestOffer.getLivingCost() * 100;
                    float offerAYB = latestOffer.getYearlyBonus() / latestOffer.getLivingCost() * 100;
                    float offerRetireBenefit = latestOffer.getRetirementBenefits() * offerAYS / 100;
                    textOfferCompareSalaryAdjustedOffer.setText(String.valueOf(offerAYS));
                    textOfferCompareBonusAdjustedOffer.setText(String.valueOf(offerAYB));
                    textOfferCompareRetireBenefitOffer.setText(String.valueOf(offerRetireBenefit));
                    textOfferCompareRelocationStipendOffer.setText(String.valueOf(latestOffer.getRelocationStipend()));
                    textOfferCompareRSUAwardOffer.setText(String.valueOf(latestOffer.getRSUAward()));

                    offerSaved = 0;
                }
            }
        }

    }

    public void offerEnterNewOfferButtonClicked()
    {

        textOfferTitleValue.setText("");
        textOfferCompanyValue.setText("");
        textOfferLocation1Value.setText("");
        textOfferLocation2Value.setText("");
        textOfferCostLivingValue.setText("");
        textOfferYearlySalaryValue.setText("");
        textOfferYearlyBonusValue.setText("");
        textOfferRetireBenefitValue.setText("");
        textOfferRelocationStipendValue.setText("");
        textOfferRSUAwardValue.setText("");

        /*
        textOfferCompareTitleCurrent.setText("");
        textOfferCompareCompanyNameCurrent.setText("");
        textOfferCompareLocation1Current.setText("");
        textOfferCompareLocation2Current.setText("");
        textOfferCompareSalaryAdjustedCurrent.setText("");
        textOfferCompareBonusAdjustedCurrent.setText("");
        textOfferCompareRetireBenefitCurrent.setText("");
        textOfferCompareRelocationStipendCurrent.setText("");
        textOfferCompareRSUAwardCurrent.setText("");

        textOfferCompareTitleOffer.setText("");
        textOfferCompareCompanyNameOffer.setText("");
        textOfferCompareLocation1Offer.setText("");
        textOfferCompareLocation2Offer.setText("");
        textOfferCompareSalaryAdjustedOffer.setText("");
        textOfferCompareBonusAdjustedOffer.setText("");
        textOfferCompareRetireBenefitOffer.setText("");
        textOfferCompareRelocationStipendOffer.setText("");
        textOfferCompareRSUAwardOffer.setText("");

         */

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
