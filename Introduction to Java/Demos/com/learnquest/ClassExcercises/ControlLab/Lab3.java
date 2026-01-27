package com.learnquest.ClassExcercises.ControlLab;

public class Lab3 {
    public static void main(String[] args){
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.println("\nStart of Output for Excercise 1 : Standard Loop");
        for (int i = 0; i < daysOfWeek.length; i++){
            System.out.println(daysOfWeek[i]);
        }

        System.out.println("\nStart of Output for Excercise 1 : For Each Loop");
        for (String day : daysOfWeek){
            System.out.println(day);
        }

        System.out.println("\nStart of Output for Excercise 1 : Standard Loop (Reversed)");
        for (int i = daysOfWeek.length - 1; i >= 0; i--){
            System.out.println(daysOfWeek[i]);
        }
    }
}
