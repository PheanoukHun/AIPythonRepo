package com.learnquest.ClassExcercises.Loops;

public class ForIStatements {
    public static void main(String[] args){
        
        for (int i = 1; i <= 10; i++){
                System.out.println(i);
            }

        System.out.println();
        
        int i, j;
        for (i = 0, j = 1; i < 10; i++, j += 3){
            System.out.println(i + " " + j);
        }
    }
}
