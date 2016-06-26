package com.diciannove.simpletasks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    // file
    private final static String fileName = "todo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    protected void onAddItemClick(View v){
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");

        // Save the item
        saveItems();
    }

    private void setupListViewListener(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                String item = items.get(position);

                Intent intent = new Intent(MainActivity.this, EditToDo.class);
                intent.putExtra("com.diciannove.simpletasks.todo", item);
                intent.putExtra("com.diciannove.simpletasks.index", position);
                startActivityForResult(intent, 1);
            }
        });
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id){
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        saveItems();
                        return  true;
                    }
        }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("com.diciannove.simpletasks.todo");
                int position = data.getIntExtra("com.diciannove.simpletasks.index", -1);

                if(position > -1){
                    items.set(position, result);
                    itemsAdapter.notifyDataSetChanged();
                    saveItems();
                }
            }
        }
    }//onActivityResult


    private void readItems(){
        File dirList = getFilesDir();
        File todoFile = new File(dirList, fileName);
        try{
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }catch (IOException e){
            items = new ArrayList<>();
        }
    }

    private void saveItems(){
        File dirList = getFilesDir();
        File todoFile = new File(dirList, fileName);
        try{
            FileUtils.writeLines(todoFile, items);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
