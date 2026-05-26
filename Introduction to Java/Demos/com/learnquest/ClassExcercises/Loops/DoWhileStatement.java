package com.learnquest.ClassExcercises.Loops;

public class DoWhileStatement {
    public static void main(String[] args){

        int x = 79;
        int n = 210;

        do {
            x *= n * 0.005;
            System.out.println(x);
        } while (x < n);

    }
}
