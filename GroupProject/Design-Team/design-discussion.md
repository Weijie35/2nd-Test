# Design-Discussion
### Design 1
  * Weijie
  * Pros:
    * has two main classes, user(or myList) for storing lists and list for storing items in a list. 
    * add associations between list, item, and database
    * provides an interface to access the database
  * Cons:
    * without the chechoff() method in list, the deleteItem() in storage.
    * The Search(query) interface doesn't have to be two methods. The method groupByType() is not necessary(it should be included in Search interface).
### Design 2
  * Amarjit Singh 
  * Pros:
    1. Classes and its operation are self contained.
    2. Associations are well defined.
  * Cons:
    1. Some namings are not very clear.
    2. Missed some operations on GroceryList to check or checkall an item.
### Design 3
   * David Ortega
   * Pros:
     * Organized design so that it's easy to follow
     * Included appropriate relationship between classes
     * Included classes, attributes, and a database to satisfy basic requirements  
   * Cons:
     * Some attributes were condensed into to few classes, an interface was missing in the design to help manage this.
### Design 4
  * Hassan Kazi 
  * Pros:
    1. Associations, Compositions, and Aggregations are distinct 
    2. Many to many, one to many relationships are displayed
    3. Classes that access to database are shown
  * Cons:
    1. User creation is vague, no function to create User
    2. CheckAll() method in two different classes leads to confusion

## Team Design
  * We have simplified the UML design to clearly show associations and aggregations between classes.
  * We have separated methods that belong only to the database.
  * Main distinction between our designs was a User Class which identified the user of the app, we have agreed on a myGroceryList class to define this.
  * Our designs had commonality among GroceryLists, GroceryItem, GroceryItemType(category) so have kept that consistent in our main design.
  * We removed unecessary or ambiguous methods and attributes from individual designs like checkAll(), groupByType() , added new methods like deleteItem()
## Summary
We were able to create this team design by communicating and forming ideas as a group. Then made team decisions on the design which inherently led to compromises on our final design. Through this D1 assignment, we were able to come together and correct each others mistakes and improve our understanding of this project. Since previous projects were personal, we were able to strengthen communication and coordination with our peers. In this cooperation, we realized the necessity of cooperation at work. This necessary communication can improve work efficiency and unify project objectives. We also learned that we should provide reasonable time for meeting together to make sure every group member can contribute to the final outcome.
