package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private ListView listView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;
    private Button okbtn;
    private Button cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.addtask);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

                editText = dialog.findViewById(R.id.editText);
                okbtn = dialog.findViewById(R.id.okbtn);
                cancelbtn = dialog.findViewById(R.id.cancelbtn);
                listView = findViewById(R.id.listview);

                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taskname = editText.getText().toString().trim();
                        if (!taskname.isEmpty()) {
                            taskList.add(taskname);
                            taskAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                            editText.setText("");
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        listView = findViewById(R.id.listview);
        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                taskList.remove(position);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
