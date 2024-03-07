package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareJobsView extends AppCompatActivity {
    private Button compareExit;
    private Button compareNewComparison;

    private ArrayList<Job> rankedJobList = new ArrayList<Job>();
    private ListView jobListView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> listItems =new ArrayList<Map<String, Object>>();

    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> companies = new ArrayList<>();
    private ArrayList<Integer> IDs = new ArrayList<>();
    private ArrayList<Boolean> isCurrentJobs = new ArrayList<>();

    public static List<Map<String, Object>> UserSelection = new ArrayList<Map<String, Object>>();

    private TextView compareTableTitleOffer1;
    private TextView compareCompanyNameOffer1;
    private TextView compareLocation1Offer1;
    private TextView compareLocation2Offer1;
    private TextView compareSalaryAdjustedOffer1;
    private TextView compareBonusAdjustedOffer1;
    private TextView compareRetireBenefitOffer1;
    private TextView compareRelocationStipendOffer1;
    private TextView compareRSUAwardOffer1;

    private TextView compareTableTitleOffer2;
    private TextView compareCompanyNameOffer2;
    private TextView compareLocation1Offer2;
    private TextView compareLocation2Offer2;
    private TextView compareSalaryAdjustedOffer2;
    private TextView compareBonusAdjustedOffer2;
    private TextView compareRetireBenefitOffer2;
    private TextView compareRelocationStipendOffer2;
    private TextView compareRSUAwardOffer2;
    private MainMenuController mainMenuController;
    private LinearLayout comparisonLayout;
    private TableLayout compareTable;
    private LinearLayout compareJobOfferTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs_view);
        mainMenuController = new MainMenuController(this);

        //Test
//        Job job1 = new Job(1,"Data Scientist", "Bain", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false,0);
//        Job job2 = new Job(2,"Junior Software Developer", "Linkedin", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false, 0);
//        Job job3 = new Job(3, "MLE", "Google", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false, 0);
//        Job job4 = new Job(4, "Senior Software Developer", "Coinbase", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false, 0);
//        rankedJobList.add(job1);
//        rankedJobList.add(job2);
//        rankedJobList.add(job3);
//        rankedJobList.add(job4);
//        titles.add(rankedJobList.get(0).getTitle());
//        titles.add(rankedJobList.get(1).getTitle());
//        titles.add(rankedJobList.get(2).getTitle());
//        titles.add(rankedJobList.get(3).getTitle());
//        companies.add(rankedJobList.get(0).getCompany());
//        companies.add(rankedJobList.get(1).getCompany());
//        companies.add(rankedJobList.get(2).getCompany());
//        companies.add(rankedJobList.get(3).getCompany());
//        IDs.add(rankedJobList.get(0).getJobID());
//        IDs.add(rankedJobList.get(1).getJobID());
//        IDs.add(rankedJobList.get(2).getJobID());
//        IDs.add(rankedJobList.get(3).getJobID());

        //titles.add("Data Scientist");
        //titles.add("Junior Software Developer");
        //titles.add("MLE");
        //titles.add("Senior Software Developer");
        //companies.add("Bain");
        //companies.add("Linkedin");
        //companies.add("Google");
        //companies.add("Coinbase");
        //IDs.add(1);
        //IDs.add(2);
        //IDs.add(3);
        //IDs.add(4);


        rankedJobList = mainMenuController.getRankedJobs();

        for (int i=0; i<rankedJobList.size() ;i++) {
            titles.add(rankedJobList.get(i).getTitle());
            companies.add(rankedJobList.get(i).getCompany());
            IDs.add(rankedJobList.get(i).getJobID());
            isCurrentJobs.add(rankedJobList.get(i).isCurrentJob());
        }

        // Show the ranked jobs
        // Haven't highlighted the current job
        for(int i=0; i<titles.size(); i++){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("jobTile", titles.get(i));
            item.put("jobCompany", companies.get(i));
            item.put("jobID", IDs.get(i));
            item.put("isCurrentJob", isCurrentJobs.get(i));
            listItems.add(item);
        }
        adapter = new ListViewAdapter(this, listItems, new String[]{"jobTile","jobCompany", "jobID", "isCurrentJob"},new int[]{R.id.jobTitle, R.id.jobCompany, R.id.jobID, R.id.isCurrentJob});
        jobListView = (ListView) findViewById(R.id.jobList);
        jobListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // Initialize the two buttons
        compareExit = (Button) findViewById(R.id.compareExitButton);
        compareNewComparison = (Button) findViewById(R.id.compareNewComparisonButton);

        // Link the jobExit button to the MainMenu
        compareExit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                exitCompareClicked();
            }
        });

        // Compare two jobs
        compareNewComparison.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                newComparisonClicked();
            }
        });
    }

    public void exitCompareClicked()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        UserSelection = new ArrayList<Map<String, Object>>();
    }

    public void newComparisonClicked()
    {
        compareTable = (TableLayout) findViewById(R.id.compareTable);
        comparisonLayout = (LinearLayout) findViewById(R.id.compareLayout);
        if (compareTable.getVisibility() == View.VISIBLE) {
            Intent intent = new Intent(this, CompareJobsView.class);
            startActivity(intent);
        } else {
            if (UserSelection.size() != 2) {
                Context context = getApplicationContext();
                CharSequence text = "Error: should choose two jobs!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //UserSelection = new ArrayList<Map<String, Object>>();
            } else {
                int jobCompareID1 = (int) UserSelection.get(0).get("jobID");
                int jobCompareID2 = (int) UserSelection.get(1).get("jobID");

                ArrayList<Job> twoJobsCompare = mainMenuController.compareTwoJobs(jobCompareID1, jobCompareID2);
                Job jobCompare1 = twoJobsCompare.get(0);
                Job jobCompare2 = twoJobsCompare.get(1);


                //Test
                //Job jobCompare1 = new Job(1,"Data Scientist", "Bain", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false,0);
                //Job jobCompare2 = new Job(2,"Junior Software Developer", "Linkedin", "NYC", "NYC", 270, 100000, 10000, 5, 10000, 30000, false, 0);

                // Show the offer1 details
                compareTableTitleOffer1 = (TextView) findViewById(R.id.compareTableTitleOffer1);
                compareCompanyNameOffer1 = (TextView) findViewById(R.id.compareCompanyNameOffer1);
                compareLocation1Offer1 = (TextView) findViewById(R.id.compareLocation1Offer1);
                compareLocation2Offer1 = (TextView) findViewById(R.id.compareLocation2Offer1);
                compareSalaryAdjustedOffer1 = (TextView) findViewById(R.id.compareSalaryAdjustedOffer1);
                compareBonusAdjustedOffer1 = (TextView) findViewById(R.id.compareBonusAdjustedOffer1);
                compareRetireBenefitOffer1 = (TextView) findViewById(R.id.compareRetireBenefitOffer1);
                compareRelocationStipendOffer1 = (TextView) findViewById(R.id.compareRelocationStipendOffer1);
                compareRSUAwardOffer1 = (TextView) findViewById(R.id.compareRSUAwardOffer1);

                compareTableTitleOffer1.setText(jobCompare1.getTitle());
                compareCompanyNameOffer1.setText(jobCompare1.getCompany());
                compareLocation1Offer1.setText(jobCompare1.getLocationState());
                compareLocation2Offer1.setText(jobCompare1.getLocationCity());
                float offer1AYS = jobCompare1.getYearlySalary() / jobCompare1.getLivingCost() * 100;
                float offer1AYB = jobCompare1.getYearlyBonus() / jobCompare1.getLivingCost() * 100;
                float offer1RetireBenefit = jobCompare1.getRetirementBenefits() * offer1AYS / 100;
                compareSalaryAdjustedOffer1.setText(String.valueOf(offer1AYS));
                compareBonusAdjustedOffer1.setText(String.valueOf(offer1AYB));
                compareRetireBenefitOffer1.setText(String.valueOf(offer1RetireBenefit));
                compareRelocationStipendOffer1.setText(String.valueOf(jobCompare1.getRelocationStipend()));
                compareRSUAwardOffer1.setText(String.valueOf(jobCompare1.getRSUAward()));

                // Show the job offer 2details
                compareTableTitleOffer2 = (TextView) findViewById(R.id.compareTableTitleOffer2);
                compareCompanyNameOffer2 = (TextView) findViewById(R.id.compareCompanyNameOffer2);
                compareLocation1Offer2 = (TextView) findViewById(R.id.compareLocation1Offer2);
                compareLocation2Offer2 = (TextView) findViewById(R.id.compareLocation2Offer2);
                compareSalaryAdjustedOffer2 = (TextView) findViewById(R.id.compareSalaryAdjustedOffer2);
                compareBonusAdjustedOffer2 = (TextView) findViewById(R.id.compareBonusAdjustedOffer2);
                compareRetireBenefitOffer2 = (TextView) findViewById(R.id.compareRetireBenefitOffer2);
                compareRelocationStipendOffer2 = (TextView) findViewById(R.id.compareRelocationStipendOffer2);
                compareRSUAwardOffer2 = (TextView) findViewById(R.id.compareRSUAwardOffer2);

                compareTableTitleOffer2.setText(jobCompare2.getTitle());
                compareCompanyNameOffer2.setText(jobCompare2.getCompany());
                compareLocation1Offer2.setText(jobCompare2.getLocationState());
                compareLocation2Offer2.setText(jobCompare2.getLocationCity());
                float offer2AYS = jobCompare2.getYearlySalary() / jobCompare2.getLivingCost() * 100;
                float offer2AYB = jobCompare2.getYearlyBonus() / jobCompare2.getLivingCost() * 100;
                float offer2RetireBenefit = jobCompare2.getRetirementBenefits() * offer2AYS / 100;
                compareSalaryAdjustedOffer2.setText(String.valueOf(offer2AYS));
                compareBonusAdjustedOffer2.setText(String.valueOf(offer2AYB));
                compareRetireBenefitOffer2.setText(String.valueOf(offer2RetireBenefit));
                compareRelocationStipendOffer2.setText(String.valueOf(jobCompare2.getRelocationStipend()));
                compareRSUAwardOffer2.setText(String.valueOf(jobCompare2.getRSUAward()));
                compareJobOfferTitle = (LinearLayout) findViewById(R.id.compareJobOfferTitle);
                comparisonLayout.setVisibility(View.VISIBLE);
                jobListView.setVisibility(View.GONE);
                compareTable.setVisibility(View.VISIBLE);
                compareJobOfferTitle.setVisibility(View.GONE);
                UserSelection = new ArrayList<Map<String, Object>>();
            }
        }
    }


}