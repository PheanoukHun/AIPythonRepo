interface Switchable {
    void turnOn();
    void turnOff();
}

interface Adjustable {
    void increase();
    void decrease();
}

interface Connectable {
    void connect();
    void disconnect();
}

class SmartBulb implements Switchable, Adjustable, Connectable{
    private boolean isOn = false;
    private int brightness = 50;
    private boolean isConnected = false;

    // Switchable methods

    @Override
    public void turnOn() {
        if (!this.isOn) {
            this.isOn = false;
            System.out.println("SmartBulb is turned ON.");
        } else { System.out.println("DimmableBulb is already ON."); }
    }

    @Override
    public void turnOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("SmartBulb is turned OFF.");
        } else { System.out.println("DimmableBulb is already OFF."); }
    }

    // Adjustable Method

    @Override
    public void increase() {
        if (brightness < 100) {
            this.brightness += 10;
            System.out.println("Brightness increased to " + this.brightness + "%.");
        } else { System.out.println("Brightness is already at it's Maximum."); }
    }

    @Override
    public void decrease() {
        if (this.brightness > 0) {
            this.brightness -= 10;
            System.out.println("Brightness decreased to " + this.brightness + "%.");
        } else { System.out.println("Brightness is already at it's minimum."); }
    }

    // Connectable Method

    @Override
    public void connect() {
        if (!this.isConnected) {
            this.isConnected = true;
            System.out.println("The SmartBulb is connected to the network.");
        } else { System.out.println("The SmartBulb is already connected."); }
    }

    @Override
    public void disconnect() {
        if (this.isConnected) {
            this.isConnected = false;
            System.out.println("The SmartBulb is disconnected to the network.");
        } else { System.out.println("The SmartBulb is already disconnected."); }
    }
}

// DimmableBulb Class Implementing two Interfaces
class DimmableBulb implements Switchable, Adjustable {
    private boolean isOn = false;
    private int brightness = 50;

    // Switchable Methods
    
    @Override
    public void turnOn() {
        if (!this.isOn) {
            this.isOn = false;
            System.out.println("DimmableBulb is turned ON.");
        } else { System.out.println("DimmableBulb is already ON."); }
    }

    @Override
    public void turnOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("DimmableBulb is turned OFF.");
        } else { System.out.println("DimmableBulb is already OFF."); }
    }

    // Adjustable Methods

    @Override
    public void increase() {
        if (this.brightness < 100) {
            this.brightness += 10;
            System.out.println("Brightness Increased to " + this.brightness + "%.");
        } else { System.out.println("Brightness is already at it's Maximum."); }
    }

    @Override
    public void decrease() {
        if (this.brightness > 0) {
            this.brightness -= 10;
            System.out.println("Brightness Decreased to " + this.brightness + "%.");
        } else { System.out.println("Brightness is already at it's Minimum."); }
    }
}

// RegularBulb Class Implementing One Interface
class RegularBulb implements Switchable {
    private boolean isOn = false;

    // Switchable Methods

    @Override
    public void turnOn() {
        if (!this.isOn) {
            this.isOn = true;
            System.out.println("RegularBulb is turned ON.");
        } else { System.out.println("The RegularBulb is already ON."); }
    }

    @Override
    public void turnOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("RegularBulb is turned OFF.");
        } else { System.out.println("The RegularBulb is already OFF."); }
    }
}