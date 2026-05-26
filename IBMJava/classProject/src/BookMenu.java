import java.util.Scanner;

public class BookMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book[] books = new Book[10];
        int bookIndex = 0;
        while (true) {
    
            System.out.println();

            System.out.println("Press 1 to view Books\nPress 2 to Add Books\nPress 3 to Compare the Prices of Two Books\nAny Other Keys to Exit");
            System.out.print("Enter Your Response Here: ");
            String userAction = sc.nextLine();
    
            System.out.println();

            if (userAction.equalsIgnoreCase("1")) {
                for (int i = 0; i < books.length; i++) { if (books[i] != null) { System.out.println(books[i]); } }
            } else if (userAction.equalsIgnoreCase("2")) {
                
                if (bookIndex == 10) { System.out.println("10 Books Has Already Been Added. Cannot Add Any More Book!"); }
                else {
                    System.out.print("Enter Your Book Title Here: ");
                    String tempTitle = sc.nextLine();

                    System.out.print("Enter Your Book's Author Here: ");
                    String tempAuthor = sc.nextLine();

                    try {
                        System.out.print("Enter the Book's Price Here: $");
                        float tempPrice = Float.parseFloat(sc.nextLine());

                        Book tempBook =  new Book(tempTitle, tempAuthor, tempPrice);
                        books[bookIndex++] = tempBook;

                        System.out.println("Your Book Has Been Added.\n");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        continue;
                    }
                }
            } else if (userAction.equalsIgnoreCase("3")) {
                Book b1Book = null;
                Book b2Book = null;
                
                String b1 = "";
                String b2 = "";

                while (b1Book == null || b2Book == null) {
                    if (b1Book == null) {
                        print("Please Enter the Title of the First Book Here: ");
                        b1 = sc.nextLine();
                    }
                    
                    if (b2Book == null) {
                        print("Please Enter the Title of the Second Book Here: ");
                        b2 = sc.nextLine();
                    }

                    for (Book b : books) {
                        if (b != null) {
                            if (b1.equalsIgnoreCase(b.getTitle())) { b1Book = b; }
                            if (b2.equalsIgnoreCase(b.getTitle())) { b2Book = b; }
                        }
                    }
                }

                String moreExpensive = comparePricesReturnHigher(b1Book, b2Book);
                if (moreExpensive != null) {
                    if (!moreExpensive.equals("Same Value")) { println(moreExpensive + " is more expensive than the Other Book."); }
                    else {println("Both Books Have the Same Values"); }
                }
            }  else {
                sc.close();
                break;
            }
        }   
    }

    private static void println(String s) { System.out.println(s); }
    private static void print(String s) { System.out.print(s); }

    public static String comparePricesReturnHigher(Book b1, Book b2) {
        if (b1 != null && b2 != null) {       
            if (b1.getPrice() > b2.getPrice()) { return b1.getTitle(); }
            else if (b1.getPrice() < b2.getPrice()) { return b2.getTitle(); }
            else { return "Same Value"; }
        } else { 
            System.out.println("One of the Book Does not Exits.");
            return null;
        }
    }
}