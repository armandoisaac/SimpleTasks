package com.diciannove.simpletasks;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by agarcia on 6/25/2016.
 */
public class SimpleTask implements Serializable{

    public Long _id;
    public String task;
    public int priority;

    public SimpleTask(){
        this.priority = 1;
    }

    public SimpleTask(String task){
        this.task = task;
        this.priority = 1;
    }

    public SimpleTask(String task, int priority){
        this.task = task;
        this.priority = priority;
    }
}
