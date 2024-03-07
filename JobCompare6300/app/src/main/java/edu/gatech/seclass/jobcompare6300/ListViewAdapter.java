package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

/*
 * Reference:
 * https://www.youtube.com/watch?v=VQEmj7Zos6Y&list=PLd2g6twJN1Gi8eSW-N7lSqbHwp-f0dkGb&index=96
 * Author: Sandip Bhattacharya
 * Modified based on the concept from the youtube tutorial
 * */
public class ListViewAdapter extends SimpleAdapter {
    private Context context;
    private List<Map<String, Object>> listItems;
    private int resource;
    private String[] from;
    private int[] to;

    public ListViewAdapter(Context context, List<Map<String, Object>> listItems, String[] from, int[] to) {
        super(context, listItems, R.layout.single_job, from, to);
        this.context = context;
        this.listItems = listItems;
        this.from = from;
        this.to = to;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.single_job, parent, false);
        if ((Boolean) listItems.get(position).get(from[3]) == true) {
            row.setBackgroundColor(Color.LTGRAY);
        }
        TextView jobTile  = (TextView) row.findViewById(R.id.jobTitle);
        TextView jobCompany  = (TextView) row.findViewById(R.id.jobCompany);

        jobTile.setText((String) listItems.get(position).get(from[0]));
        jobCompany.setText((String) listItems.get(position).get(from[1]));

        CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkBox);
        checkBox.setTag(position);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int position = (int) compoundButton.getTag();
                if (CompareJobsView.UserSelection.contains(listItems.get(position))) {
                    CompareJobsView.UserSelection.remove(listItems.get(position));
                } else {
                    CompareJobsView.UserSelection.add(listItems.get(position));
                }
            }
        });
        return row;
    }



















}
