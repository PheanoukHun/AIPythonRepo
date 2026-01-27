package com.learnquest.ClassExcercises.SwitchAndTernary;

public class Switch {
    public static void main(String[] args){
        
        String myStr = "Monday";
        String typeOfDay;

        switch (myStr){
            case "Monday":
                typeOfDay = "Start of the Week";
                break;
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
                typeOfDay = "Midweek";
                break;
            case "Friday":
                typeOfDay = "End of Week";
                break;
            case "Saturday":
            case "Sunday":
                typeOfDay = "Weekend";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + myStr);
        }

        System.out.println("Today is a " + typeOfDay + "day.");
    }
}
