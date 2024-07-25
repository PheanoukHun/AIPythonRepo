public class Part3 {
    public static void main(String[] args){
        Part3 p = new Part3();
        p.testOccurrences();
    }

    public void testOccurrences(){
        String a = "a";
        String b = "Banana";

        System.out.println();
        if (twoOccurrences(a, b)){
            System.out.println(a + " does occur more than once in " + b + ".");
        } else {
            System.out.println(a + " does not occur more than once in " + b + ".");
        }

        a = "by";
        b = "A story by the Abby Long";
        System.out.println();
        if (twoOccurrences(a, b)){
            System.out.println(a + " does occur more than once in " + b + ".");
        } else {
            System.out.println(a + " does not occur more than once in " + b + ".");
        }

        a = "atg";
        b = "ctgtatgta";
        System.out.println();
        if (twoOccurrences(a, b)){
            System.out.println(a + " does occur more than once in " + b + ".");
        } else {
            System.out.println(a + " does not occur more than once in " + b + ".");
        }
    
        a = "an";
        b = "banana";
        System.out.println();
        System.out.println("The part of the string after " + a + " in " + b +  " is " + lastPart(a, b) + ".");

        a = "zoo";
        b = "forest";
        System.out.println();
        System.out.println("The part of the string after " + a + " in " + b +  " is " + lastPart(a, b) + ".");
    }

    public boolean twoOccurrences(String a, String b){
        int index = b.indexOf(a);
        if (index == -1){
            return false;
        }

        index = b.indexOf(a, index + a.length());
        return index != -1;
    }

    public String lastPart(String a, String b){
        int index = b.indexOf(a);
        if (index == -1){
            return b;
        }
        return b.substring(index + a.length());
    }
}
