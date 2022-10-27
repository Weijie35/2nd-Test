# Grocery List
## App Design
This design will be of an android app.
1. Our app will have a of items and corresponding item types. To realize this requirement, I added to the design a class itemType with attributes typeId and typeName. Also class item with attributes itemId, name., typeId.
2. Each user will be represented by the class User.
3. Users have the methods to create list or delete list (lists are of the class GroceryList)
4. Each GroceryList will contain sublists which are of class listCategory
5. Each listCategory has attributes listId, typeId, typeName and method to add listItems and a method to check all boxes.
6. listItems are items with the added attributes of quantity and checked. Checked is a boolean attribute where user can know if they have checked the item or not. listItem has a method to edit quantity as well as checkItem, which will toggle checked between True and False.
7. GroceryList class also has methods to rename its own instance. It also has a method to add and delete items from the list. To add items it will search from the database, if not found it will add corresponding item, category to the database. 

Example of a GroceryList with listCategory sublists: 
<b> Weekend List </b>
* Meats
    - [ ] Chicken
    - [ ] Bacon
    - [ ] Rib-eye
* Cereal
    - [ ] Cinnamon Toast Crunch
    - [ ] Corn Flakes

## Not Considered
1. The User Interface (UI) must be intuitive and responsive. Not considered because it does not affect the design directly. 

