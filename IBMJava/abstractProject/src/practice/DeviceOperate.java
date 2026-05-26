public class DeviceOperate {
    public static void main(String[] args) {
        
        // Create an array of Switchable devices
        Switchable[] devices = new Switchable[2];

        // Create instances of SmartSpeaker and Fan
        SmartSpeaker speaker = new SmartSpeaker();
        Fan fan = new Fan();

        // Add devices to the array
        devices[0] = speaker;
        devices[1] = fan;

        // Turn each device on and off
        for (Switchable device : devices) {
            System.out.println();
            device.turnOn();
            device.turnOff();
        }

        System.out.println();

        // Increase the fan speed (uses Adjustable)
        ((Adjustable) devices[1]).increase();

        // Decrease the fan speed
        ((Adjustable) devices[1]).decrease();

        System.out.println();

        // Increase the speaker volume directly
        speaker.increaseVol();

        // Decrease the speaker volume
        speaker.decreaseVol();

        System.out.println();

        // Connect and disconnect the SmartSpeaker
        ((Connectable) devices[0]).connect();
        ((Connectable) devices[0]).disconnect();
    }
}
