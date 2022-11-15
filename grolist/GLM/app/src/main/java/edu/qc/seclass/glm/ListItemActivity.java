package edu.qc.seclass.glm;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import edu.qc.seclass.glm.models.GroceryListItemModel;
import edu.qc.seclass.glm.models.GroceryListModel;
import edu.qc.seclass.glm.models.ItemTypeModel;

/** This class is responsible for the GroceryListItem Activity  */
public class ListItemActivity extends AppCompatActivity {
    /** Custom Adapter */
    public static ListsListViewAdapter listItemAdapter = null;
    /** ListItemView  */
    public static ListView listItemView;
    /** GroceryListModel that the listItemBelongs to  */
    public static GroceryListModel groceryListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        TextView textView = (TextView) findViewById(R.id.listItemLabel);
        textView.setText(groceryListModel.getGroceryListName());
        listItemView = (ListView) findViewById(R.id.listItemView);
        //populate the list adapter
        listItemAdapter = new ListsListViewAdapter(this, R.layout.listitem_row, groceryListModel.getGroceryListItemModelArrayList());
        listItemView.setAdapter(listItemAdapter);

        /* If the selectAllButton is clicked, for every GroceryListItem in the groceryListModelArrayList,
         * if the item has been unchecked, we use the getter method to toggle the value and update the database   */
        Button selectAllButton = findViewById(R.id.selectAllBttn);
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(GroceryListItemModel listItemModel : groceryListModel.getGroceryListItemModelArrayList())
                {
                    if(!listItemModel.getIsCheckedAsBoolean())
                    {
                        listItemModel.setIsCheckedFromBoolean(true);
                        MainActivity.dataBaseHelper.updateGroceryListItemIsChecked(listItemModel.getGroceryListItemID(), true);
                    }
                }
                listItemView.invalidateViews();
            }
        });

        /* If the unselectAllButton is clicked, for every GroceryListItem in the groceryListModelArrayList,
         * if the item has been checked, we use the getter method to toggle the value and update the database   */
        Button unSelectAllButton = findViewById(R.id.unselectAllBttn);
        unSelectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(GroceryListItemModel listItemModel : groceryListModel.getGroceryListItemModelArrayList())
                {
                    if(listItemModel.getIsCheckedAsBoolean())
                    {
                        listItemModel.setIsCheckedFromBoolean(false);
                        MainActivity.dataBaseHelper.updateGroceryListItemIsChecked(listItemModel.getGroceryListItemID(), false);
                    }
                }
                listItemView.invalidateViews();
            }
        });

        //  If the deleteItemButton is clicked, the activity is switched to the DeleteListItemActivity*/
        Button deleteItemButton= findViewById(R.id.deleteItemBttn);
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListItemActivity.this, DeleteListItemActivity.class);
                startActivity(myIntent);
            }
        });

        // If the deleteItemButton is clicked, the activity is switched to the QuantityListItemActivity
        Button editQuantityItemButton= findViewById(R.id.editQuantityItemBttn);
        editQuantityItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListItemActivity.this, QuantityListItemActivity.class);
                startActivity(myIntent);
            }
        });

        // If the addItemButton is clicked, the activity is switched to the SearchToolActivity
        Button addItemButton = findViewById(R.id.addItemBttn);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchToolActivity.itemTypeQuery = new ArrayList<>();
                SearchToolActivity.itemTypeQuery.addAll(ListManagerActivity.itemTypeModels);
                SearchToolActivity.itemQuery = new ArrayList<>();
                SearchToolActivity.itemQuery.addAll(ListManagerActivity.allItemModels);
                Intent myIntent = new Intent(ListItemActivity.this, SearchToolActivity.class);
                startActivity(myIntent);
            }
        });

        Button listItemGoBackBttn = findViewById(R.id.ListItemGoBackBttn);
        listItemGoBackBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListItemActivity.this, ListManagerActivity.class);
                startActivity(myIntent);
            }
        });
    }
    /** This class creates the custom ListsListViewAdapter */
    private class ListsListViewAdapter extends ArrayAdapter<GroceryListItemModel> {

        private Context mContext;
        int mResource;

        /** Constructor to set the mContext and mResource */
        public ListsListViewAdapter(@NonNull Context context, int resource, @NonNull List<GroceryListItemModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }
        /** First the properties of the GroceryListItemModel are pulled.
         * Then the list item is updated wiht the quantity and GroceryListItem name.
         * The checked property is also updated in the database  */
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String temp = getItem(position).getItemName();
            String itemtypename = getItem(position).getItemTypeName();
            int countTemp = getItem(position).getItemQuantity();
            Boolean boolTemp = getItem(position).getIsCheckedAsBoolean();

            final LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView itemName = (TextView) convertView.findViewById(R.id.listItemRowLabel);
            TextView listItemTypeRowLabel = (TextView) convertView.findViewById(R.id.listItemTypeRowLabel);
            TextView itemCount = (TextView) convertView.findViewById(R.id.listItemRowCountLabel);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.listItemCheckBox);
            checkBox.setChecked(boolTemp);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    groceryListModel.getGroceryListItemModelArrayList().get(position).setIsCheckedFromBoolean(isChecked);
                    MainActivity.dataBaseHelper.updateGroceryListItemIsChecked(groceryListModel.getGroceryListItemModelArrayList().get(position).getGroceryListItemID(), isChecked);
                }
            });

            itemName.setText(temp);
            listItemTypeRowLabel.setText(itemtypename);
            itemCount.setText(Integer.toString(countTemp));
            return convertView;
        }
    }

    /** This method lets the items in the user list to be reorder by type */
    public static void ReOrderByType()
    {
        ArrayList<GroceryListItemModel> groceryListItemModelArrayListTEMP = new ArrayList<>();
        for(ItemTypeModel itemTypeModel : ListManagerActivity.itemTypeModels)
        {
            for(GroceryListItemModel groceryListItemModel : groceryListModel.getGroceryListItemModelArrayList())
            {
                if(groceryListItemModel.getItemTypeName().compareTo(itemTypeModel.getItemTypeName()) == 0)
                {
                    groceryListItemModelArrayListTEMP.add(groceryListItemModel);
                }
            }
        }
        groceryListModel.getGroceryListItemModelArrayList().removeAll(groceryListModel.getGroceryListItemModelArrayList());
        for(int i = 0; i<groceryListItemModelArrayListTEMP.size(); i++)
        {
            groceryListModel.getGroceryListItemModelArrayList().add(groceryListItemModelArrayListTEMP.get(i));
        }
//        groceryListModel.getGroceryListItemModelArrayList().addAll(groceryListItemModelArrayListTEMP);
    }
}
