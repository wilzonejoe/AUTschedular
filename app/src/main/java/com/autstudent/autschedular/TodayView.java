package com.autstudent.autschedular;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wilzo on 27/12/2015.
 */
public class TodayView extends Fragment {

    private ProgressDialog pd;
    final private String DAY = "Day";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_view, container, false);
        final ListView ls = (ListView)view.findViewById(R.id.today_list);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Add the schedule needed", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getActivity(),AddActivity.class);
                startActivity(intent);
            }
        });

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Test");
        query.whereEqualTo(DAY, day + "");

        if(query.hasCachedResult())
        {
            query.fromLocalDatastore();
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> list, com.parse.ParseException e) {
                if (e != null) {
                    Log.d("testing_inital_query", "could not fetch event data");
                    return;
                }
                CustomAdapter ca = new CustomAdapter(getContext(), R.layout.custom_row_layout, list);
                ls.setAdapter(ca);
                ls.setDivider(null);
                pd.dismiss();


                //releases objects previously pinned
                ParseObject.unpinAllInBackground(DAY, list, new DeleteCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.d("testing_local_caching", "could not remove local parse objects :(");
                            //error occured exits
                            return;
                        }
                        //adding latest results of the query to cache
                        ParseObject.pinAllInBackground(DAY, list);
                    }
                });
            }
        });
        return view;
    }
}
