package com.diciannove.simpletasks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.diciannove.simpletasks.adapters.TaskViewAdapter;
import com.diciannove.simpletasks.db.TaskRepository;
import java.util.ArrayList;

public final class MainActivity extends AppCompatActivity {

    private ArrayList<SimpleTask> items;
    private TaskViewAdapter itemsAdapter;
    private ListView lvItems;

    // repository
    private final static String fileName = "todo.txt";
    private TaskRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // repository
        repository = new TaskRepository(this);

        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new TaskViewAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    protected void onAddItemClick(View v){
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();

        // Add the item to the adapter and insert to the db
        SimpleTask task = new SimpleTask(itemText);
        task._id = repository.insert(task);
        itemsAdapter.add(task);

        // Clear the text
        etNewItem.setText("");
    }

    private void setupListViewListener(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                SimpleTask item = items.get(position);

                Intent intent = new Intent(MainActivity.this, EditToDo.class);
                intent.putExtra("com.diciannove.simpletasks.todo", item);
                intent.putExtra("com.diciannove.simpletasks.index", position);
                startActivityForResult(intent, 1);
            }
        });
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id){

                        // Remove from the list and delete from the database
                        repository.remove(items.get(pos)._id);
                        items.remove(pos);

                        // Refresh the view
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
        }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                SimpleTask result = (SimpleTask)data.getSerializableExtra("com.diciannove.simpletasks.todo");
                int position = data.getIntExtra("com.diciannove.simpletasks.index", -1);

                if(position > -1){
                    items.set(position, result);
                    itemsAdapter.notifyDataSetChanged();
                    repository.update(result);
                }
            }
        }
    }//onActivityResult


    private void readItems(){
        items = repository.getItems();
    }

    private void saveItems(){

    }
}
