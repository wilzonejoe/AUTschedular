package com.autstudent.autschedular;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wilzo on 26/12/2015.
 */
public class CustomAdapter extends ArrayAdapter {
    private Activity activity;
    private List<String> title;


    public CustomAdapter(Activity context, int resource, List<String> Title) {
        super(context, resource, Title);
        this.activity = context;
        this.title = Title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder view;
        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.custom_row_layout, null);
            // Hold the view objects in an object, that way the don't need to be "re- finded"
            view = new ViewHolder();
            view.title = (TextView) rowView.findViewById(R.id.row_title);
            view.details = (TextView) rowView.findViewById(R.id.row_details);
            rowView.setTag(view);
        } else {
            view = (ViewHolder) rowView.getTag();
        }
        /** Set data to your Views. */

        view.title.setText(title.get(position));
        view.details.setText(title.get(position));

        return rowView;
    }


    protected static class ViewHolder {
        protected TextView title;
        protected TextView details;
    }
}
