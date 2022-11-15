package edu.qc.seclass.glm.models;

/** Class for communication between UI and GroceryListItem Table in the database */
public class GroceryListItemModel {
    /** Unique id for GroceryListItem */
    private long groceryListItemID;
    /** Item name */
    private String itemName;
    /** Integer value for checked property since the SQLite Database does not have the boolean datatype */
    private int isCheckedInteger;
    /** Item Quantity chosen by the user */
    private int itemQuantity;
    /** Unique foreign key for GroceryList that the GroceryListItem belongs to */
    private long groceryListFK;
    /** Unique foreign key for Item that GroceryListItem references */
    private long itemFK;
    /** Unique foreign key for ItemTypeFK that GroceryListItem references */
    private long itemTypeID;
    /** Unique foreign key for ItemTypeName that GroceryListItem references */
    private String itemTypeName;


    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }



    public GroceryListItemModel(long groceryListItemID, String itemName, int isCheckedInteger, int itemQuantity, long groceryListFK, long itemFK, long itemTypeID, String itemTypeName) {
        this.groceryListItemID = groceryListItemID;
        this.itemName = itemName;
        this.isCheckedInteger = isCheckedInteger;
        this.itemQuantity = itemQuantity;
        this.groceryListFK = groceryListFK;
        this.itemFK = itemFK;
        this.itemTypeID = itemTypeID;
        this.itemTypeName = itemTypeName;
    }





    /* ********************Constructors*********************/
    /** Constructor for GroceryListItemModel
     * @param isChecked whether item has been marked
     * @param itemQuantity integer quantity chosen by the user
     * @param groceryListFK unique key of GroceryList that the GroceryListItem belongs to
     * @param itemFK unique key of item that was selected for this GroceryListItem
     * @param itemTypeID unique key of itemtype that was selected for this GroceryListItem  */
    public GroceryListItemModel(boolean isChecked, int itemQuantity, long groceryListFK, long itemFK, long itemTypeID) {
        isCheckedInteger = (isChecked) ? 1: 0;
        this.itemQuantity = itemQuantity;
        this.groceryListFK = groceryListFK;
        this.itemFK = itemFK;
        this.itemTypeID = itemTypeID;
    }

    /** Default constructor for GroceryListItem Model */
    public GroceryListItemModel() {
    }
    /* ********************Getters*********************/

    /** Getter method for GroceryListItemID
     * @return groceryListItemID
     * */
    public long getGroceryListItemID() {
        return groceryListItemID;
    }


    /** Getter method for isCheckInteger as boolean. Converts integer value to boolean
     * @return isCheckedInteger converted to boolean data type */
    public boolean getIsCheckedAsBoolean() {
        return isCheckedInteger == 1;
    }

    /** Getter method for isCheckedAs Integer
     * @return int value */
    public int getIsCheckedAsInteger() {
        return isCheckedInteger;
    }

    /** Getter method for itemQuantity
     * @return itemQuantity */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /** Getter method for groceryListFK
     * @return groceryListFK */
    public long getGroceryListFK() {
        return groceryListFK;
    }

    /** Getter method for itemFK
     * @return itemFK */
    public long getItemFK() {
        return itemFK;
    }

    /** Getter method for itemName
     * @return itemName */
    public String getItemName() {
        return itemName;
    }

    public long getItemTypeID() {
        return itemTypeID;
    }

    /* ********************Setters*********************/
    /** setter method for groceryListItemID
     * @param groceryListItemID unique id */
    public void setGroceryListItemID(long groceryListItemID) {
        this.groceryListItemID = groceryListItemID;
    }


    /** setter method for isCheckedInteger
     * @param isCheckedInteger integer val */
    public void setIsCheckedFromInteger(int isCheckedInteger) {
        this.isCheckedInteger = isCheckedInteger;
    }

    /** setter method for isCheckedInteger
     * @param booleanValue boolean val */
    public void setIsCheckedFromBoolean(boolean booleanValue) {
        isCheckedInteger = (booleanValue)? 1: 0;
    }

    /** setter method for isCheckedInteger
     * @param itemQuantity integer val for itemQuantity */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /** Setter method for groceryListFK
     * @param groceryListFK long value */
    public void setGroceryListFK(long groceryListFK) {
        this.groceryListFK = groceryListFK;
    }

    /** Setter method for groceryListFK
     * @param itemFK long value */
    public void setItemFK(long itemFK) {
        this.itemFK = itemFK;
    }

    /** Setter method for itemName
     * @param itemName String */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemTypeID(long itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    /** To String method */
    @Override
    public String toString() {
        return "GroceryListItemModel{" +
                "groceryListItemID=" + groceryListItemID +
                ", itemName='" + itemName + '\'' +
                ", isCheckedInteger=" + isCheckedInteger +
                ", itemQuantity=" + itemQuantity +
                ", groceryListFK=" + groceryListFK +
                ", itemFK=" + itemFK +
                ", itemTypeFK=" + itemTypeID +
                ", itemTypeName=" + itemTypeName +
                '}';
    }
}
