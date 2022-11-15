package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.qc.seclass.glm.models.GroceryListModel;

/** This class is an activity responsible for Adding a new list to the list manager
 * */
public class CreateNewListActivity extends AppCompatActivity {
    /**onCreate() method  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);

        //EditText for new List Creation
        final EditText editText = findViewById(R.id.HPcreateNewListET);

        //Button for submitting the name for the newly created list
        Button button = findViewById(R.id.HPcreateNewBttn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().replaceAll("\\s+", "").length()==0)
                {
                    new AlertDialog.Builder(CreateNewListActivity.this)
                            .setTitle("Invalid Input")
                            .setMessage("Please enter the list name")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setCancelable(false)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    //UPDATES THE LIST with the newly added list
                    GroceryListModel listModelTemp = new GroceryListModel(editText.getText().toString());
                    MainActivity.dataBaseHelper.createGroceryList(listModelTemp);
                    ListManagerActivity.groceryListModels.add(listModelTemp);
                    ListManagerActivity.listView.invalidateViews();
                    Intent myIntent = new Intent(CreateNewListActivity.this, ListManagerActivity.class);
                    startActivity(myIntent);
                }
            }
        });
    }
}
