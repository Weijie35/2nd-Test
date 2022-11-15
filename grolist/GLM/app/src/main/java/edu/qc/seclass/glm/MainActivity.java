package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.qc.seclass.glm.models.GroceryListItemModel;
import edu.qc.seclass.glm.models.GroceryListModel;
import edu.qc.seclass.glm.models.ItemModel;
import edu.qc.seclass.glm.models.ItemTypeModel;
/** This lass logs in the user */
public class MainActivity extends AppCompatActivity {
    TextView welcomeLabel;
    EditText usernameET;
    EditText passwordET;
    Button loginButton;
    Button registerButton;
    public static DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        dataBaseHelper.createDefaultItemTypesAndItems();
        welcomeLabel = findViewById(R.id.welcomeLabel);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);

        //button listeners for the add and view all buttons
        loginButton = findViewById(R.id.loginBttn);
        registerButton = findViewById(R.id.signUpBttn);


        //updated testing list
        ListManagerActivity.createObjectListItem();
        ListManagerActivity.createObjectItemType();



        //When login button is clicked, it takes the user to ListManagerActivity class
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ListManagerActivity.class);
                startActivity(myIntent);            }
        });
    }

}
