package edu.qc.seclass.glm.models;
import java.util.ArrayList;
/** This class is used to connect the UI to the GroceryList Table in the database */
public class GroceryListModel {
    /** ID for grocerList row */
    private long groceryListID;
    /** Name of GroceryList */
    private String groceryListName;
    /** List of all items that belong to this specific groceryList */
    private ArrayList<GroceryListItemModel> groceryListItemModelArrayList;
    /* ********************Constructors*********************/
    /** Default constructor for GroceryListItemModel tha initializes a new ArrayList of GroceryListItems */
    public GroceryListModel() {
        groceryListItemModelArrayList = new ArrayList<>();
    }

    /** Default constructor for GroceryListItemModel that initializes a new ArrayList of GroceryListItems
     * and the name of the GroceryList
     * @param groceryListName String*/
    public GroceryListModel(String groceryListName) {
        this.groceryListName = groceryListName;
        groceryListItemModelArrayList = new ArrayList<>();
    }

    /* ********************Getters*********************/
    /** Getter method for groceryListID
     * @return groceryListID */
    public long getGroceryListID() {
        return groceryListID;
    }

    /** Getter method for GroceryListName
     * @return groceryListName */
    public String getGroceryListName() {
        return groceryListName;
    }

    /** Getter method for GroceryListItemModelArrayList
     * @return arraylist of GroceryList Items */
    public ArrayList<GroceryListItemModel> getGroceryListItemModelArrayList() {
        return groceryListItemModelArrayList;
    }

    /* ********************Setters*********************/
    /** Setter method for groceryListID
     * @param groceryListID unique id*/
    public void setGroceryListID(long groceryListID) {
        this.groceryListID = groceryListID;
    }

    /** Setter method for groceryListName
     * @param groceryListName String */
    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    /** Setter method for ArrayList of GroceryList Items
     * @param groceryListItemModelArrayList ArrayList of GroceryListItemModel objects */
    public void setGroceryListItemModelArrayList(ArrayList<GroceryListItemModel> groceryListItemModelArrayList) {
        this.groceryListItemModelArrayList = groceryListItemModelArrayList;
    }

    /** toString() method for this class
     * @return String */
    @Override
    public String toString() {
        return "GroceryListModel{" +
                "groceryListID=" + groceryListID +
                ", groceryListName='" + groceryListName + '\'' +
                '}';
    }
}
