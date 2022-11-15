package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import edu.qc.seclass.glm.models.GroceryListItemModel;
import edu.qc.seclass.glm.models.ItemModel;
import edu.qc.seclass.glm.models.ItemTypeModel;

public class AddItemToListDialog extends AppCompatDialogFragment {
    private EditText editText;
    int position;
    boolean isAdded;
    long groceryListItemId;
    int previousQuantity;
    int listItemPosition;


    /** Diaglog created for the user to input the quantity of the item they want to add */
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.searchtool_quantityadd, null);
        isAdded = false;
        ItemModel itemModel = SearchToolActivity.itemQuery.get(position);
        String setTitleString = itemModel.getItemName();
        String positiveButtonString = "Add";
        groceryListItemId = 0;
        previousQuantity = 0;
        listItemPosition = 0;

        //First checks whether the item was already added in the list
        for(GroceryListItemModel groceryListItemModeltemp : ListItemActivity.groceryListModel.getGroceryListItemModelArrayList())
        {
            if(groceryListItemModeltemp.getItemName().compareTo(itemModel.getItemName()) == 0)
            {
                System.out.println("Already added Item");
                isAdded = true;
                setTitleString+= " -- currently " + groceryListItemModeltemp.getItemQuantity() + " in the List";
                positiveButtonString+= " More";
                groceryListItemId = groceryListItemModeltemp.getGroceryListItemID();
                previousQuantity = groceryListItemModeltemp.getItemQuantity();
                listItemPosition = ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().indexOf(groceryListItemModeltemp);
                break;
            }
        }

        builder.setView(view).setTitle(setTitleString)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(positiveButtonString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int enteredAmount = Integer.parseInt(editText.getText().toString());
                        //Adds the item only if the amount is greater than 0
                        if(enteredAmount > 0) {
                            // if item is added quantity count is incremented by the entered amount
                            if (isAdded) {
                                ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().get(listItemPosition).setItemQuantity(previousQuantity + enteredAmount);
                                MainActivity.dataBaseHelper.updateGroceryListItemQuantity(groceryListItemId, previousQuantity + enteredAmount);
                            }
                            //else if item is not added item will be added to the list with the quantity
                            else {
                                GroceryListItemModel groceryListItemModel = new GroceryListItemModel();
                                groceryListItemModel.setIsCheckedFromBoolean(false);
                                groceryListItemModel.setItemQuantity(enteredAmount);
                                groceryListItemModel.setGroceryListFK(ListItemActivity.groceryListModel.getGroceryListID());
                                groceryListItemModel.setItemFK(SearchToolActivity.itemQuery.get(position).getItemID());
                                groceryListItemModel.setItemName(SearchToolActivity.itemQuery.get(position).getItemName());
                                groceryListItemModel.setItemTypeID(SearchToolActivity.itemQuery.get(position).getItemTypeFK());
                                boolean itemTypeNameAdded = false;
                                for(ItemTypeModel itemTypeModel : ListManagerActivity.itemTypeModels)
                                {
                                    if(itemTypeModel.getItemTypeID() == SearchToolActivity.itemQuery.get(position).getItemTypeFK())
                                    {
                                        groceryListItemModel.setItemTypeName(itemTypeModel.getItemTypeName());
                                        itemTypeNameAdded = true;
                                        break;
                                    }
                                }
                                if (itemTypeNameAdded && MainActivity.dataBaseHelper.createGroceryListItem(groceryListItemModel)) {
                                    ListItemActivity.groceryListModel.getGroceryListItemModelArrayList().add(groceryListItemModel);
                                }
                            }
                            ListItemActivity.listItemView.invalidateViews();
                        }
                    }
                });
        editText = view.findViewById(R.id.quantityaddET);
        return builder.create();
    }
}
