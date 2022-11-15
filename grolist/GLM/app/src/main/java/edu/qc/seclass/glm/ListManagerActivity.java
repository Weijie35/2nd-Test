package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.qc.seclass.glm.models.GroceryListModel;
import edu.qc.seclass.glm.models.ItemModel;
import edu.qc.seclass.glm.models.ItemTypeModel;

public class ListManagerActivity extends AppCompatActivity {

    public static ListsListViewAdapter listsAdapter = null;
    public static ListView listView;

    //Contains the Lists for the user
    public static ArrayList<GroceryListModel> groceryListModels;

    //ItemType
    public static ArrayList<ItemTypeModel> itemTypeModels;
    public static ArrayList<ItemModel> allItemModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_manager);

        //initialize the ListName Arraylist
        EditText editText = findViewById(R.id.RenameListET);

        listView = (ListView) findViewById(R.id.listsListView);

        listsAdapter = new ListsListViewAdapter(this, R.layout.list_row, groceryListModels);
        listView.setAdapter(listsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItemActivity.groceryListModel = groceryListModels.get(position);
                ListItemActivity.ReOrderByType();
                Intent myIntent = new Intent(ListManagerActivity.this, ListItemActivity.class);
                startActivity(myIntent);
            }
        });
        Button createButton = findViewById(R.id.createListBttn);
        Button deleteButton = findViewById(R.id.deleteListBttn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListManagerActivity.this, CreateNewListActivity.class);
                startActivity(myIntent);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListManagerActivity.this, DeleteListActivity.class);
                startActivity(myIntent);
            }
        });
    }


    /** Creates elements into the ListView*/
    private class ListsListViewAdapter extends ArrayAdapter<GroceryListModel> {
        private int layout;
        //
        private Context mContext;
        int mResource;

        public ListsListViewAdapter(@NonNull Context context, int resource, @NonNull List<GroceryListModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String temp = getItem(position).getGroceryListName();
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            // If the listButton is clicked, the createRenameDialog method is called
            TextView listName = (TextView) convertView.findViewById(R.id.listRowLabel);
            ImageButton listButton = (ImageButton) convertView.findViewById(R.id.listRowBttn);
            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createRenameDialog(position);
                }
            });

            listName.setText(temp);


            return convertView;
        }
    }

    /** This method shows a fragment popup that creates an instance of the RenameListDialog to rename the list
     * @param position of the listButton clicked to be renamed */
    public void createRenameDialog(int position)
    {
        RenameListDialog renameListDialog = new RenameListDialog();
        renameListDialog.position = position;
        renameListDialog.show(getSupportFragmentManager(), "rename list");
    }

    /** This method instantiates the groceryListModels   */
    public static void createObjectListItem()
    {
        groceryListModels = new ArrayList<>();
    //MainActivity.dataBaseHelper.getAllGroceryLists()
        groceryListModels = MainActivity.dataBaseHelper.getAllGroceryLists();
        for(int i = 0; i<groceryListModels.size(); i++)
        {
            System.out.println(groceryListModels.get(i).getGroceryListName() + groceryListModels.get(i).getGroceryListID());
            groceryListModels.get(i).setGroceryListItemModelArrayList(MainActivity.dataBaseHelper.getAllGroceryListItems(groceryListModels.get(i).getGroceryListID()));
        }
    }

    /** adds items and itemsTypes from the database to separate arrayLists (itemTypeModels and allItemModels */
    public static void createObjectItemType()
    {
        itemTypeModels = new ArrayList<>();
        allItemModels = new ArrayList<>();

        itemTypeModels = MainActivity.dataBaseHelper.getAllItemTypes();

        for(ItemTypeModel itemTypeModeltemp : itemTypeModels)
        {
            itemTypeModeltemp.setItemModelArrayList(MainActivity.dataBaseHelper.getItemsByCategory(itemTypeModeltemp.getItemTypeID()));
            allItemModels.addAll(itemTypeModeltemp.getItemModelArrayList());
        }
    }
}





