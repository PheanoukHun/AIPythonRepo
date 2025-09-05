public class BookAccess {
    public static void main(String[] args) throws CloneNotSupportedException {
        Book book1 = new Book("Atomic Habits", "James Clear", 30.0f);
        
        Book book2 = new Book();
        book2.setTitle("Sapiens");
        book2.setAuthor("Yuval Noah Harari");
        book2.setPrice(25.0f);

        Book book3 = (Book) (book1.clone());

        System.out.println();
        System.out.println(book1);
        System.out.println();
        System.out.println(book2);
        System.out.println();
        System.out.println(comparePrices(book1, book2));
        System.out.println();
        System.out.println("The third book is cloned.");
        System.out.println(book3);
        System.out.println();
    }

    private static String comparePrices(Book book1, Book book2) {
        
        float book1Price = book1.getPrice();
        float book2Price = book2.getPrice();

        if (book1Price < book2Price) { return book1.getTitle() + " costs less than " + book2.getTitle(); }
        else if (book1Price == book2Price) { return book1.getTitle() + " costs as much as " + book2.getTitle(); }
        else { return book1.getTitle() + " costs more than " + book2.getTitle(); }
    }
}