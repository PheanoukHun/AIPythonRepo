public class Q9 {
    public static void main(String[] args) {
        boolean passing = false;
        double cash = 34.78;
        String day = "Friday";
        if (cash < 25 || (isSchoolNight(day) && !passing)) {
            System.out.println("You can't go to the Concert.");
        } else if (!passing) {
            System.out.println("You can go, but you need to study before you leave.");
        } else {
            System.out.println("Yeah, you can go! Have fun!");
        }
    }

    public static boolean isSchoolNight(String day) {
        return !(day.equals("Friday") || day.equals("Saturday"));
    }
}
