public class copyAndPasteIntoCodeLater {
    
    // Method to choose a file.
    public static Scanner getFileScanner(Scanner keyboard){
        Scanner result = null;
        try {
            System.out.print("Enter the name of the file with"
                    + " the personality data: ");
            String fileName = keyboard.nextLine().trim();
            result = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("Problem creating Scanner: " + e);
            System.out.println("Creating Scanner hooked up to default data " 
                    + e);
            String defaultData = "1\nDEFAULT DATA\n"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            result = new Scanner(defaultData);
        }
        return result;
    }
    
}
