package com.diciannove.simpletasks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditToDo extends AppCompatActivity {

    private int _position;
    private SimpleTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        Intent intent = getIntent();
        task = (SimpleTask)intent.getSerializableExtra("com.diciannove.simpletasks.todo");
        _position = intent.getIntExtra("com.diciannove.simpletasks.index", -1);

        EditText textBox = (EditText)findViewById(R.id.etEditText);
        textBox.setText(task.task);
    }

    protected void onSaveButtonClick(View v){
        EditText etNewItem = (EditText)findViewById(R.id.etEditText);
        String itemText = etNewItem.getText().toString();

        // Update the values
        task.task = itemText;

        // Return the value
        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.diciannove.simpletasks.todo", task);
        returnIntent.putExtra("com.diciannove.simpletasks.index", _position);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}