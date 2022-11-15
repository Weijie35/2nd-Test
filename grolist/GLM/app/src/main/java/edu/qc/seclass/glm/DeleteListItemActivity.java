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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import edu.qc.seclass.glm.models.GroceryListItemModel;

/** This class is responsible for deleting a grocery item from a specific list */
public class DeleteListItemActivity extends AppCompatActivity {
    /** ArrayList of deleted grocery list items */
    ArrayList<GroceryListItemModel> deletedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_list_item);
        //initialize deleted items arrayList
        deletedItems = new ArrayList<>();
        //create the listview
        final ListView listView = (ListView) findViewById(R.id.DeleteListItemListView);
        //set the adapter to the custom adapter LisItemsViewAdapter
        listView.setAdapter(new ListItemsViewAdapter(this, R.layout.listitemdelete_row, ListItemActivity.groceryListModel.getGroceryListItemModelArrayList()));
        // responsive to user click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DeleteListItemActivity.this, "Textbox clicked on " + position, Toast.LENGTH_SHORT).show();
            }
        });
        // Set on click listener on the HPDeleteListItemBttn. If the user clicks on this button, they are taken to the
        // ListItemActivity class and the selected item is removed from the database
        Button button = findViewById(R.id.HPDeleteListItemBttn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(GroceryListItemModel selectedListItemModel : deletedItems)
                {
                    MainActivity.dataBaseHelper.deleteGroceryListItem(selectedListItemModel.getGroceryListItemID());
                    ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().remove(selectedListItemModel);
                }
                ListItemActivity.listItemView.invalidateViews();
                Intent myIntent = new Intent(DeleteListItemActivity.this, ListItemActivity.class);
                startActivity(myIntent);
            }
        });
    }



    /** Creates elements into the ListView*/
    private class ListItemsViewAdapter extends ArrayAdapter<GroceryListItemModel> {
        /** Context for the ListItemsViewAdapter */
        private Context mContext;
        /** Resource */
        int mResource;

        /** Constructor for ListItemsViewAdapter */
        public ListItemsViewAdapter(@NonNull Context context, int resource, @NonNull List<GroceryListItemModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        /** Updates the ListItemsView Adapter */
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView listItemName = (TextView) convertView.findViewById(R.id.deleteListItemRowLabel);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.deleteListItemCheckBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        deletedItems.add(getItem(position));
                    }
                    else
                    {
                        deletedItems.remove(getItem(position));
                    }
                }
            });

            listItemName.setText(getItem(position).getItemName());
            return convertView;
        }
    }
}
