package com.learnquest.ClassExcercises.Loops;

public class ForEachLoops {
    public static void main(String[] args){
        
        String[] cars = {"Volvo", "Toyota", "Hyunda", "Honda"};
        for (String car : cars) {
            System.out.println(car);
        }

        Integer[] myNums = {123, 4354, 657, 988, 7657};
        for (Integer num : myNums){
            System.out.println(num);
        }
    }
}
