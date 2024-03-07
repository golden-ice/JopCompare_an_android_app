package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button addCurrentJob;
    private Button addJobOffer;
    private Button adjustSettings;
    private Button compareJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the four buttons
        addCurrentJob = (Button) findViewById(R.id.mainaddCurrentJobButton);
        addJobOffer = (Button) findViewById(R.id.mainAddJobOfferButton);
        adjustSettings = (Button) findViewById(R.id.maincomparisonSettings);
        compareJobs = (Button) findViewById(R.id.mainCompareJobsButton);

        // Link the four buttons to actions in code
        addCurrentJob.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onClickAddCurrentJobButton();
            }
        });

        addJobOffer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onClickAddJobOfferButton();
            }
        });

        adjustSettings.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onClickComparisonSettingsButton();
            }
        });

        compareJobs.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onClickCompareJobsButton();
            }
        });
    }

    public void onClickAddCurrentJobButton()
    {
        Intent intent = new Intent(this, EnterCurrentJobView.class);
        startActivity(intent);
    }

    public void onClickAddJobOfferButton()
    {
        Intent intent = new Intent(this, AddJobView.class);
        startActivity(intent);
    }

    public void onClickComparisonSettingsButton()
    {
        Intent intent = new Intent(this, ComparisonSettingsView.class);
        startActivity(intent);
    }

    public void onClickCompareJobsButton()
    {
        Intent intent = new Intent(this, CompareJobsView.class);
        startActivity(intent);
    }

}