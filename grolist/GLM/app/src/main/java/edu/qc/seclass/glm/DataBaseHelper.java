package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.qc.seclass.glm.models.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/** This class is used to communicate with the internal SQLite database */
public class DataBaseHelper extends SQLiteOpenHelper {
    /** Tag name for debugging purposes */
    private static final String TAG = "DataBaseHelper";
    /** Name of database to be created for this android app */
    public static String DATABASE_NAME = "GroceryAPPDB.db";
    /** Version of the database. We will likely not be upgrading */
    private static final int DATABASE_VERSION = 1;

    /* ************************ GroceryList Table information  ************************  */
    /** GroceryList Table name */
    private static final String TABLE_GROCERY_LIST = "GroceryList";
    /** Unique Identifier for GroceryList (PK) */
    private static final String COLUMN_GROCERY_LIST_ID = "groceryListID";
    /** Name of each grocery list to be stored by the List Manager */
    private static final String COLUMN_GROCERY_LIST_NAME = "groceryListName";
    /** SQLite create table statement for groceryList */
    private static final String CREATE_TABLE_GROCERY_LIST = "CREATE TABLE IF NOT EXISTS "
            + TABLE_GROCERY_LIST + "("
            + COLUMN_GROCERY_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_GROCERY_LIST_NAME + " TEXT"
            + ")";

    /* ************************ ItemType Table information  ************************  */
    /** Table Name for ItemType */
    private static final String TABLE_ITEM_TYPE = "ItemType";
    /** Unique Identifier for ItemType (PK) */
    private static final String COLUMN_ITEM_TYPE_ID = "itemTypeID";
    /** Column for name of each item type */
    private static final String COLUMN_ITEM_TYPE_NAME = "itemTypeName";
    /* Create statement for ItemType table */
    private static final String CREATE_TABLE_ITEM_TYPE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ITEM_TYPE + "(" +
            COLUMN_ITEM_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ITEM_TYPE_NAME + " TEXT"
            + ")";


    /* ************************ Item Table information  ************************  */
    /* Table name for Item */
    private static final String TABLE_ITEM = "Item";
    /** Unique Identifier for Item (PK)  */
    private static final String COLUMN_ITEM_ID = "itemID";
    /** Name for each item */
    private static final String COLUMN_ITEM_NAME = "itemName";
    /**Foreign key to item type */
    private static final String COLUMN_ITEM_TYPE_FK = "itemTypeFK";
    /** Create Table statement for Item */
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ITEM + "(" +
            COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ITEM_NAME + " TEXT, "
            + COLUMN_ITEM_TYPE_FK + " REFERENCES " + TABLE_ITEM_TYPE
            + ")";

    /* ************************ GroceryListItem Table information  ************************  */
    /** Table name for Grocery List Item*/
    private static final String TABLE_GROCERY_LIST_ITEM = "GroceryListItem";
    /** Unique identifier for GroceryListItem (PK) */
    private static final String COLUMN_GROCERY_LIST_ITEM_ID = "groceryListItemID";
    /** Binary Property for whether item has been selected by the user */
    private static final String COLUMN_IS_CHECKED = "isChecked";
    /** Column for quantity chosen by the user */
    private static final String COLUMN_ITEM_QUANTITY = "itemQuantity";
    /** Foreign key to GroceryList */
    private static final String COLUMN_GROCERY_LIST_FK = "groceryListFK";
    /** Foreign key to item referenced by the GroceryList Item */
    private static final String COLUMN_ITEM_FK = "itemFK";

    private static final String CREATE_TABLE_GROCERY_LIST_ITEM = "CREATE TABLE IF NOT EXISTS "
            + TABLE_GROCERY_LIST_ITEM + "("
            + COLUMN_GROCERY_LIST_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_IS_CHECKED + " INTEGER DEFAULT 0,"
            + COLUMN_ITEM_QUANTITY + " INTEGER,"
            + COLUMN_GROCERY_LIST_ID + " INTEGER, "
            + COLUMN_ITEM_ID + " INTEGER,"
            + COLUMN_GROCERY_LIST_FK + " REFERENCES " + TABLE_GROCERY_LIST + ","
            + COLUMN_ITEM_FK + " REFERENCES " + TABLE_ITEM + ","
            + COLUMN_ITEM_TYPE_FK + " REFERENCES " + TABLE_ITEM_TYPE + ","
            + COLUMN_ITEM_TYPE_NAME + " TEXT " +
            ")";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     * @param context to use for locating paths to the the database
     */
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON");
        try {
            db.execSQL(CREATE_TABLE_GROCERY_LIST);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            db.execSQL(CREATE_TABLE_ITEM_TYPE);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            db.execSQL(CREATE_TABLE_ITEM);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            db.execSQL(CREATE_TABLE_GROCERY_LIST_ITEM);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_GROCERY_LIST_ITEM + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_ITEM + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_GROCERY_LIST + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_ITEM_TYPE + "'");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * This method creates a grocery list row in the GroceryList table
     * @param newList GroceryListModel object
     * @return true if successfully inserted row and false otherwise
     */
    public boolean createGroceryList(GroceryListModel newList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROCERY_LIST_NAME, newList.getGroceryListName());
        // insert row
        long newGroceryListID = db.insert(TABLE_GROCERY_LIST, null, values);
        newList.setGroceryListID(newGroceryListID);
        return newGroceryListID != -1; //-1 means failed to create
    }


    /** This method returns a GroceryListModel object from the GroceryList Table given a specific id
     * @param groceryListID unique id
     * @return groceryList
     * */
    public GroceryListModel getGroceryList(long groceryListID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GROCERY_LIST + " WHERE "
                + COLUMN_GROCERY_LIST_ID + " = " + groceryListID;

        Cursor c = db.rawQuery(selectQuery, null);

        GroceryListModel groceryList = new GroceryListModel();

        if (c != null) {
            c.moveToFirst();
            groceryList.setGroceryListID(c.getLong(c.getColumnIndex(COLUMN_GROCERY_LIST_ID)));
            groceryList.setGroceryListName(c.getString(c.getColumnIndex(COLUMN_GROCERY_LIST_NAME)));
            c.close();
        }
        return groceryList;
    }


    /** This method returns a list of all GroceryListModel objects from the GroceryList Table
     * @return groceryLists
     * */
    public ArrayList<GroceryListModel> getAllGroceryLists() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GROCERY_LIST;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<GroceryListModel> groceryLists = new ArrayList<>();

        if (c.moveToFirst()){
            do {
                GroceryListModel groceryList = new GroceryListModel();
                groceryList.setGroceryListID(c.getLong(c.getColumnIndex(COLUMN_GROCERY_LIST_ID)));
                groceryList.setGroceryListName(c.getString(c.getColumnIndex(COLUMN_GROCERY_LIST_NAME)));
                groceryLists.add(groceryList);
            } while(c.moveToNext());
        }
        c.close();
        return groceryLists;

    }

    /** This method deletes a row with the specified groceryListID from table Grocery List
     * @param groceryListID unique id
     * @return true if the item was successfully deleted from the database
     * */
    public boolean deleteGroceryList(long groceryListID) {
        deleteAllGroceryListItems(groceryListID);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GROCERY_LIST, COLUMN_GROCERY_LIST_ID + " = ? ",
                new String[] { String.valueOf(groceryListID) }) > 0;
    }


    /** This method deletes every row in the GroceryList Table
     * @return true if all items were successfully deleted from the database */
    public boolean deleteAllGroceryLists() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GROCERY_LIST, null, null) > 0;
    }

    /**
     * This method updates the name of the specified grocery list inside the GroceryList Table
     * @param groceryListID unique id of list to update
     * @param newGroceryListName new Name for GroceryList
     * @return true if the row was successfully updated and false otherwise
     */
    public boolean updateGroceryListName(long groceryListID, String newGroceryListName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_GROCERY_LIST_NAME, newGroceryListName);
        // updating row
        return db.update(TABLE_GROCERY_LIST, values, COLUMN_GROCERY_LIST_ID + " = ? ",
                new String[] { String.valueOf(groceryListID) }) > 0;
    }

    /** This method returns the number of entries in the Grocery List table
     * @return count */
    public int getGroceryListCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_GROCERY_LIST;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            count = c.getInt(0);
        }
        c.close();
        return count;
    }

    /**
     * This method creates a grocery list item object row in the GroceryListItem table
     * @param newGroceryListItem GroceryListItemModel object
     * @return true if successfully inserted row and false otherwise
     */
    public boolean createGroceryListItem(GroceryListItemModel newGroceryListItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_CHECKED, newGroceryListItem.getIsCheckedAsInteger());
        values.put(COLUMN_ITEM_QUANTITY, newGroceryListItem.getItemQuantity());
        values.put(COLUMN_GROCERY_LIST_FK, newGroceryListItem.getGroceryListFK());
        values.put(COLUMN_ITEM_FK, newGroceryListItem.getItemFK());
        values.put(COLUMN_ITEM_TYPE_FK, newGroceryListItem.getItemTypeID());
        values.put(COLUMN_ITEM_TYPE_NAME, newGroceryListItem.getItemTypeName());
        // insert row
        long newGroceryListItemID = db.insert(TABLE_GROCERY_LIST_ITEM, null, values);
        newGroceryListItem.setGroceryListItemID(newGroceryListItemID);
        return newGroceryListItemID != -1; //-1 means failed to create
    }

    /** This method returns a GroceryListItemModel object from the GroceryListItem Table given a specific id
     * @param groceryListItemID unique id
     * @return groceryListItem
     * */
    public GroceryListItemModel getGroceryListItem(long groceryListItemID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GROCERY_LIST_ITEM + " WHERE "
                + COLUMN_GROCERY_LIST_ITEM_ID + " = " + groceryListItemID;

        Cursor c = db.rawQuery(selectQuery, null);

        GroceryListItemModel groceryListItem = new GroceryListItemModel();

        if (c != null) {
            c.moveToFirst();
            groceryListItem.setGroceryListItemID(c.getLong(c.getColumnIndex(COLUMN_GROCERY_LIST_ITEM_ID)));
            groceryListItem.setIsCheckedFromInteger(c.getInt(c.getColumnIndex(COLUMN_IS_CHECKED)));
            groceryListItem.setItemQuantity(c.getInt(c.getColumnIndex(COLUMN_ITEM_QUANTITY)));
            groceryListItem.setGroceryListFK(c.getLong(c.getColumnIndex(COLUMN_GROCERY_LIST_FK)));
            groceryListItem.setItemFK(c.getLong(c.getColumnIndex(COLUMN_ITEM_FK)));
            groceryListItem.setItemTypeID(c.getLong(c.getColumnIndex(COLUMN_ITEM_TYPE_FK)));
            groceryListItem.setItemTypeName(c.getString(c.getColumnIndex(COLUMN_ITEM_TYPE_NAME)));
            c.close();
        }
        return groceryListItem;
    }

    /** This method returns a list of all GroceryListItemModel objects from the GroceryListItem Table
     * @param groceryListFK id of grocery list that the items belong to
     * @return GroceryListItems
     * */
    public ArrayList<GroceryListItemModel> getAllGroceryListItems(long groceryListFK) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GROCERY_LIST_ITEM + " WHERE " + COLUMN_GROCERY_LIST_FK + " = " + groceryListFK ;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<GroceryListItemModel> groceryListItems = new ArrayList<>();

        if (c.moveToFirst()){
            do {
                GroceryListItemModel groceryListItem = new GroceryListItemModel();
                groceryListItem.setGroceryListItemID(c.getLong(c.getColumnIndex(COLUMN_GROCERY_LIST_ITEM_ID)));
                groceryListItem.setIsCheckedFromInteger(c.getInt(c.getColumnIndex(COLUMN_IS_CHECKED)));
                groceryListItem.setItemQuantity(c.getInt(c.getColumnIndex(COLUMN_ITEM_QUANTITY)));
                groceryListItem.setGroceryListFK(c.getLong(c.getColumnIndex(COLUMN_GROCERY_LIST_FK)));
                groceryListItem.setItemFK(c.getLong(c.getColumnIndex(COLUMN_ITEM_FK)));
                groceryListItem.setItemName(getItem(c.getLong(c.getColumnIndex(COLUMN_ITEM_FK))).getItemName());
                groceryListItem.setItemTypeID(getItem(c.getLong(c.getColumnIndex(COLUMN_ITEM_TYPE_FK))).getItemTypeFK());
                groceryListItem.setItemTypeName(c.getString(c.getColumnIndex(COLUMN_ITEM_TYPE_NAME)));
                groceryListItems.add(groceryListItem);
            } while(c.moveToNext());
        }
        c.close();
        return groceryListItems;
    }


    /** This method deletes a row with the specified groceryListItemID
     * @param groceryListItemID unique id
     * @return true if the item was successfully deleted from the database
     * */
    public boolean deleteGroceryListItem(long groceryListItemID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GROCERY_LIST_ITEM, COLUMN_GROCERY_LIST_ITEM_ID + " = ? ",
                new String[] { String.valueOf(groceryListItemID) }) > 0;
    }

    /** This method deletes every row in the GroceryListItem Table
     * @param groceryListFK Foreign to delete lists from
     * @return true if all rows were successfully deleted from the database */
    public boolean deleteAllGroceryListItems(long groceryListFK) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GROCERY_LIST_ITEM, COLUMN_GROCERY_LIST_FK + " = " + Long.toString(groceryListFK), null) > 0;
    }


    /** This method returns the number of entries in the Grocery List Item table
     * @return count */
    public int getGroceryListItemCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_GROCERY_LIST_ITEM;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            count = c.getInt(0);
        }
        c.close();
        return count;
    }


    /** This method updates the item quantity of a row in a specific list
     * @param groceryListItemID specific id of item in grocery list
     * @param newItemQuantity item quantity specified by user*/
    public boolean updateGroceryListItemQuantity(long groceryListItemID, int newItemQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_QUANTITY, newItemQuantity);

        // updating row
        return db.update(TABLE_GROCERY_LIST_ITEM, values, COLUMN_GROCERY_LIST_ITEM_ID + " = ? " ,
                new String[] { String.valueOf(groceryListItemID) }) > 0;
    }

    public boolean updateGroceryListItemIsChecked(long groceryListItemID, boolean isChecked)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(isChecked) {
            values.put(COLUMN_IS_CHECKED, 1);
        }
        else
            values.put(COLUMN_IS_CHECKED, 0);

        // updating row
        return db.update(TABLE_GROCERY_LIST_ITEM, values, COLUMN_GROCERY_LIST_ITEM_ID + " = ? " ,
                new String[] { String.valueOf(groceryListItemID) }) > 0;
    }

    /**
     * This method creates an ItemType row in the ItemType table
     * @param newItemType ItemTypeModel object
     * @return true if successfully inserted row and false otherwise
     */
    public boolean createItemType(ItemTypeModel newItemType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_TYPE_NAME, newItemType.getItemTypeName());
        // insert row
        long newItemTypeID = db.insert(TABLE_ITEM_TYPE, null, values);
        newItemType.setItemTypeID(newItemTypeID);
        return newItemTypeID != -1; //-1 means failed to create
    }

    /** This method returns a ItemTypeModel object from the ItemType Table given a specific id
     * @param itemTypeID unique id
     * @return itemType
     * */
    public ItemTypeModel getItemType (long itemTypeID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEM_TYPE + " WHERE "
                + COLUMN_ITEM_TYPE_ID + " = " + itemTypeID;

        Cursor c = db.rawQuery(selectQuery, null);

        ItemTypeModel itemType = new ItemTypeModel();

        if (c != null) {
            c.moveToFirst();
            itemType.setItemTypeID(c.getLong(c.getColumnIndex(COLUMN_ITEM_TYPE_ID)));
            itemType.setItemTypeName(c.getString(c.getColumnIndex(COLUMN_ITEM_TYPE_NAME)));
            c.close();
        }
        return itemType;
    }

    /** This method returns a list of all ItemTypeModel objects from the ItemType Table
     * @return itemTypes
     * */
    public ArrayList<ItemTypeModel> getAllItemTypes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEM_TYPE;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<ItemTypeModel> itemTypes = new ArrayList<>();

        if (c.moveToFirst()){
            do {
                ItemTypeModel itemType = new ItemTypeModel();
                itemType.setItemTypeID(c.getLong(c.getColumnIndex(COLUMN_ITEM_TYPE_ID)));
                itemType.setItemTypeName(c.getString(c.getColumnIndex(COLUMN_ITEM_TYPE_NAME)));
                itemTypes.add(itemType);
            } while(c.moveToNext());
        }
        c.close();
        return itemTypes;
    }

    /** This method returns the number of entries in the ItemType table
     * @return count */
    public int getItemTypeCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_ITEM_TYPE;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            count = c.getInt(0);
        }
        c.close();
        return count;
    }


    /** This method first checks if the item count in the database is 0
     * If so, it generates 5 default ItemTypes and three items corresponding to an item type
     * The item types and items are then inserted into the database*/
    public void createDefaultItemTypesAndItems() {
        if (getItemTypeCount() == 0) {

            /* Create item types */
            String[] defaultItemTypes = {"Dairy", "Meat", "Frozen", "Fish/Seafood", "Produce"};

            /* Create three items for each item type */
            String[] defaultItemsDairy = {"Cheese", "Yogurt", "Milk"};
            String[] defaultItemsMeat = {"Beef", "Chicken", "Pork"};
            String[] defaultItemsFrozen = {"Pizza", "FrozenVeggies", "HungryMan"};
            String[] defaultItemsFishSeafood = {"Tilapia", "Shrimp", "Salmon"};
            String[] defaultItemsProduce = {"Apples", "Oranges", "Green Beans"};


            /* Generate an itemType model for each default item and store in the database */
            for (String itemType: defaultItemTypes) {
                createItemType(new ItemTypeModel(itemType));
            }

            /* Retrieve all itemTypes just inserted to the database  */
            ArrayList<ItemTypeModel> allCategories = getAllItemTypes();


            /* For each ItemTypeModel in the allCategories arraylist, check the category name and
            * use addAllItems() helper to insert the items into the database */
            for (ItemTypeModel category:allCategories) {
                switch (category.getItemTypeName())
                {
                    case "Dairy":
                        addAllItems(defaultItemsDairy, category.getItemTypeID());
                        break;
                    case "Meat":
                        addAllItems(defaultItemsMeat, category.getItemTypeID());
                        break;
                    case "Frozen":
                        addAllItems(defaultItemsFrozen, category.getItemTypeID());
                        break;
                    case "Fish/Seafood":
                        addAllItems(defaultItemsFishSeafood, category.getItemTypeID());
                        break;
                    case "Produce":
                        addAllItems(defaultItemsProduce, category.getItemTypeID());
                        break;
                }
            }
        }
    }


    /** This helper method is used to insert all items passed as a String array into the database
     * using createItem()
     * @param defaultItems String array of item names
     * @param itemTypeFK long foreign key*/
    private void addAllItems(String[] defaultItems, long itemTypeFK) {
        for (String defaultItem : defaultItems) {
            createItem(new ItemModel(defaultItem, itemTypeFK));
        }
    }



    /**
     * This method creates a new ItemModel object in the Item table
     * @param newItem ItemModel object
     * @return true if successfully inserted row and false otherwise
     */
    public boolean createItem(ItemModel newItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, newItem.getItemName());
        values.put(COLUMN_ITEM_TYPE_FK, newItem.getItemTypeFK());
        // insert row
        long newItemID = db.insert(TABLE_ITEM, null, values);
        newItem.setItemID(newItemID);
        return newItemID != -1; //-1 means failed to create
    }

    /** This method returns a ItemModel object from the GroceryList Table given a specific id
     * @param itemID unique id
     * @return item
     * */
    public ItemModel getItem(long itemID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEM + " WHERE "
                + COLUMN_ITEM_ID + " = " + itemID;

        Cursor c = db.rawQuery(selectQuery, null);

        ItemModel item = new ItemModel();

        if (c != null) {
            c.moveToFirst();
            item.setItemID(c.getLong(c.getColumnIndex(COLUMN_ITEM_ID)));
            item.setItemName(c.getString(c.getColumnIndex(COLUMN_ITEM_NAME)));
            item.setItemTypeFK(c.getLong(c.getColumnIndex(COLUMN_ITEM_TYPE_FK)));
            c.close();
        }
        return item;
    }

    /** This method returns a list of all ItemModel objects that belong to a certain itemType
     * @param itemTypeFK foreign key to type that that this set of items belongs to
     * @return items
     * */
    public ArrayList<ItemModel> getItemsByCategory(long itemTypeFK) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM + " WHERE " + COLUMN_ITEM_TYPE_FK + " = " + itemTypeFK;
        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<ItemModel> items = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                ItemModel item = new ItemModel();
                item.setItemID(c.getLong(c.getColumnIndex(COLUMN_ITEM_ID)));
                item.setItemName(c.getString(c.getColumnIndex(COLUMN_ITEM_NAME)));
                item.setItemTypeFK(c.getLong(c.getColumnIndex(COLUMN_ITEM_TYPE_FK)));
                items.add(item);
            } while(c.moveToNext());
        }
        c.close();
        return items;
    }

    /** This method returns the number of entries in the Item table
     * @return count */
    public int getItemCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_ITEM;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            count = c.getInt(0);
        }
        c.close();
        return count;
    }

    /** This method is used to close the database */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}


