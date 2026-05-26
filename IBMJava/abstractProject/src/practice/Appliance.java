// Created Volume Interface
interface Volume {
    void increaseVol();
    void decreaseVol();
}

// Created Switchable Interface
interface Switchable {
    void turnOn();
    void turnOff();
}

// Created Adjustable Interface
interface Adjustable {
    void increase();
    void decrease();
}

// Created Connectable Interface
interface Connectable {
    void connect();
    void disconnect();
}

class SmartSpeaker implements Volume, Switchable, Connectable {
    private int volume = 50;
    private boolean isOn = false;
    private boolean isConnected = false;

    // Implement the Volume Interface

    // Method to Increase Volume
    @Override
    public void increaseVol() {
        if (this.volume < 100) {
            this.volume += 10;
            System.out.println("Smart Speaker increased to " + this.volume + "%");
        } else { System.out.println("Smart Speaker is already at it's Maximum."); }
    }

    // Method to Decrease Volume
    @Override
    public void decreaseVol() {
        if (this.volume > 0) {
            this.volume -= 10;
            System.out.println("Smart Speaker decreased to " + this.volume + "%");
        } else { System.out.println("Smart Speaker is already at it's Minimum."); }
    }

    // Implement the Switchable Interface
    
    // Method to Turn On Smart Speaker
    @Override
    public void turnOn() {
        if (!this.isOn) {
            this.isOn = true;
            System.out.println("Smart Speaker is turned ON.");
        } else { System.out.println("Smart Speaker is already ON."); }
    }

    // Method to Turn Off Smart Speaker
    @Override
    public void turnOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("Smart Speaker is turned OFF.");
        } else { System.out.println("Smart Speaker is already OFF."); }
    }

    // Implement the Connectable Interface

    // Method to Connect the Speaker to Bluetooth
    @Override
    public void connect() {
        if (!this.isConnected) {
            this.isConnected = true;
            System.out.println("Smart Speaker is Connected to Bluetooth.");
        } else { System.out.println("Smart Speaker is ALREADY Connected to Bluetooth."); }
    }

    // Method to Disconnect the Speaker from Bluetooth
    @Override
    public void disconnect() {
        if (this.isConnected) {
            this.isConnected = false;
            System.out.println("Smart Speaker is Disconnected from Bluetooth.");
        } else { System.out.println("The Smart Speaker is ALREADY Disconnected from Bluetooth."); }
    }
}

class Fan implements Switchable, Adjustable {
    private boolean isOn = false;
    private int speed = 0;

    // Implement the Switchable Interface
    
    // Method to Turn On Smart Speaker
    @Override
    public void turnOn() {
        if (!this.isOn) {
            this.isOn = true;
            System.out.println("The Fan is turned ON.");
        } else { System.out.println("The Fan is already ON."); }
    }

    // Method to Turn Off Smart Speaker
    @Override
    public void turnOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("The Fan is turned OFF.");
        } else { System.out.println("The Fan is already OFF."); }
    }

    // Implement the Adjustable Interface
    @Override
    public void increase() {
        if (this.speed < 100) {
            this.speed += 10;
            System.out.println("Fan's Speed increased to " + this.speed + "%.");
        } else { System.out.println("Fan's Speed is already at it's Maximum."); }
    }

    @Override
    public void decrease() {
        if (this.speed > 0) {
            this.speed -= 10;
            System.out.println("Fan's Speed decreased to " + this.speed + "%.");
        } else { System.out.println("Fan's Speed is already at it's minimum."); }
    }
}