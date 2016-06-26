package com.diciannove.simpletasks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.diciannove.simpletasks.util.ResourcesUtils;

public class EditToDo extends AppCompatActivity {

    private int _position;
    private SimpleTask task;

    //
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        BuildSpinner();

        Intent intent = getIntent();
        task = (SimpleTask)intent.getSerializableExtra("com.diciannove.simpletasks.todo");
        _position = intent.getIntExtra("com.diciannove.simpletasks.index", -1);

        EditText textBox = (EditText)findViewById(R.id.etEditText);

        // Set the values
        textBox.setText(task.task);
        spinner.setSelection(task.priority);
    }

    private void BuildSpinner(){
        spinner = (Spinner) findViewById(R.id.spPriority);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                task.priority = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
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