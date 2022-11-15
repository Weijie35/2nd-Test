package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.qc.seclass.glm.models.ItemModel;

/**
 * Activity for creating a new Item
 */
public class CreatingNewItemActivity extends AppCompatActivity {

    EditText newItemNameET;
    Button confirmItemNameBttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_new_item);

        newItemNameET = findViewById(R.id.newItemNameET);
        confirmItemNameBttn = findViewById(R.id.confirmItemNameBttn);

        confirmItemNameBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = newItemNameET.getText().toString().toLowerCase();
                if(itemName.isEmpty())
                {
                    new AlertDialog.Builder(CreatingNewItemActivity.this)
                            .setTitle("Invalid Input")
                            .setMessage("Can't be an Empty String")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setCancelable(false)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else
                {
                    boolean isAlreadyAdded = false;
                    for(ItemModel itemModel : ListManagerActivity.allItemModels)
                    {
                        if(itemModel.getItemName().toLowerCase().compareTo(itemName) == 0)
                        {
                            isAlreadyAdded = true;
                            break;
                        }
                    }
                    if(isAlreadyAdded)
                    {
                        new AlertDialog.Builder(CreatingNewItemActivity.this)
                                .setTitle("Invalid Input")
                                .setMessage(itemName + " is already added")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        newItemNameET.setText("");
                                    }
                                })
                                .setCancelable(false)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                    else
                    {
                        SelectTypeForNewItemActivity.itemName = itemName.substring(0, 1).toUpperCase() + itemName.substring(1);
                        Intent myIntent = new Intent(CreatingNewItemActivity.this, SelectTypeForNewItemActivity.class);
                        startActivity(myIntent);
                    }
                }
            }
        });

    }
}
