/*
 * LinkedListTester.java - CS 314 Assignment 6
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@eid.utexas.edu
 * UTEID: ph23434
 */


/*
 * TODO: Place your experiment results here:
 *
 */

import java.util.Arrays;
import java.util.Iterator;

public class LinkedListTester {

    public static void main(String[] args) {

        // basicTests();
        // spring2021StressTests();
        // itRemoveStressTests();

        // Uncomment the following line to run tests comparing
        // your LL314 class to the java ArrayList class.
        // comparison();

        myTestSuite();
    }

    private static void myTestSuite() {
        
        LL314<String> stringLL = new LL314<>();

        stringLL.add("A");
        stringLL.add("B");
        stringLL.add("C");
        stringLL.add("A");

        boolean actual = stringLL.toString().equals("[A, B, C, A]");
    }

    // Convert elements of list to an array. Uses the list
    // size method and iterator.
    private static Object[] toArray(LL314<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while(it.hasNext()){
            result[index] = it.next();
            index++;
        }
        return result;
    }

    // pre: none
    // post: return true if the 
    private static boolean arraysSame(Object[] one, Object[] two)  {
        return Arrays.equals(one, two);
    }
}