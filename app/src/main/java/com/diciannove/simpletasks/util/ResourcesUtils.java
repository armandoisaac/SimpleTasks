package com.diciannove.simpletasks.util;

import android.content.Context;

import com.diciannove.simpletasks.R;

/**
 * Created by agarcia on 6/25/2016.
 */
public class ResourcesUtils {

    public static int findPriority(Context ctx, String priority){
        String[] priorities = ctx.getResources().getStringArray(R.array.priorities);
        for (String s : priorities) {
            int i = s.indexOf(priority);
            if (i > -1) {
                return  i;
            }
        }

        return -1;
    }

    public static String getPriorityName(Context ctx, int priority){
        String[] priorities = ctx.getResources().getStringArray(R.array.priorities);
        return priorities[priority];
    }
}
