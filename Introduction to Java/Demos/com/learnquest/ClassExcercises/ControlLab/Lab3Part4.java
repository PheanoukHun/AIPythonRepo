package com.learnquest.ClassExcercises.ControlLab;

public class Lab3Part4 {
    public static void main(String[] args){
        System.out.println("\nExercise 4 : Months and Their Days");
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int monthCount = 1;
        while (monthCount <= 12){
            switch (monthCount) {
                case 2:
                    System.out.println(months[monthCount - 1] + " : " + 29);
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    System.out.println(months[monthCount - 1] + " : " + 31);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println(months[monthCount - 1] + " : " + 30);
                    break;
                default:
                    System.out.println("Invalid Month Number.");
                
            }
            monthCount++;
        }
    }
}
