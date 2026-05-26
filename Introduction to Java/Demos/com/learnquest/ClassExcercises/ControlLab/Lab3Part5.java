package com.learnquest.ClassExcercises.ControlLab;

public class Lab3Part5 {
    public static void main(String[] args){
        boolean notPrinting = true;
        int leftOff = 4; // Skipping Through Sunday to Thursday
        int dayCount = 1; // Day of the Week
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        
        for (int i = 0; i < 6; i++){ // Lines of the Month
            for (int j = 0; j < 7; j++){ // Days of the Week
                
                if (dayCount > 31){
                    break;
                }
                
                if (notPrinting){
                    System.out.print("    "); // Print Spaces Before Day 1
                    if (j == leftOff){
                        notPrinting = false;
                    }
                }
                else if (dayCount < 10) {
                    System.out.print(dayCount + "   ");
                    dayCount++;
                }
                else {
                    System.out.print(dayCount + "  ");
                    dayCount++;
                }
            }
        System.out.println();
        }
    }
}
