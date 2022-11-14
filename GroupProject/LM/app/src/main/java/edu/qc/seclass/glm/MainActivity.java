package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private MyExpandableListAdapter expandableListAdapter;
    private ArrayList<String> groups = new ArrayList<String>();
    private ArrayList<ArrayList<String>> children = new ArrayList<ArrayList<String>>();
    private Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        expandableListView = (ExpandableListView)findViewById(R.id.groceryLists);
        
        //create new myExpandableListAdapter with group and children
        expandableListAdapter = new MyExpandableListAdapter(context, groups, children);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListAdapter.addGroups("weekly");
        
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(context, "you click：" + children.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    //onclick method for the button with id "open"
    public void openDB(View v){
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }

    //onclick method for the button with id "createList"
    public void createList(View view){
        EditText inputListNameEdt = (EditText) findViewById(R.id.inputListName);
        String inputListName = inputListNameEdt.getText().toString();
        if (inputListName.isEmpty()) {
            Toast.makeText(this, "empty input", Toast.LENGTH_SHORT).show();
            return;
        }
        expandableListAdapter.addGroups(inputListName);
    }
}