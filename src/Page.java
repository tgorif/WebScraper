import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Page {
    URL url;
    List<String> content;
    List<String> urls;
    String urlHost;
    static final String urlMatcher = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public Page(URL u) throws IOException {
        url=u;
        content = new ArrayList<>();
        urls= new ArrayList<>();
        parseURL();
        findURL2();
        urlHost=url.getHost();
    }
    private void parseURL() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            content.add(inputLine);
        in.close();
        connection.disconnect();
        Set<String> set = new HashSet<>();
        set.forEach(System.out::println);
    }

    private void findURL(){
        int counter=0;
        final Pattern urlPattern = Pattern.compile(
                "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                        + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                        + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        for (String s : content) {
            Matcher matcher = urlPattern.matcher(s);
            while (matcher.find()) {
                int matchStart = matcher.start(1);
                int matchEnd = matcher.end();
                urls.add(s.substring(matchStart,matchEnd));
                // now you have the offsets of a URL match
            }
        }
    }
    private void findURL2(){
        Document document;
        try {
            //Get Document object after parsing the html from given url.
            document = Jsoup.connect(url.toString()).get();

            //Get links from document object.
            Elements links = document.select("a[href]");

            //Iterate links and print link attributes.
            for (Element link : links) {
                System.out.println("Link: " + link.attr("href"));
                System.out.println("Text: " + link.text());
                System.out.println("");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
