package edu.qc.seclass.glm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.qc.seclass.glm.models.ItemModel;
import edu.qc.seclass.glm.models.ItemTypeModel;

/**
 * Lets the user select the type for the newly created item
 */
public class SelectTypeForNewItemActivity extends AppCompatActivity {

    public ItemTypeViewAdapter itemTypeViewAdapter;
    public static String itemName;
    public ItemTypeModel itemTypeModel;
    public TextView selectedTypeforItemLabel;
    public int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_for_new_item);

        selectedTypeforItemLabel = findViewById(R.id.selectedTypeforItemLabel);

        final ListView listView = (ListView) findViewById(R.id.selectTypeListView);
        itemTypeViewAdapter = new ItemTypeViewAdapter(this, R.layout.searchtool_row, ListManagerActivity.itemTypeModels);
        listView.setAdapter(itemTypeViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemTypeModel = ListManagerActivity.itemTypeModels.get(position);
                index = position;
                selectedTypeforItemLabel.setText(itemTypeModel.getItemTypeName());
            }
        });

        Button confirmTypeBttn = findViewById(R.id.confirmCategoryBttn);

        confirmTypeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemTypeModel == null)
                {
                    new AlertDialog.Builder(SelectTypeForNewItemActivity.this)
                            .setTitle("Invalid Input")
                            .setMessage("Please select one of the Category")
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
                    ItemModel itemModel = new ItemModel();
                    itemModel.setItemName(itemName);
                    itemModel.setItemTypeFK(itemTypeModel.getItemTypeID());
                    if(MainActivity.dataBaseHelper.createItem(itemModel))
                    {
                        ListManagerActivity.itemTypeModels.get(index).getItemModelArrayList().add(itemModel);
                        ListManagerActivity.allItemModels.add(itemModel);
                        Intent myIntent = new Intent(SelectTypeForNewItemActivity.this, SearchToolActivity.class);
                        startActivity(myIntent);
                    }
                }
            }
        });
    }


    /** Creates elements into the ListView*/
    private class ItemTypeViewAdapter extends ArrayAdapter<ItemTypeModel> {
        private int layout;
        private Context mContext;
        int mResource;

        public ItemTypeViewAdapter(@NonNull Context context, int resource, @NonNull List<ItemTypeModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            TextView listItemName = (TextView) convertView.findViewById(R.id.searchToolRowLabel);
            listItemName.setText(getItem(position).getItemTypeName());
            return convertView;
        }
    }
}
