# Idea of the Requirement 
1. A `grocery list` consists of `items` the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list.

    - add a class `list` with attributes `name` that stores the name of the list.
    - add methods in the class `list`, addItem() that adds items to a list, and deleteItem() that deletes items from a list. 
    - add a class `item` with attributes `name`(the name of a item), `type`(the type of a item), `quantity` (that stores the number of each item).  
    - changeQuantity() that changes the quantity of items in list,

2. The application must contain a `database` (DB) of items and corresponding item types.
    - add a class `storage` with attributes `itemsName`, `itemsType` as a database of items 

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the `item type` (e.g., cereal), and the second level is the `name of the actual item` (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.
    - add an interface `Query` which provides a method `searchType()` that finds all item under a particular type.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
    - add new method `searchName()` that specifies a item by its relevant name for the interface `Query`
    - if a match cannot be found, add a new item, I add a method `newItem()` that adds a new `item` for the class `storage`

5. Lists must be saved automatically and immediately after they are modified.
    - it is not a main function for the user

6. Users must be able to check off items in a list (without deleting them).
    - add a new boolean attribute the `check_off` state for the item

7. Users must also be able to clear all the check-off marks in a list at once.
    - add a method `clearMarks()` 

8. Check-off marks for a list are persistent and must also be saved immediately.
    - just a state of a item

9. The application must present `the items in a list grouped by type`, so as to allow users to shop for a specific type of product at once (i.e., without having to go back and forth between aisles).
    - add a method `groupByGroup()` that presents the items in a list grouped by type.

10. The application must support `multiple lists` at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to `create`, `(re)name`, `select`, and `delete` lists.
    - create a class `user` to store multiple lists.
    - add methods `createList()` that creates a list, `reName()` that renames a list, `deleteList()` that deletes a list.

11. The User Interface (UI) must be intuitive and responsive.
    - Not considered because now we focus on the system.