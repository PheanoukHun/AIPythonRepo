public class Book implements Cloneable {

    // Initializing the Variables

    // Initializing the String Variables
    private String title;
    private String author;

    // Initializing the Float Variables
    private float price;


    // Methods

    // Main Constructor
    public Book (String title, String author, Float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Default Constructor
    public Book() {
        this.title = "";
        this.author = "";
        this.price = 0.0f;
    }

    // Property Methods

    // Getting Title
    public String getTitle() { return this.title; }

    // Getting the Author
    public String getAuthor() { return this.author; }

    // Getting the Price
    public float getPrice() { return this.price; }


    // Setter Methods

    // Setting the Author
    public void setAuthor(String author) { this.author = author; }

    // Setting the Price
    public void setPrice(float price) { this.price = price; }

    // Setting the Author
    public void setTitle(String title) { this.title = title; }

    // To String Method to Show a Way to Display to the Screen
    @Override
    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + this.author + "\nPrice: $" + String.format("%.2f", this.price);
    }

    // A Method that Allows the Book to Be Displayed
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}