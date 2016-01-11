package com.autstudent.autschedular;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;

public class PaperDescription extends Fragment implements AdapterView.OnItemSelectedListener {

    private List<ParseObject> papers;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_paper_description,container,false);
        this.rootView = rootView;
        papers = new ArrayList<>();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("paper");

        try {
            ProgressDialog pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading papers");
            pd.setCancelable(false);
            pd.show();
            papers = query.find();
            ArrayList<String> ar = new ArrayList();
            for (ParseObject o : papers) {
                ar.add(o.get("paper_title").toString());
            }
            Spinner spinner = (Spinner) rootView.findViewById(R.id.paper_spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ar);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            pd.dismiss();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rootView;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        List<ParseObject> streams = new ArrayList<>();
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading papers");
        pd.setCancelable(false);
        pd.show();
        try {
            ParseObject itemSelected = papers.get(position);
            ParseRelation<ParseObject> relation = itemSelected.getRelation("class");
            ParseQuery<ParseObject> query = relation.getQuery();
            query.orderByAscending("stream_no");
            streams = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ListView ls = (ListView) rootView.findViewById(R.id.paper_stream_selector);
        StreamArrayAdapter streamArrayAdapter = new StreamArrayAdapter(getActivity(), R.layout.custom_paper_row_layout, streams);
        ls.setAdapter(streamArrayAdapter);

        pd.dismiss();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //nothing
    }
}
