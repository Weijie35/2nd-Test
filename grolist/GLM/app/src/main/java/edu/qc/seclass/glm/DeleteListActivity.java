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
import edu.qc.seclass.glm.models.GroceryListModel;

/** This class is an activity responsible for Deleting a List when inside the List Manager */
public class DeleteListActivity extends AppCompatActivity {
    /** ArrayList of GroceryLists to be deleted */
    ArrayList<GroceryListModel> deletedLists;

    /** onCreate() method */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_list);
        // initialize new ArrayList
        deletedLists = new ArrayList<>();
        //linking listView
        final ListView listView = (ListView) findViewById(R.id.listsListView);
        //populating listView
        listView.setAdapter(new ListsListViewAdapter(this, R.layout.listdelete_row, ListManagerActivity.groceryListModels));
        //adding onClickListener to listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DeleteListActivity.this, "Textbox clicked on " + position, Toast.LENGTH_SHORT).show();
            }
        });

        //initializing delete button
        Button button = findViewById(R.id.HPDeleteListBttn);
        //onClickListener for delete button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(GroceryListModel selectedListModel : deletedLists)
                {
                    MainActivity.dataBaseHelper.deleteGroceryList(selectedListModel.getGroceryListID());
                    ListManagerActivity.groceryListModels.remove(selectedListModel);
                }
                ListManagerActivity.listView.invalidateViews();
                Intent myIntent = new Intent(DeleteListActivity.this, ListManagerActivity.class);
                startActivity(myIntent);
            }
        });
    }

    /** This class is responsible for updating the ListAdapter */
    private class ListsListViewAdapter extends ArrayAdapter<GroceryListModel> {
        /** Describes the activity we are currently in */
        private Context mContext;
        /** Specifies the resource */
        int mResource;

        /** Constructor for the custom ListsListViewAdapter */
        public ListsListViewAdapter(@NonNull Context context, int resource, @NonNull List<GroceryListModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView listName = (TextView) convertView.findViewById(R.id.deleteListRowLabel);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.deleteListCheckBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        deletedLists.add(getItem(position));
                    }
                    else
                    {
                        deletedLists.remove(getItem(position));
                    }
                }
            });

            listName.setText(getItem(position).getGroceryListName());

            return convertView;
        }
    }
}
