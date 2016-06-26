package com.diciannove.simpletasks.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diciannove.simpletasks.R;
import com.diciannove.simpletasks.SimpleTask;
import com.diciannove.simpletasks.util.ResourcesUtils;

import java.util.ArrayList;

/**
 * Created by agarcia on 6/25/2016.
 */
public class TaskViewAdapter extends ArrayAdapter<SimpleTask> {
    private Context context;

    public TaskViewAdapter(Context context, ArrayList<SimpleTask> tasks) {
        super(context, 0, tasks);
        this.context = context;
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
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

        tvPriority.setVisibility(View.VISIBLE);
        tvName.setPadding(10, 5, 0, 0);

        // Populate the data into the template view using the data object
        tvName.setText(task.task);
        tvPriority.setText(ResourcesUtils.getPriorityName(context, task.priority) + " priority");
        //tvPriority.setText(Integer.toString(task.priority));
        // Return the completed view to render on screen


        switch (task.priority){
            case 0:
                tvPriority.setTextColor(Color.GRAY);
                break;
            case 2:
                tvPriority.setTextColor(Color.RED);
                break;
            default:
                tvPriority.setTextColor(Color.BLACK);
                tvPriority.setVisibility(View.GONE);
                tvName.setPadding(10, 10, 0, 10);
                break;
        }

        return convertView;
    }
}