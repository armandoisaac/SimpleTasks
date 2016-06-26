package com.diciannove.simpletasks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditToDo extends AppCompatActivity {

    private int _position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        Intent intent = getIntent();
        String toDo = intent.getStringExtra("com.diciannove.simpletasks.todo");
        _position = intent.getIntExtra("com.diciannove.simpletasks.index", -1);

        EditText textBox = (EditText)findViewById(R.id.etEditText);
        textBox.setText(toDo);
    }

    protected void onSaveButtonClick(View v){
        EditText etNewItem = (EditText)findViewById(R.id.etEditText);
        String itemText = etNewItem.getText().toString();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.diciannove.simpletasks.todo", itemText);
        returnIntent.putExtra("com.diciannove.simpletasks.index", _position);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}