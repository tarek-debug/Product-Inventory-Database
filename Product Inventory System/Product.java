/**
 * Creates an Product object class.
 * @ Author Tarek Solamy (Alsolame)
 * @version 1.0 3/28/2022
 */

public class Product implements Comparable <Object> {

private String name;
private int number;
private String category;
private int availabilty;
private double price;
/**
* Creates a new product.
*/
    public Product(){}
    public Product (String name, int number, int availabilty,  String category, double price){
      this.name=name;
      this.availabilty=availabilty;
      this.number=number;
      this.category=category;
      this.price=price;
    }
    @Override
    public int compareTo (Object o){
      //Product obj=(Object)o;
      return Integer.compare(this.getProductNumber(),((Product)o).getProductNumber());
      // https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html//
    }


    @Override
    public String toString(){
      //String a= "Name: " + this.getProductName();
      //String b= "Number: "+ Integer.toString(this.getProductNumber());
      //String c= " Category: "  + this.getProductCategory();
      //String d= "Price: " +Double.toString(this.getPrice());
      String productPrint= "Name: " + this.getProductName() + ","
      + "Number: "+ Integer.toString(this.getProductNumber()) + "," +
       " Category: "  + this.getProductCategory() + "," +
       "Price: " +Double.toString(this.getPrice()) + "." + "\n";
      return productPrint;
    }
// source: https://docs.oracle.com/javase/tutorial/java/data/converting.html
    public String getProductName(){
      return name;
    }
    public int getProductNumber(){
      return number;
    }
    public String getProductCategory(){
      return category;
    }
    public int getProductAvailabilty(){
      return availabilty;
    }
    public double getPrice(){
      return price;
    }
    public void setProductName(String name) {
      this.name=name;
    }
    public void setProductNumber( int number) {
      this.number=number;
    }
    public void setProductCategory( String category) {
      this.category=category;

    }
    public void setProductAvailabilty(int availabilty) throws IllegalArgumentException{
      if (availabilty<0){throw new IllegalArgumentException();} // if availablilty is negative, it will throw an excpetion
      this.availabilty=availabilty;

    }
    public void setPrice(double price)  throws IllegalArgumentException{
      if (price<0){throw new IllegalArgumentException();}     // if price is negative, it will throw an excpetion

      this.price=price;

    }



}
