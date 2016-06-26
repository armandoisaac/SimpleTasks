package com.diciannove.simpletasks;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by agarcia on 6/25/2016.
 */
public class SimpleTask implements Serializable{

    public Long _id;
    public String task;

    public SimpleTask(){}

    public SimpleTask(String task){
        this.task = task;
    }
}
