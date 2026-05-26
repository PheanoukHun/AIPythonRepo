import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckLink {
    public static void main(String[] args) {
        try {
            // Connect to the website and get the HTML document
            Document doc = Jsoup.connect("http://www.dukelearntoprogram.com/course2/data/manylinks.html").get();
            
            // Define the word to check
            String wordToCheck = "www.youtube.com";
            
            // Select all anchor elements
            Elements links = doc.select("a[href]");
            
            // Iterate through the links
            for (Element link : links) {
                String linkHref = link.attr("href");
                //String linkText = link.text();
                
                // System.out.println("Link Text: " + linkText);
                // System.out.println("Link Href: " + linkHref);

                // Check if the link text contains the word and if the link is external
                if (linkHref.contains(wordToCheck) && isExternalLink(linkHref)) {
                    System.out.println("Word \"" + wordToCheck + "\" is linked to: " + linkHref);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to check if a link is external
    private static boolean isExternalLink(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }
}
