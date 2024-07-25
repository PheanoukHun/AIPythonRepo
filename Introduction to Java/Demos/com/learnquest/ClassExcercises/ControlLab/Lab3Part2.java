package com.learnquest.ClassExcercises.ControlLab;

public class Lab3Part2 {
    public static void main(String[] args){
        int n = 1;
        System.out.println("\nExercise 2 : Printing Even Numbers between 1 and 20.");
        while(n <= 20){
            if (n % 2 == 0){
                System.out.print(n + " ");
            }
            n++;
        }
    }
}
