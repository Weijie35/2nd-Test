package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {
    private int idAssign = 0; //for record the next id would be
    private static final String TABLE_NAME = "items";
    private static final String ID_COL = "itemID";
    private static final String TYPE_COL = "itemType";
    private static final String NAME_COL = "itemName";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databse);

        //items in the initial database
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        idAssign = dbHelper.readAllItems(database, "items").size();
        displayItems();
    }


    /*this method is used to display all items in the database by updating the listView
    *
    * */
    public void displayItems(){
        ArrayList<String> data = dbHelper.readAllItems(database, "items");
        ListView listView;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DatabaseActivity.this,
                android.R.layout.simple_list_item_1, data);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }


    public void addItemToDB(View view){
        try{
            EditText itemNameEdt = (EditText)findViewById(R.id.itemName);
            EditText itemTypeEdt = (EditText)findViewById(R.id.itemType);
            String itemName = itemNameEdt.getText().toString();
            String itemType = itemTypeEdt.getText().toString();

            // validating if the text fields are empty or not.
            if (itemType.isEmpty() && itemName.isEmpty()) {
                Toast.makeText(this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            idAssign++;
            dbHelper.addItemToTable(database, idAssign, itemType, itemName);

            //success
            Toast.makeText(this, "Item has been added.", Toast.LENGTH_SHORT).show();
            displayItems();
        }catch (Exception e){
            Toast.makeText(this, "error happened", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteItemFromDB(View view){
        try{
            EditText itemIDEdt = (EditText)findViewById(R.id.itemID);
            String itemIDStr = itemIDEdt.getText().toString();

            if (itemIDStr.isEmpty()) {
                Toast.makeText(this, "empty input", Toast.LENGTH_SHORT).show();
                return;
            }
            int itemID = Integer.parseInt(itemIDStr);

            dbHelper.deleteItemById(database,"items", itemID);

            //success
            Toast.makeText(this, "Item has been deleted.", Toast.LENGTH_SHORT).show();
            displayItems();
        }catch (Exception e){
            Toast.makeText(this, "error happened", Toast.LENGTH_SHORT).show();
        }
    }
}