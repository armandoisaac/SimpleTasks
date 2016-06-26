package com.diciannove.simpletasks.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diciannove.simpletasks.SimpleTask;
import com.diciannove.simpletasks.sql.ConnectionHelper;

import java.util.ArrayList;

import nl.qbusict.cupboard.QueryResultIterable;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by agarcia on 6/25/2016.
 */
public class TaskRepository {

    // Database
    private ConnectionHelper connection;
    SQLiteDatabase db;

    public TaskRepository(Context context){
        // Create the database connection
        connection = new ConnectionHelper(context);
        db = connection.getWritableDatabase();
    }

    public ArrayList<SimpleTask>  getItems(){
        ArrayList<SimpleTask> tasks = new ArrayList<SimpleTask>();
        Cursor bunnies = cupboard().withDatabase(db).query(SimpleTask.class).getCursor();
        try {
            QueryResultIterable<SimpleTask> itr = cupboard().withCursor(bunnies).iterate(SimpleTask.class);
            for (SimpleTask task : itr) {
                tasks.add(task);
            }
        } finally {
            // close the cursor
            bunnies.close();
        }

        return tasks;
    }

    public long insert(SimpleTask task){
        return cupboard().withDatabase(db).put(task);
    }

    public void update(SimpleTask task){
        cupboard().withDatabase(db).put(task);
    }

    public void remove(long id){
        cupboard().withDatabase(db).delete(SimpleTask.class, id);
    }
}