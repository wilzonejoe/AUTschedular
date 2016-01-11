package com.autstudent.autschedular;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.autstudent.autschedular.Helper.DatabaseTitle;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private ParseObject ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int idRef = getIntent().getIntExtra("id_ref",-1);
        try {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(DatabaseTitle.TABLESCHEDULENAME);
            query.whereEqualTo("idRef", idRef);
            ob = query.find().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ob != null) {
            TextView titleTv = (TextView) findViewById(R.id.detail_title);
            titleTv.setText(ob.get("Title").toString());
        }
    }
}
