package com.autstudent.autschedular;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String day = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(AddActivity.this);
                pd.setMessage("Saving.....");
                pd.setCancelable(false);
                pd.show();
                EditText editText = (EditText) findViewById(R.id.edit_details);
                String details = editText.getText().toString();
                ParseObject object = new ParseObject("Test");
                object.put("Day",day);
                object.put("Activity", details);
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        pd.dismiss();
                        NavUtils.navigateUpFromSameTask(AddActivity.this);
                    }
                });
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.day_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.day = position+1+"";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }
}
