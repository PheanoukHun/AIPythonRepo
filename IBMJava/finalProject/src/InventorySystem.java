import java.io.*;

public class InventorySystem {

    // Initializing the Arrays Variables

    // Array for String Names
    private String[] inventoryListStrings;

    // Array for the Pricing
    private float[] inventoryListPricing;

    // Array for the Amount
    private int[] inventoryListAmount;


    // Properties

    // Names Property
    public String[] getNames() { return this.inventoryListStrings; }
    
    // Pricing Property
    public float[] getPrices() { return this.inventoryListPricing; }

    // Amount Property
    public int[] getAmounts() { return this.inventoryListAmount; }

    // Constructor to Read the Files and Get Valuable Information

    public InventorySystem(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            // Reading the Item Names
            String line = br.readLine();
            this.inventoryListStrings = line.split(",");

            // Reading the Item Prices
            line = br.readLine();
            String[] temp = line.split(",");

            this.inventoryListPricing = new float[temp.length];
            for (int i = 0; i < temp.length; i++) { this.inventoryListPricing[i] = Float.parseFloat(temp[i].trim()); }

            // Reading the Item Quantity On Hand
            line = br.readLine();
            temp = line.split(",");

            this.inventoryListAmount = new int[temp.length];
            for (int i = 0; i < temp.length; i++) { this.inventoryListAmount[i] = Integer.parseInt(temp[i].trim()); }
        }
        catch (IOException e) { System.out.println("Error Reading File: " + e.getMessage()); }
        catch (NumberFormatException e) { System.out.println("Error Reading File: " + e.getMessage()); }
        catch (Exception e) { System.out.println("Error Reading File: " + e.getMessage()); }
    }


    // Methods

    // Updating the Inventory Based on What Customer Chose
    public void updateInventory(int index, int numTaken) {
        this.inventoryListAmount[index] += numTaken;
    }

    // Printing out the Inventory Amount and Prices
    @Override
    public String toString() {
        String totalString = "";
        if (this.inventoryListStrings.length != 0) {
            for (int i = 0; i < this.inventoryListStrings.length; i++) {
            totalString += this.inventoryListStrings[i] + ": \n  Amount Left: " + this.inventoryListAmount + "\n  Price Per Unit: $" + this.inventoryListPricing + "\n";
            }
            return totalString;
        } else {
            System.out.println("There is no Items in the Inventory List.");
            return "";
        }
    }
}