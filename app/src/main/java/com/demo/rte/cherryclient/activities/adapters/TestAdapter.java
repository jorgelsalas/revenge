package com.demo.rte.cherryclient.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.rte.cherryclient.R;
import com.demo.rte.cherryclient.activities.entities.PackageTest;

import java.util.ArrayList;

/**
 * Created by Jorge on 06/08/2015.
 */
public class TestAdapter extends BaseAdapter {

    private ArrayList<PackageTest> tests = new ArrayList<>();
    private Activity activity;

    public TestAdapter(){

    }

    public TestAdapter(ArrayList<PackageTest> tests, Activity activity){
        this.tests = tests;
        this.activity = activity;
    }

    public void setNewData(ArrayList<PackageTest> newTests){
        tests = newTests;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tests.size();
    }

    @Override
    public Object getItem(int position) {
        return tests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout test = (LinearLayout) convertView;
        if (test == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            test = (LinearLayout) inflater.inflate(R.layout.test_item, parent, false);
        }
        TextView nameTv = (TextView) test.findViewById(R.id.name);
        nameTv.setText(tests.get(position).getName());
        TextView acronTv = (TextView) test.findViewById(R.id.acronym);
        acronTv.setText(tests.get(position).getAcronym());
        TextView statusTv = (TextView) test.findViewById(R.id.status);
        statusTv.setText(tests.get(position).getStatus());
        if (tests.get(position).getStatus().equals(PackageTest.TEST_STATUS_FAILED)){
            statusTv.setTextColor(Color.RED);
        }
        else if (tests.get(position).getStatus().equals(PackageTest.TEST_STATUS_SUCCESS)){
            statusTv.setTextColor(Color.rgb(0,100,0));
        }
        else if (tests.get(position).getStatus().equals(PackageTest.TEST_STATUS_IN_PROGRESS)){
            statusTv.setTextColor(Color.BLACK);
        }
        else if (tests.get(position).getStatus().equals(PackageTest.TEST_STATUS_NOT_STARTED)){
            statusTv.setTextColor(Color.BLACK);
        }
        else {
            statusTv.setTextColor(Color.BLACK);
        }
        return test;
    }
}
