1. A grocery list consists of items the users want to buy at a grocery store. The application
   must allow users to add items to a list, delete items from a list, and change the quantity
   of items in the list (e.g., change from one to two pounds of apples).
   - The class groceryItems will access the database when the user searches for an item to add to the class groceryLists class.  
2. The application must contain a database (DB) of items and corresponding item types.
   - Database is  named "Database for groceryList"
3. Users must be able to add items to a list by picking them from a hierarchical list, where
  the first level is the item type (e.g., cereal), and the second level is the name of the
  actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
  quantity for that item.
  - This functionality is in the GroceryItems class.
4. Users must also be able to specify an item by typing its name. In this case, the
  application must look in its DB for items with similar names and ask the users, for each
  of them, whether that is the item they intended to add. If a match cannot be found, the
  application must ask the user to select a type for the item and then save the new item,
  together with its type, in its DB.
  - Specified in the GroceryItem class, there is a itemsearch method to search database.
5. Lists must be saved automatically and immediately after they are modified.
   - lists created are saved by myGroceryList class.
6. Users must be able to check off items in a list (without deleting them).
   - this functionality is in the GroceryLists class. "checkoff".
7. Users must also be able to clear all the check-off marks in a list at once.
   - This functionality is in the GroceryLists class "clearcheckoff".
8. Check-off marks for a list are persistent and must also be saved immediately.
   - since there is no deletion calls or edits done to the list, the status of "checkoffs" are saved.
9. The application must present the items in a list grouped by type, so as to allow users to
  shop for a specific type of product at once (i.e., without having to go back and forth
  between aisles).
  - This functionality is in the groceryLists class. The user is able to add multiple lists that are kept track of. There are methods that groceryLists has to repeat lists already created to give the user frequency option in the RepeatGroceryList enumerable.
10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
    farmer’s market list”). Therefore, the application must provide the users with the ability to
    create, (re)name, select, and delete lists.
    - List creation methods are in myGroceryList class and the ability to add multiple lists are in the GroceryLists class.