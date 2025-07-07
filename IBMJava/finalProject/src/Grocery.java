// Importing the Scanner Class
import java.util.Arrays;
import java.util.Scanner;

public class Grocery {
    public static void main(String[] args) {
        
        // Initializing Terms
        
        // Creating The Scanner Class
        Scanner sc = new Scanner(System.in);

        // Initializing String Input Variable
        String userChoice = "";
        String itemAdded;

        // Initializing the Number Input Variables
        int itemIndex;
        int numAdded;
        float itemPrice;

        // Initializing the InventorySystyem Class
        InventorySystem invSys = new InventorySystem("../dataFiles/data.csv");

        // Getting Inventory List Names and Inventory List Prices from InventorySystem Class
        String[] inventoryList = invSys.getNames();
        float[] priceList = invSys.getPrices();

        // Initializing the Inventory Class
        GroceryList groceryList = new GroceryList();


        // Main Loop
        while (true) {

            // Reading User Input
            System.out.println("\nWelcome to Online Grocery\nPlease Enter One of the Options Below:");
            System.out.println("  1. Create a Grocery List");
            System.out.println("  2. View Your Grocery List");
            System.out.println("  3. View the Average Price per Item");
            System.out.println("  4. Filter Items Below a Certain Price");
            System.out.println("  5. Apply for Discounts");
            System.out.println("  6. Grocery Store Inventory Check");
            System.out.println("Type 'Exit' to Exit the Program");
            userChoice = sc.nextLine();

            // Getting User Actions

            // Exit Condition
            if (userChoice.equalsIgnoreCase("Exit")) { break; }

            // Create a Grocery List
            else if (userChoice.equals("1")) { 
                while (true) {
                    System.out.println("\nWhich Items would You Like to Add to Your Cart: ");
                    
                    // Showing all Items
                    for (int i = 0; i < inventoryList.length; i++) { System.out.println("  - " + (i + 1) + ". " + inventoryList[i]); }

                    // Exit Condition for the Loop
                    System.out.println("  - Type 'Complete' to Finish Adding Items.");

                    // Item Input Field
                    System.out.print("Please Enter a Number between 1 to " + inventoryList.length + " or 'Complete' Here: ");
                    itemAdded = sc.nextLine();

                    // Exit Field
                    if (itemAdded.equalsIgnoreCase("Complete")) { break; }
                    else {
                        try {
                            itemIndex = Integer.parseInt(itemAdded) - 1;
                            itemAdded = inventoryList[itemIndex];
                            itemPrice = priceList[itemIndex];
                        } catch (NumberFormatException e) { 
                            System.out.println("Please Enter a Valid Number between 1 to " + inventoryList.length + " or 'Complete: "); 
                            continue;
                        }  catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Number is not within the Valid Interval. Please Try Again.");
                            continue;
                        }

                        try {
                            System.out.print("Enter the Number of Items You Want Here: ");
                            numAdded = Math.abs(Integer.parseInt(sc.nextLine()));

                            while (numAdded > invSys.getAmounts()[itemIndex]) {
                                System.out.println("Sorry for the Inconvenience but we current only have " + invSys.getAmounts()[itemIndex] + " in stock currently.");
                                System.out.print("Please enter a Smaller Number Here: ");
                                numAdded = Math.abs(Integer.parseInt(sc.nextLine()));
                            }

                            groceryList.addingItem(itemAdded, numAdded, itemPrice);
                        } catch (NumberFormatException e) { System.out.println("Please Enter a Valid Number."); }
                    }
                }
            }

            // View Your Grocery List
            else if (userChoice.equals("2")) { System.out.println(groceryList); }

            // Get Average 
            else if (userChoice.equals("3")) { groceryList.averagePrice(); }

            // Filter Items Below a Certain Price
            else if (userChoice.equals("4")) {
                while (true) {
                    try {
                        System.out.print("Please Enter a Number to be filtered: $");
                        float price = Float.parseFloat(sc.nextLine());
                        
                        System.out.println("\nHere are the Items and Prices that are Lower than your Criteria");
                        for (int i = 0; i < priceList.length; i++) {
                            if (priceList[i] < price) {
                                System.out.println("  - " + inventoryList[i] + ": $" + priceList[i]);
                            }
                        }

                        System.out.println("Press Enter to Go Back to the Main Page");
                        sc.nextLine();
                        break;
                    } 
                    catch (NumberFormatException e) { System.out.println("Please Enter a Valid Number."); }
                }
            }

            // Apply Discount
            else if (userChoice.equals("5")) {

                System.out.print(" Do you qualify for a Veterans Discount (y/n): ");
                boolean vetDiscount = sc.nextLine().equalsIgnoreCase("y");

                System.out.print(" Do you qualify for a Seniors Discount (y/n): ");
                boolean senDiscount = sc.nextLine().equalsIgnoreCase("y");

                groceryList.addingDiscount(vetDiscount, senDiscount);

                if (vetDiscount || senDiscount) { System.out.println("Your Discounts has been Applied to Your Cart");}
                else {System.out.println("No Discounts has been Applied to Your Cart."); }
            }

            else if (userChoice.equals("6")) {
                System.out.println("Here is the Current Inventory Amount Found in the Store: ");
                for (int i = 0; i < groceryList.getNames().length; i++) {
                    int index = Arrays.asList(inventoryList).indexOf(groceryList.getNames()[i]);
                    if  (index != -1 ) { invSys.updateInventory(index, -groceryList.getQtys()[i]); }
                }

            }

            // Invalid Choice Indicator
            else { System.out.println("Invalid Input, Please Enter a Valid Number between 1-2 or the word 'Exit'."); }
        }

        // Closing the Scanner Class
        sc.close();
    }
}