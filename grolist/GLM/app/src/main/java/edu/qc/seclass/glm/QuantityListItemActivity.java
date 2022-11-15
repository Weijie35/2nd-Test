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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.qc.seclass.glm.models.GroceryListItemModel;
/** This class is responsible for changing the quantity of a specified item */
public class QuantityListItemActivity extends AppCompatActivity {
    HashMap<Integer, Integer> editTextHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity_list_item);

        editTextHashMap = new HashMap<>();

        final ListView listView = (ListView) findViewById(R.id.QuantityListItemListView);

        listView.setAdapter(new ListItemsViewAdapter(this, R.layout.listitemquantity_row, ListItemActivity.groceryListModel.getGroceryListItemModelArrayList()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QuantityListItemActivity.this, "Textbox clicked on " + position, Toast.LENGTH_SHORT).show();
            }
        });

        Button button = findViewById(R.id.HPQuantityListItemBttn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<editTextHashMap.size(); i++)
                {
                    if(editTextHashMap.get(i) != ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().get(i).getItemQuantity())
                    {
                        ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().get(i).setItemQuantity(editTextHashMap.get(i));
                        MainActivity.dataBaseHelper.updateGroceryListItemQuantity(ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().get(i).getGroceryListItemID()
                                            , editTextHashMap.get(i));
                    }
                }
                ListItemActivity.listItemView.invalidateViews();
                Intent myIntent = new Intent(QuantityListItemActivity.this, ListItemActivity.class);
                startActivity(myIntent);
            }
        });
    }


    /** Creates elements into the ListView*/
    private class ListItemsViewAdapter extends ArrayAdapter<GroceryListItemModel> {
        private int layout;
        private Context mContext;
        int mResource;

        public ListItemsViewAdapter(@NonNull Context context, int resource, @NonNull List<GroceryListItemModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            int countTemp = getItem(position).getItemQuantity();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView listItemName = (TextView) convertView.findViewById(R.id.quantityListItemRowLabel);
            listItemName.setText(getItem(position).getItemName());
            EditText editText = (EditText) convertView.findViewById(R.id.quantityListItemRowET);
            editText.setText(Integer.toString(countTemp));
            editText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    if(s.length() >0) {
                        editTextHashMap.put(position, Integer.parseInt(s.toString()));
                    }

                }
            });

            return convertView;
        }
    }
}
