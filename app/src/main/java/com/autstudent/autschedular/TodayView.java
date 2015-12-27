package com.autstudent.autschedular;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilzo on 27/12/2015.
 */
public class TodayView extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_view, container, false);
        final ListView ls = (ListView)view.findViewById(R.id.today_list);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add the schedule needed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Test");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                List<String> names = new ArrayList<String>();
                for (ParseObject o : list) {
                    names.add(o.get("Day").toString());
                }
                CustomAdapter ca = new CustomAdapter(getContext(),R.layout.custom_row_layout,names);
                ls.setAdapter(ca);
            }
        });
        return view;
    }
}
