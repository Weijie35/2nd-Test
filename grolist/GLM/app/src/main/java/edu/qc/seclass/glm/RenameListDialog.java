package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
/** This class is responsible for editing the name of a List*/
public class RenameListDialog extends AppCompatDialogFragment {
    private EditText editText;
    int position;

    /** Diaglog created to give the user the option to edit the name of the list */
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.listmanager_rename, null);
        builder.setView(view).setTitle("Rename List")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText.getText().toString();
                        //updating the database with the new list name
                        if(MainActivity.dataBaseHelper.updateGroceryListName(ListManagerActivity.groceryListModels.get(position).getGroceryListID(),name))
                        {
                            ListManagerActivity.groceryListModels.get(position).setGroceryListName(name);

                        }
                        ListManagerActivity.listView.invalidateViews();
                    }
                });
        editText = view.findViewById(R.id.RenameListET);
        return builder.create();
    }

}