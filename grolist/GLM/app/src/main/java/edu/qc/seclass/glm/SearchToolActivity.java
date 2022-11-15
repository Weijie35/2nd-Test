package edu.qc.seclass.glm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.qc.seclass.glm.models.ItemModel;
import edu.qc.seclass.glm.models.ItemTypeModel;

/**
 * Lets the user search and add items to the list
 */
public class SearchToolActivity extends AppCompatActivity {

    ItemTypeViewAdapter itemTypeViewAdapter;
    ItemViewAdapter itemViewAdapter;
    EditText searchToolET;
    Button searchToolGoBackBttn;
    Button createNewItemBttn;
    RadioButton byCategoryRadio;
    RadioButton byItemNameRadio;
    String mode;

    public static ArrayList<ItemTypeModel> itemTypeQuery;
    public static ArrayList<ItemModel> itemQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tool);

        searchToolET = findViewById(R.id.searchToolET);
        byCategoryRadio = findViewById(R.id.byCategoryRadio);
        byItemNameRadio = findViewById(R.id.byItemNameRadio);

        mode = "Category";

        final ListView listView = (ListView) findViewById(R.id.searchToolListView);

        if(ListManagerActivity.itemTypeModels.size() >0)
        {
            System.out.println("Has Items inside");
        }
        itemTypeViewAdapter = new ItemTypeViewAdapter(this, R.layout.searchtool_row, itemTypeQuery);
        itemViewAdapter = new ItemViewAdapter(this, R.layout.searchtool_row, itemQuery);
        listView.setAdapter(itemTypeViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if category then it will display the items for that type
                if(mode.compareTo("Category") == 0 && listView.getAdapter()!=itemViewAdapter)
                {
                    ItemTypeModel itemTypeModeltemp = itemTypeQuery.get(position);
                    searchToolET.setText(itemTypeModeltemp.getItemTypeName());
                    itemQuery.removeAll(ListManagerActivity.allItemModels);
                    itemQuery.addAll(itemTypeModeltemp.getItemModelArrayList());
                    listView.setAdapter(itemViewAdapter);
                }
                // else dialog with the item will be asked to enter the quantity
                else
                {
                    createRenameDialog(position);
                }
            }
        });

        /** Set the search tool to default values */
        Button clearSearchButton = findViewById(R.id.clearSearchBttn);
        clearSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "Category";
                searchToolET.setText("");
                if(!byCategoryRadio.isChecked()) {
                    byCategoryRadio.toggle();
                }
                setDefaultItemTypeQuery();
                itemTypeViewAdapter.notifyDataSetChanged();

                setDefaultItemQuery();
                itemViewAdapter.notifyDataSetChanged();

                listView.setAdapter(itemTypeViewAdapter);
                listView.invalidateViews();
            }
        });

        /** Takes the user back to User List */
        searchToolGoBackBttn = findViewById(R.id.SearchToolGoBackBttn);
        searchToolGoBackBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListItemActivity.ReOrderByType();
                Intent myIntent = new Intent(SearchToolActivity.this, ListItemActivity.class);
                startActivity(myIntent);
            }
        });

        /** takes the user to different activity to create a new item */
        createNewItemBttn = findViewById(R.id.createNewItemBttn);
        createNewItemBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SearchToolActivity.this, CreatingNewItemActivity.class);
                startActivity(myIntent);
            }
        });

        /** switches the search mode to Category */
        byCategoryRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && mode.compareTo("Category") != 0)
                {
                    mode = "Category";
                    searchToolET.setText("");
                    setDefaultItemTypeQuery();
                    listView.setAdapter(itemTypeViewAdapter);
                    itemTypeViewAdapter.notifyDataSetChanged();
                    listView.invalidateViews();
                }
            }
        });

        /** switches the search mode to Item name */
        byItemNameRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && mode.compareTo("Item Name") != 0)
                {
                    mode = "Item Name";
                    searchToolET.setText("");
                    setDefaultItemQuery();
                    itemViewAdapter.notifyDataSetInvalidated();
                    if(listView.getAdapter()!=itemViewAdapter)
                        listView.setAdapter(itemViewAdapter);
                    itemViewAdapter.notifyDataSetChanged();
                    listView.invalidateViews();
                }
            }
        });

        /** lets the user input a specific type or item name */
        searchToolET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length()>0)
                {
                    if(mode.compareTo("Category") == 0) {
                        if(listView.getAdapter()!=itemTypeViewAdapter)
                        {
                            listView.setAdapter(itemTypeViewAdapter);
                        }
                        for (ItemTypeModel itemTypeTemp : ListManagerActivity.itemTypeModels) {
                            if (!itemTypeQuery.contains(itemTypeTemp) && itemTypeTemp.getItemTypeName().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                itemTypeQuery.add(itemTypeTemp);
                            } else if (itemTypeQuery.contains(itemTypeTemp) && !itemTypeTemp.getItemTypeName().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                itemTypeQuery.remove(itemTypeTemp);
                            }
                        }
                        for (ItemTypeModel itemTypeTemp : ListManagerActivity.itemTypeModels) {
                            if (!itemTypeQuery.contains(itemTypeTemp) && itemTypeTemp.getItemTypeName().toLowerCase().contains(s.toString().toLowerCase())) {
                                itemTypeQuery.add(itemTypeTemp);
                            }
                        }
                        itemTypeViewAdapter.notifyDataSetChanged();

                    }
                    else if(mode.compareTo("Item Name") == 0)
                    {
                        System.out.println("Item Name");
                        if(listView.getAdapter()!=itemViewAdapter)
                        {
                            listView.setAdapter(itemViewAdapter);
                        }
                        for (ItemModel itemTemp : ListManagerActivity.allItemModels) {
                            if (!itemQuery.contains(itemTemp) &&itemTemp.getItemName().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                itemQuery.add(itemTemp);
                            } else if (itemQuery.contains(itemTemp) && !itemTemp.getItemName().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                itemQuery.remove(itemTemp);
                                System.out.println("Removed" + itemQuery.size());
                            }
                        }
                        for (ItemModel itemTemp : ListManagerActivity.allItemModels) {
                            if (!itemQuery.contains(itemTemp) &&itemTemp.getItemName().toLowerCase().contains(s.toString().toLowerCase())) {
                                itemQuery.add(itemTemp);
                            }
                        }
                    }
                    listView.invalidateViews();
                }
                else
                {
                    if(mode.compareTo("Category") == 0)
                    {
                        setDefaultItemTypeQuery();
                        itemTypeViewAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                    }
                    else if(mode.compareTo("Item Name") == 0)
                    {
                        setDefaultItemQuery();
                        itemViewAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                    }
                }

            }
        });


    }


    /** Creates elements into the ListView for Types*/
    private class ItemTypeViewAdapter extends ArrayAdapter<ItemTypeModel> {
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

    /** Creates elements into the ListView for Items*/
    private class ItemViewAdapter extends ArrayAdapter<ItemModel> {
        private int layout;
        private Context mContext;
        int mResource;

        public ItemViewAdapter(@NonNull Context context, int resource, @NonNull List<ItemModel> objects) {
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
            listItemName.setText(getItem(position).getItemName());
            return convertView;
        }
    }

    /** resets the ListView for Types */
    public static void setDefaultItemTypeQuery()
    {
        itemTypeQuery.removeAll(ListManagerActivity.itemTypeModels);
        itemTypeQuery.addAll(ListManagerActivity.itemTypeModels);
    }

    /** resets the ListView for Items */
    public static void setDefaultItemQuery()
    {
        itemQuery.removeAll(ListManagerActivity.allItemModels);
        itemQuery.addAll(ListManagerActivity.allItemModels);
    }

    /** Creates the dialog for adding the item to the list */
    public void createRenameDialog(int position)
    {
        AddItemToListDialog addItemToListDialog = new AddItemToListDialog();
        addItemToListDialog.position = position;
        addItemToListDialog.show(getSupportFragmentManager(), "add item to list");
    }

}
