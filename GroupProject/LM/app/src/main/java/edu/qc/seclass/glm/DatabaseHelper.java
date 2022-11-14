package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "grocery.db";
    private static final int DB_VERSION = 1;
    private static final String ID_COL = "itemID";
    private static final String TYPE_COL = "itemType";
    private static final String NAME_COL = "itemName";
    private static final String QUANTITY_COL = "itemQuan";
    private static final String createItemsTable = "create table items " + " ("
            + ID_COL + " INT PRIMARY KEY, "
            + NAME_COL + " TEXT NOT NULL, "
            + TYPE_COL + " TEXT NOT NULL)";

    //construction method
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override//to create the table "items"
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(createItemsTable);
        //initialize database
        addItemToTable(database,1, "Bakery&Bread", "Donuts");
        addItemToTable(database,2, "Bakery&Bread", "Croissant");
        addItemToTable(database,3, "Dairy", "Yogurt");
        addItemToTable(database,4, "Dairy", "Parmesan Cheese");
        addItemToTable(database,5, "Produce", "Paprika");
        addItemToTable(database,6, "Produce", "Radish");
    }

    // to help the database add a new item to the table "items".
    public void addItemToTable(SQLiteDatabase database, int itemID, String itemType, String itemName) {

        //creating a variable for the database and calling writable method
        // as we are writing data in the database.

        //creating a variable for content values.
        ContentValues values = new ContentValues();

        // passing all values along with its key and value pair.
        values.put(ID_COL, itemID);
        values.put(NAME_COL, itemType);
        values.put(TYPE_COL, itemName);

        //passing values to the table.
        database.insert("items", null, values);
    }

    /*V1
    * this method is used for deleteItem by itemName
    * param: itemName
    * */
    public void deleteItem(SQLiteDatabase database, String TABLE_NAME, String itemName){
        String selection = NAME_COL + "= ? ";
        String[] selectionArgs = {itemName};
        database.delete(TABLE_NAME, selection, selectionArgs);
    }

    /*
    * this method is to help the database delete a item to the table .
    * */
    public void deleteItemById(SQLiteDatabase database, String TABLE_NAME, int itemID){
        // it is equivalent to
        // DELETE FROM TABLE_NAME WHERE ID_COL + "=" + itemID;
        database.delete(TABLE_NAME, ID_COL + "=" + itemID, null);
    }

    /*this method is used for read all items in the database
    * return ArrayList<String> list that contain the information of all items
    * */
    public ArrayList<String> readAllItems(SQLiteDatabase database, String TABLE_NAME){
        //arraylist to store the information of all items
        ArrayList<String> list = new ArrayList<>();
        //select all items
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0) + ". "
                    +cursor.getString(1) + ": "
                    +cursor.getString(2) ; // col 2 is the name of the item
            list.add(name);
        }
        return list;
    }

    /*
    * these methods is for create,drop a list table
    * */
    // to create a list in DB
    public void createListTable(SQLiteDatabase database, String TABLE_NAME){
        database.execSQL("create table" + TABLE_NAME + " ("
                + ID_COL + " INT PRIMARY KEY, "
                + NAME_COL + " TEXT NOT NULL, "
                + TYPE_COL + " TEXT NOT NULL, "
                + QUANTITY_COL + " INT NOT NULL)");
    }

    public void dropListTable(SQLiteDatabase database, String TABLE_NAME){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
