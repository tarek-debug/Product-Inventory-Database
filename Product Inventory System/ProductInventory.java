/**
 * Create an inventory positinal list that stores Products objects in it.
 * @ Author Tarek Solamy (Alsolame)
 * @version 1.0 3/28/2022
 */

import java.util.*;

public class ProductInventory {

  private String companyName;
  private PositionalList<Product> inventory;
  private Product product;
  private Deque<Product> topSearchedProducts;
  /**
  * Creates a new products positional list.
  *@param CompanyName which is the name of the company that will be using this postional list invenotry
  */
  public ProductInventory (String companyName){
  this.companyName=companyName;
  product= new Product();                                   // creates a new product
  inventory= new LinkedPositionalList<Product>();           // creates a new positional list of the invenotry
  topSearchedProducts= new DoublyLinkedList<Product>();     // creates a doublylinkedlist Deque
  }
  /**
  * insert a product to the inventory positinal list.
  *@ param  add which is the product to be added
  *@ returns if products already exists.
  *Time Complexity: O(n)
  */
  public void insert(Product add){
        Iterator <Product> iterate= inventory.iterator();                  // iterator of the positional list is created
         while (iterate.hasNext()){
         if(add.compareTo(iterate.next())==0){                            // if the product exists in the list ( using comparable method)...
           System.out.println("This product already exist.");             // it will not be added
           return;
         }
       }
     inventory.addFirst(add);                                           // if the product doesn't exist, it will be added to the list
    }
    /**
    * remove a product from the inventory positinal list.
    *@ param  productNumber which is the unique number of the product that will be used to remove it
    *@ returns if product doesn't exist or if inventory is empty
    * Time Complexity: O(n)
    */
  public void remove(int productNumber){
    Product locate= new Product();                                      // new product is created...
    locate.setProductNumber(productNumber);                             // the product number is assigned to it
    if (inventory.isEmpty()){
      System.out.println("The inventory is currenlty empty.");          // if invenotry is empty, then product to be removed doesn't exist
      return;
    }
    else{
      Iterator <Product> iterate= inventory.iterator();
       while (iterate.hasNext()){
       if(locate.compareTo(iterate.next())==0){                        // if the product exists in the list ( using comparable method)...
         iterate.remove();                                             // it will be removed
         return;
       }
     }
     System.out.println("This product doesn't exist.");                 // after checking the list, if product doesn't exist
   }
 }
 /**
 * helper method to sort the inventory positinal list by their product number.
 * book : Data Structures and Algrothims in java
 * page 293
 *Time Complexity: O(n^2)
 */
  private void sort(PositionalList<Product> inventory){
    Position <Product> marker=inventory.first();    // last postion known to be sorted
    while(marker != inventory.last()){
      Position<Product> pivot = inventory.after(marker);
      int value = pivot.getElement().getProductNumber();      // product number to be placed
     if (value > marker.getElement().getProductNumber()){     // pivot is already sorted
       marker=pivot;
     } else {                                                 // must relocate pivot
        Position<Product> walk= marker;                       // find leftmost item greater than value
        while (walk != inventory.first( ) && ((inventory.before(walk).getElement().getProductNumber()) > value))
          walk= inventory.before(walk);
        Product piv= pivot.getElement();
        inventory.remove(pivot);                          // remove pivot entry and
        inventory.addBefore(walk,piv);                        // reinsert value in front walk
     }
    }
    }
    /**
    * Display all the products in the positional list
    *Time Complexity: O(n^2)
    */
    public void display(){
      sort(inventory);                                // the invenotry positinal list sorted
      System.out.println(toString());                 // it is printed by calling out the "toString method"
    }
    /**
    * Convert the inventory positinal list into a string which will be used in display method
    *@ returns sb, which is the string format of the posintal list
    *Time Complexity: O(n)
    */
    @Override
    public String toString(){
      StringBuilder sb = new StringBuilder("");
      if (inventory.isEmpty()){
        sb.append("Inventory is empty.");           // append this statement to sb
      }
      else if(inventory.size()==1){                 // if the list only has one element...
        sb.append(inventory.first().getElement());  // add the first element to the sb
      }
      else if (inventory.size()>1){                 // if the size is bigger than one...
        Iterator <Product> iterate= inventory.iterator();   // iterator of the invenotry is created
        while (iterate.hasNext()){
            sb.append(iterate.next());                    // while the iterator has more elements add them to the list
         }
         }
      return sb.toString();                       // String builder is converted to string
    }
    /**
    * Display available products, total number of products in the inventory and total number of available products.
    *Time Complexity: O(n)
    */
    public void displayAvailable(){
      sort(inventory);                          // the list is sorted
      int countAvailable=0;                     // this will count all the available products in the list.
      if (inventory.isEmpty()){
        System.out.println("The inventory is currently empty.");      // if list is empty
      } else {
        Iterator <Product> iterate= inventory.iterator();   // iterator of the invenotry is created
        while (iterate.hasNext()){
          Product check= iterate.next();
          if(check.getProductAvailabilty()>0){
           System.out.println(check);            // if product has availablilty more than zero, it will be printed
           ++countAvailable;                              // this counter variable will increase
      }
    }
        System.out.println("Total number of all products in the inventory: " + inventory.size());
        System.out.println("Total number of available products in the inventory: " + countAvailable);
    }
  }
  /**
  * Finds a product in the inventory list.
  *@ param ProductNumber, the unique number used to locate the product associated with it.
  *@ returns if inventory is empty or if the product is found.
  *Time Complexity: O(n)
  */
    public void find(int productNumber){
      Product locate= new Product();                        // new product is created...
      locate.setProductNumber(productNumber);              // the product number is assigned to it
      if (inventory.isEmpty()){
        System.out.println("The inventory is currenlty empty."); // if invenotry is empty, then product to be found doesn't exist
        return;
      } else{
        Iterator <Product> iterate= inventory.iterator();
       while (iterate.hasNext()){
         Product found=iterate.next();
         if(locate.compareTo(found)==0){                // if the product to be located and the product to be found has the same product number...
           System.out.println(found);                   // it will print out the product
           topSearchedProducts.addFirst(found);         // this will be will to the topSearchedProducts Deque
           trimTopSearchedDeque();                      // this method will be used to keep the Deque elements equal to 5
           return;
         }
       }
       System.out.println("The product you are trying to find is not in the inventory.");
      }

    }
    /**
    * Helper method to trim the topSearchedProducts list if it becomes more than 5 to avoid overloading.
    *Time Complexity: O(n)
    */
    private Deque<Product> trimTopSearchedDeque(){
        while (topSearchedProducts.size()>5){
          topSearchedProducts.removeLast();       // everytime the Deque size is more than 5, the last element of the deque will be removed until it reaches 5
        }
        return topSearchedProducts;
    }
    /**
    * Evaluates a postfix expression expr.
    * @ returns a string that products has not been searched yet if the topSearchedProducts is empty
    *@ returns a string format of the topSearchedProducts list.
    *Time Complexity: O(n)
    */
    public String topSearched(){
        LinkedStack <Product> helper= new LinkedStack <Product>();    // temporary stack is created
        if (topSearchedProducts.isEmpty()){
          return "No products has been searched yet.";                // if topSearchedProducts list is empty, it returns no products has been searched
        }
        while (!topSearchedProducts.isEmpty()){
          helper.push(topSearchedProducts.removeLast());             // topSearchedProducts list is not empty, add the last element to stack and removes
        }
        StringBuilder sb = new StringBuilder("(");                   // creates a string builder
        while (!helper.isEmpty()){
          sb.append(helper.top().getProductName());                   // append the product information to sb
          if  (helper.size()>1){                                      // if helper size is bigger than 1
            sb.append(",");                                           // add comma to sb
          }
          topSearchedProducts.addLast(helper.pop());                  // add the product element back to the topSearchedProducts list
        }
          sb.append(")");
        return sb.toString();                                           // return the string format of sb
    }
    /**
    * clears the screan after the user perfored the operation is done and they decided to either continue or quit.
    * Sources: Sources: https://stackoverflow.com/questions/2979383/how-to-clear-the-console
    * https://www.youtube.com/watch?v=Knl20uhL1B0&ab_channel=UbuntuTricks
    *Time Complexity: O(n)
    */
    public final static void clearScreen(){
      try {
      final String os= System.getProperty("os.name");       //Gets the system property indicated by the specified key
      if(os.contains("Windows")){                           // if the os is Windows
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor(); // it will created ProcessBuilder to clear the console
      } else{
        //Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");                 // this will clear the screen from the cursor to the end of the screen
        System.out.flush();                                // this empties to the content of the nuffer
      }
      }
      catch (final Exception e){}                         // this will catch any excpetions that might occur
    }


    /*
    * this method create a user interface menue that asks the user which operation to use and let them exit or continue after finishing their operation
    */
    public static void menu (){
      int operationToChoose, number;
      Scanner scan= new Scanner(System.in).useDelimiter("\n");
      String option="";
      System.out.print("Welcome to the inventory Program, please enter your company's name:");
      String companyName= scan.nextLine();          //topSearchedProducts.removeLast();

      ProductInventory inv= new ProductInventory(companyName);        // new inventory postional list
      do {                                                            // menu is created
          System.out.println("Which operation would you like to perform?");
          System.out.println("To add a product, insert 1");
          System.out.println("To remove a product, insert 2");
          System.out.println("To display all products, insert 3");
          System.out.println("To display available products, insert 4");
          System.out.println("To find a product, insert 5");
          System.out.println("To look at the top searched products, insert 6");
            String check=scan.next();
        try{
            operationToChoose=Integer.parseInt(check);
          if (operationToChoose==1){
            Product add= new Product();
            System.out.print("Please enter the product name you want to add:");
            String name= scan.next();
            add.setProductName(name);
              try {
              System.out.print("enter its Product number: ");
              number= scan.nextInt();
              add.setProductNumber(number);
              System.out.print("Enter its availabilty:");
              int availabilty=  scan.nextInt();
              add.setProductAvailabilty(availabilty);
              System.out.print("Enter its price:");
              double price = scan.nextDouble();
              add.setPrice(price);
              System.out.print("Enter its category:");
              String category= scan.next();
              add.setProductCategory(category);
              //add= new Product(name, number, availabilty, category, price);
              inv.insert(add);
            } catch (InputMismatchException m){scan.next();System.out.println("please enter a valid number and try again.");} // if the user enters a non-number value for number variables, it throws an exception
            catch (IllegalArgumentException n){ System.out.println("please enter a positive number and try again.");}         // if the user enters a negative exception
            catch (Exception e){System.out.println("please enter a valid argument try again.");}                              // if the users enters a non-valid input, it asks the user to redo this operation again
          }

          else if(operationToChoose==2){
            System.out.print("Please enter the product number of the product you wish to remove: ");
            int removing= scan.nextInt();
            System.out.println("removing product...");
            inv.remove(removing);

          }
          else if(operationToChoose==3){
            System.out.println("Diplaying all products..");
            System.out.println("Finding product...");
            inv.display();

          }
          else if(operationToChoose==4){
            System.out.println("Diplaying available products... ");
                    //topSearchedProducts.removeLast();
            inv.displayAvailable();

          }
          else if (operationToChoose==5){
            System.out.print("Please enter the product number of the product you wish to look up : ");
            int finding= scan.nextInt();
            inv.find(finding);
          }
          else if(operationToChoose==6){
            System.out.println("Displaying the top searched products... ");
                    //topSearchedProducts.removeLast();
            System.out.println(inv.topSearched());
          } else{
            System.out.println("please choose a number within these options."); // if the user enters a number outside these, it asks them to rechoose and try again
          }
        } catch(NumberFormatException e){
          System.out.println("please choose a valid number.");        // if the user enters a non-number value for the operation variable, it tells to rechoose again

        }

          System.out.println("if you wish to continue?");
          System.out.println("if no, enter 'quit' or 'q' ");
          System.out.println("if yes, enter any other key");
          option=scan.next();


          clearScreen();                                          // The console is cleared after every operation
      } while (!option.equals("quit") & !option.equals("q")  );   // if option equal to quit or q, it exits the while loop
    }

  public static void main(String[] args) {
 /*
    ProductInventory inv= new ProductInventory("Best");
    System.out.println("Diplaying all products..");
    inv.display();
    System.out.println("Diplaying available products..");
    inv.displayAvailable();
    Product car= new Product("Toyota", 100, 3002 ,"car", 50000);
    inv.insert(car);
    Product computer= new Product("Hp", 200, 0 ,"Tech", 999);
    inv.insert(computer);

    Product pen= new Product("pen", 300, 3200 ,"writing", 7 );
    inv.insert(pen);

    Product book = new Product("book", 400, 0,"reading", 18 );
    inv.insert(book);

    Product apple= new Product("apple", 500, 30410 ,"food", 5 );
    inv.insert(apple);
    Product cup= new Product("cup", 500, 600 ,"food", 5 );
    inv.insert(cup);

    Product banana= new Product("banana", 600, 3200 ,"Food", 2 );
    inv.insert(banana);
    Product key= new Product("key", 601, 30340 ,"Food", 2 );
    inv.insert(banana);
    Product thyme= new Product("thyme", 620, 30130 ,"Food", 2 );
    inv.insert(thyme);
    Product grape= new Product("grape", 698, 30031 ,"Food", 2 );
    inv.insert(grape);
    Product knife= new Product("knife", 1023, 30012 ,"Food", 2 );
    inv.insert(knife);


    Product laptop= new Product("laptop", 700, 0 ,"tech", 2000 );
    inv.insert(laptop);

    Product phone = new Product("phone", 250, 300 ,"tech", 800 );
    inv.insert(phone);
    Product sofa= new Product("sofa",510, 0 ,"house", 5 );
    inv.insert(sofa);

    Product potato= new Product("potato", 630, 300 ,"Food", 2 );
    inv.insert(potato);


    System.out.println("Finding product that has 200 as product number..");
    inv.remove(200);
    System.out.println("Finding product that has 700 as product number..");
    inv.find(700);
    System.out.println("Finding product that has 1023 as product number..");
    inv.find(1023);
    System.out.println("Finding product that has 250 as product number..");
    inv.find(250);
    System.out.println("Finding product that has 510 as product number..");
    inv.find(510);
    System.out.println("Finding product that has 3109 as product number..");
    inv.find(3109);
    System.out.println("Printing top search products");
    System.out.println(inv.topSearched());
    System.out.println("Printing top search products");
    System.out.println(inv.topSearched());

    System.out.println("Diplaying all products..");
    inv.display();
    System.out.println("Diplaying available products..");
    inv.displayAvailable();
*/

    menu();


}
}
