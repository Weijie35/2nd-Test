package edu.qc.seclass.glm.models;

import java.util.ArrayList;

/** This class is used to communicate between the UI and the Item Table in the database */
public class ItemModel {
    /** Unique identifier for row in Item Table */
    private long itemID;
    /** Name of item */
    private String itemName;
    /** Foreign key for itemType  */
    private long itemTypeFK;


    /* ********************Constructors*********************/
    /** Constructor specifies both the foreign key for item type and the foreign key to its item type  */
    public ItemModel(String itemName, long itemTypeFK) {
        this.itemName = itemName;
        this.itemTypeFK = itemTypeFK;
    }

    /** Default constructor or ItemModel */
    public ItemModel() {
    }
    /* ********************Getters*********************/
    /** Getter method for the itemID
     * @return itemID */
    public long getItemID() {
        return itemID;
    }

    /** Getter method for the ItemName
     * @return itemName */
    public String getItemName() {
        return itemName;
    }

    /** Getter method for the itemType Foreign Key
     * @return itemTypeFK */
    public long getItemTypeFK() {
        return itemTypeFK;
    }
    /* ********************Setters*********************/
    /** Setter method for the itemID
     * @param itemID Long */
    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    /** Setter method for the itemName
     * @param itemName String */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** Setter method for the itemTypeForeign key
     * @param itemTypeFK Long*/
    public void setItemTypeFK(long itemTypeFK) {
        this.itemTypeFK = itemTypeFK;
    }

    /** toString() method for the Item class */
    @Override
    public String toString() {
        return "ItemModel{" +
                "itemID=" + itemID +
                ", itemName=" + itemName +
                ", itemTypeID=" + itemTypeFK +
                '}';
    }
}
