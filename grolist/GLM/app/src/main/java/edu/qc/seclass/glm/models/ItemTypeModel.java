package edu.qc.seclass.glm.models;

import java.util.ArrayList;

/** This class is used to communicated between the UI and the ItemType table in the database */
public class ItemTypeModel {
    /** Unique identifier to row in ItemType table */
    private long itemTypeID;
    /** Name of ItemType */
    private String itemTypeName;

    public ArrayList<ItemModel> itemModelArrayList;

    /* *******Constructors********/
    /** Default constructor for ItemTypeModel */
    public ItemTypeModel() {
        this.itemModelArrayList = new ArrayList<>();
    }

    /** This constructor initializes the itemTypeName
     * @param itemTypeName String */
    public ItemTypeModel(String itemTypeName) {
        this.itemTypeName = itemTypeName;
        this.itemModelArrayList = new ArrayList<>();
    }

    /* *******Getters********/
    /** This is a getter method for the itemTypeID
     * @return itemTypeID */
    public long getItemTypeID() {
        return itemTypeID;
    }
    /** Getter method for the arrayList of items that belong to this item type
     * @return itemModelArrayList */
    public ArrayList<ItemModel> getItemModelArrayList() {
        return itemModelArrayList;
    }

    /** This is a getter method for the ItemTypeName
     * @return itemTypeName */
    public String getItemTypeName() {
        return itemTypeName;
    }

    /* *******Setters********/
    /** This is a setter method for the itemTypeID
     * @param itemTypeID long */
    public void setItemTypeID(long itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    /** This is a setter method for the itemTypeName
     * @param itemTypeName String */
    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    /** Setter method for ItemModelArrayList
     * @param itemModelArrayList list of item models */
    public void setItemModelArrayList(ArrayList<ItemModel> itemModelArrayList) {
        this.itemModelArrayList = itemModelArrayList;
    }
     /** toString() method for the ItemTypeModel class */
    @Override
    public String toString() {
        return "ItemTypeModel{" +
                "itemTypeID=" + itemTypeID +
                ", itemTypeName='" + itemTypeName + '\'' +
                '}';
    }
}

