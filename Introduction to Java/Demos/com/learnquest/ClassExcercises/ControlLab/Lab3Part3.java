package com.learnquest.ClassExcercises.ControlLab;

public class Lab3Part3 {
    public static void main(String[] args){
        int count = 0;
        System.out.println("\nExercise 3 : Printing Even Numbers between 1 and 100 and skipping the 50s.");
        while (count < 100){
            count++;
            
            if (count > 49 && count < 61){
                continue;
            }
            
            System.out.print(count + " ");
            
        }
    }
}
