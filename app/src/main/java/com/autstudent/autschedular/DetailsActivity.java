package com.autstudent.autschedular;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {

    private static final int VIEW = 1;
    private static final int EDIT = 2;

    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editMode) {
                    editMode = true;
                    changeLayout(DetailsActivity.EDIT);
                }
                else {
                    editMode = false;
                    changeLayout(DetailsActivity.VIEW);
                }
            }
        });
        changeLayout(DetailsActivity.VIEW);
    }

    private void changeLayout(int status) {
        Fragment fragment = new DetailViewFrag();
        switch (status) {
            case DetailsActivity.VIEW:
                fragment = new DetailViewFrag();
                break;
            case DetailsActivity.EDIT:
                fragment = new DetailEditFrag();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.detail_fragment, fragment).commit();
    }

}
