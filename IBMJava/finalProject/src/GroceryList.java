import java.util.*;

public class GroceryList {
    
    // Initializing the Attributes
    
    // Initializing the List of Items
    private ArrayList<String> groceryListName;
    private ArrayList<Float> groceryListPrice;
    private ArrayList<Integer> groceryListQty;
    
    // Initializing the Number Values
    private int listLength;
    private float totalCost;
    private int itemCount;

    // Initializing the Boolean Values
    private boolean vetDisc;
    private boolean seniorDisc;


    // Getter Properties

    // Grocery List Name Property
    public String[] getNames() { return this.groceryListName.toArray(new String[0]); }

    // Grocery List Price Property
    public Float[] getPrices() { return this.groceryListPrice.toArray(new Float[0]); }
    
    // Grocery List Price Property
    public Integer[] getQtys() { return this.groceryListQty.toArray(new Integer[0]); }


    // Initializing the Methods for the Classes

    // Constructor
    public GroceryList() {
        this.groceryListName = new ArrayList<>();
        this.groceryListPrice = new ArrayList<>();
        this.groceryListQty = new ArrayList<>();
    }

    // Adding the Number of Items User wanted to Add or Adding the Item to the ArrayList
    public void addingItem(String item, int numAdded, float price) {
        // Getting the Current Number of Items in the List
        if (this.groceryListName.contains(item)) {
            int index = this.groceryListName.indexOf(item);
            this.groceryListQty.set(index, this.groceryListQty.get(index) + numAdded);
        } else {
            this.groceryListName.add(item);
            this.groceryListQty.add(numAdded);
            this.groceryListPrice.add(price);
        }
        System.out.println("Your Item has added to your Cart.");
    }

    // Adding the Veteran and Senior Discount after asking to the User.
    public void addingDiscount(boolean vet, boolean senior) {
        this.vetDisc = vet;
        this.seniorDisc = senior;
        this.calculateTotals();
    }

    // Calculating the Total Prices, Total Quantity, and List Length
    private void calculateTotals() {
        int totalQty = 0;
        float total = 0f;

        this.listLength = this.groceryListPrice.size();
        for (int i = 0; i < this.listLength; i++) {
            total += this.groceryListPrice.get(i) * this.groceryListQty.get(i);
            totalQty += this.groceryListQty.get(i);
        }

        if (this.vetDisc) { total *= 0.9; }
        if (this.seniorDisc) { total *= 0.95; }
        
        this.totalCost = total;
        this.itemCount = totalQty;
    }

    // Find the Average Price of the Items in the Cart Per Item.
    public Float averagePrice() {

        this.calculateTotals();

        if (this.itemCount == 0) { 
            System.out.println("There is no Items to Calculate in the Cart to Calculate Average Prices.");
            return 0f;
        }
        
        float average = this.totalCost / this.itemCount;
        return average;
    }

    // Finding the Values of Item Info
    public String findItem(int i) {
        this.calculateTotals();
        if (i < this.listLength) {
            return "You have " + this.groceryListQty.get(i) + "x " + this.groceryListName.get(i) + " and each costs $" + this.groceryListPrice.get(i) + " for a total of: $" + this.groceryListPrice.get(i) * this.groceryListQty.get(i);
        }
        return "You Currently Do Not Have that item in your Cart Right Now.";
    }

    // Printing the Class in a Certain Way
    @Override
    public String toString() {

        // The String that is going to return
        String totalString = "Your Grocery List Here: \n";
        
        // Price Variables
        this.calculateTotals();

        for (int i = 0; i < this.listLength; i++) {
            String item = this.groceryListName.get(i);
            float price = this.groceryListPrice.get(i);
            int qty = this.groceryListQty.get(i);

            if (this.vetDisc) { price *= 0.9; }
            if (this.seniorDisc) { price *= 0.95; }

            if (qty > 0) {
                float itemTotal = price * qty;

                // Append to the total string (correct way)
                totalString += "  - " + qty + "x " + item + ": $" + String.format("%.2f", itemTotal) + "\n";
            }
        }
        totalString += "Total: $" + String.format("%.2f", this.totalCost) + "\n";

        return totalString;
    }
}