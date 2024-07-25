package com.learnquest.ClassExcercises.Lab2;

public class Lab2{
    public static void main(String[] args){
        
        int width, height, area;
        double radius = 10.0;
        double pi = 3.14;
        boolean result = true;

        width = 8; height = 12;
        area = 96;

        int[] daysInMonths = new int[12];
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                                "November", "December"};
        
        daysInMonths[0] = 31;
        daysInMonths[1] = 28;
        daysInMonths[2] = 31;
        daysInMonths[3] = 30;
        daysInMonths[4] = 31;
        daysInMonths[5] = 30;
        daysInMonths[6] = 31;
        daysInMonths[7] = 30;
        daysInMonths[8] = 31;
        daysInMonths[9] = 30;
        daysInMonths[10] = 31;
        daysInMonths[11] = 30;

        System.out.println("Hello World");
        
        System.out.println("The value of width is " + width);
        System.out.println("The value of height is " + height);
        System.out.println("The value of area is " + area);
        System.out.println("The value of radius is " + radius);
        System.out.println("The value of pi is " + pi);
        System.out.println("The value of result is " + result);

        for (int i = 0; i < 12; i++){
            System.out.println("There are " + daysInMonths[i] + " days in " + " " + monthNames[i]);
        }
    }
}