public class BulbOperate {
    public static void main(String[] args) {
        
        // Create an array of Switchable objects to hold different types of bulbs
        Switchable switchables[] = new Switchable[3];

        // Create Instance of SmartBulb, DimmableBulb, and RegularBulb

        // SmartBulb implements Switchable, Adjustable, and Connectable
        SmartBulb sb = new SmartBulb();

        // DimmableBulb Implements Switchable, and Adjustable
        DimmableBulb db = new DimmableBulb();

        // RegularBulb implements only Switchable
        RegularBulb rb = new RegularBulb();

        // Populate the Array with the Bulb Instances
        
        // Add SmartBulb to the Array
        switchables[0] = sb;

        // Add DimmableBulb to the Array
        switchables[1] = db;

        // Add RegularBulb to the Array
        switchables[2] = rb;

        // Loops through the Array and Turn Each Bulb On and Off
        for (int i = 0; i < switchables.length; i++) {
            
            System.out.println();
            
            // Turns on the Bulb
            switchables[i].turnOn();

            // Turns off the Bulb
            switchables[i].turnOff();
        } System.out.println();

        // Increase the Brightness of the SmartBulb Directly
        
        // Calls the Increase() Method of SmartBulb
        sb.increase();

        // Decrease the Brightness of the DimmableBulb by Casting to Adjustable

        // Cast Switchables[1] (DimmableBulb) to Adjustable and Call Decrease()
        ((Adjustable) switchables[0]).decrease();

        // Connect the SmartBulb to the Network by Casting to Connectable
        // Cast Switchables[0] (SmartBulb) to Connectable and Call Connect()
        ((Connectable) switchables[0]).connect();
    }
}