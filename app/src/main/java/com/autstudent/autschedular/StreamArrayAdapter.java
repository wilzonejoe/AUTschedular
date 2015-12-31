package com.autstudent.autschedular;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.Calendar;
import java.util.List;



/**
 * Created by wilzo on 26/12/2015.
 */
public class StreamArrayAdapter extends ArrayAdapter {
    private Context activity;
    private List<ParseObject> objects;


    public StreamArrayAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.objects= objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder view;
        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


        view.title.setText(objects.get(position).get("stream_code").toString());
        final TextView detail = view.details;
        ParseRelation<ParseObject> parseRelation = objects.get(position).getRelation("stream");
        ParseQuery <ParseObject>parseQuery = parseRelation.getQuery();
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null) {
                    String details = "";
                    boolean first = true;
                    for (ParseObject o : list) {
                        if(!first){
                            details+="\n";
                        }
                        else{
                            first = false;
                        }
                        details += getDate(Integer.parseInt(o.get("day").toString())) + " " + o.get("start").toString() + " - " + o.get("end").toString()+"\n \t" + o.get("room").toString();
                    }
                    detail.setText(details);
                }
            }
        });

        return rowView;
    }


    protected static class ViewHolder {
        protected TextView title;
        protected TextView details;
    }

    public static String getDate(int dayCode){
        String day ="Sunday";
        switch (dayCode){
            case Calendar.MONDAY:
                day = "Monday";
                break;
            case Calendar.TUESDAY:
                day = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                day = "Wednesday";
                break;
            case Calendar.THURSDAY:
                day = "Thursday";
                break;
            case Calendar.FRIDAY:
                day = "Friday";
                break;
            case Calendar.SATURDAY:
                day = "Saturday";
                break;
            case Calendar.SUNDAY:
                day = "Sunday";
                break;
        }
        return day;
    }
}
