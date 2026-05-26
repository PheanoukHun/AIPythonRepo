import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class URLResource {
    public String getBodyText(String url) throws Exception {
        Document doc = Jsoup.connect(url.toLowerCase()).get();
        return doc.body().text();
    }

    public static void main(String[] args){
        URLResource ur = new URLResource();
        try{
            System.out.println(ur.getBodyText("http://example.com"));
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
