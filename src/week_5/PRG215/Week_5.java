package week_5.PRG215;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author lsum0
 */
public class Week_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Constant for the total number of items for sale
        final int TOTAL_ITEMS = 6;
        
        //Create the items object arrays
        ItemsForSale[]items = new ItemsForSale[TOTAL_ITEMS];
        
        //Loop and instantiate each object
        for(int i = 0; i<TOTAL_ITEMS; i++)
        {
            items[i]=new ItemsForSale();
        }
        
        //Use the PopulateItem method from the ItemsForSale class to insert the properties of each item object for sale
        items[0].PopulateItem("Tennis Shoes", 45.89,true);
        items[1].PopulateItem("Shirts", 25.55,true);
        items[2].PopulateItem("Coats", 89.99,true);
        items[3].PopulateItem("Belts", 15, true);
        items[4].PopulateItem("Pants", 25.99,true);
        items[5].PopulateItem("Donation", 10,false);
        
        //variables for the financial calculations
        double totalAmount = 0.0;
        double totalTax = 0.0;
        double taxRate = 0.081;
        
        //Discounts for large purchases
        final double DISCOUNT_RATE = 0.025; //2.5%
        final double AMOUNT_TO_QUALIFY_FOR_DISCOUNT = 100;
        double discountAmount = 0;
        
        //Display items for sale on the console
        System.out.println("The following clothing items are available for purchase:");
        for(int i=0; i<items.length; i++)
        {
            //Display eah item in the array
            System.out.println(" "+(i+1)+"."+items[i].itemName+" for $"+items[i].itemCost+" each");
        }
        System.out.println("");
        
       //Create a keyboard input object
       Scanner keyboard = new Scanner(System.in);
       
       //Create a new customer object
       Customer newCust =new Customer();
       
       //Ask for the cus's first name
       System.out.println("Please enter your first name:");
       
       newCust.firstName = keyboard.next();
       
       //Ask for the last name
       System.out.println("Please enter your last name:");
       
       newCust.lastName = keyboard.next();
       
       System.out.println("");
       
       //Display cus full name
       System.out.println("Ok, "+newCust.fullName()+", please enter the product ID (the number to the left of the item name)that you wish to purchase."
               + "Enter 0 when you are finished.");
       
       //loop until the user is finished
       int itemID = 0; //Set the condition to 0
       int itemCounter = 1; //No longer the loop condition conter, 
       do
       {
           //Prompt the user
           System.out.println("Please enter item ID number"+(itemCounter)+":");
           
           //Wrap the input in a Try/catch block to capture any user data entry exceptions
           try
           {
               itemID = keyboard.nextInt();
               
               //Test the user did not enter the exit conditon
               if(itemID>0)
               {
                   totalAmount = totalAmount + items[itemID-1].itemCost;
                   
                   //Moved tax calculation to here since we might not charge tax on al items
                   if(items[itemID-1].taxable == true)
                   {
                       totalTax = totalTax+(items[itemID-1].itemCost*taxRate);
                   }
                   //increment of counter
                   itemCounter++;
               }
           }
           //User entered a # outside the size of the array
           catch (ArrayIndexOutOfBoundsException e1)
           {
               //Display the error message
               System.out.println("The item ID you entered is outside the range of possible items."
                       + "This must be a number between 1 and "+TOTAL_ITEMS+". Please re-enter your item ID.");
               
               //Set this as a flag to remian the loop
               itemID = -1;
           }
           //User didn't enter an integer
           catch (InputMismatchException e2)
           {
               //Display the error message
               System.out.println("The item ID you entered does not appear to be an integer number."
                       + "The item ID must be a number between 1 and "+TOTAL_ITEMS+". Please re-enter your item ID.");
               
               //This is required to remove additional non-integer items stored in the scanner input buffer
               keyboard.nextLine();
               
               //Set this as a flag to remina the loop
               itemID = -1;
           }
       } while (itemID !=0); //Check if exit condition has been met
       
       //The loop is complete, calculate the discount and taxes and then display the reuslts
       if(totalAmount >= AMOUNT_TO_QUALIFY_FOR_DISCOUNT)
       {
           discountAmount = totalAmount * DISCOUNT_RATE;
       }
       else
       {
           discountAmount = 0;
       }
       
       //Display the results
       System.out.println("");
       System.out.println("You selected "+(itemCounter-1)+" items to purchase.");
       System.out.println("Your sales total $"+totalAmount);
       System.out.println("Your discount ammount is $"+discountAmount);
       System.out.println("Your sale tax is $"+totalTax);
       System.out.println("The total amount due is $"+(totalAmount-discountAmount+totalTax));
       System.out.println("");
    }
    
}
