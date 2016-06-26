package com.diciannove.simpletasks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diciannove.simpletasks.R;
import com.diciannove.simpletasks.SimpleTask;

import java.util.ArrayList;

/**
 * Created by agarcia on 6/25/2016.
 */
public class TaskViewAdapter extends ArrayAdapter<SimpleTask> {
    public TaskViewAdapter(Context context, ArrayList<SimpleTask> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SimpleTask task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        // Populate the data into the template view using the data object
        tvName.setText(task.task);
        // Return the completed view to render on screen
        return convertView;
    }
}