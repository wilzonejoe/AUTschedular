package com.autstudent.autschedular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wilzo on 3/01/2016.
 */
public class Schedule extends AddActivity.PlaceholderFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_schedule, container, false);
        return rootView;
    }

}