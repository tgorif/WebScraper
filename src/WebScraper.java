import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WebScraper {
    public static void main(String[] args){
        ScapeController s = new ScapeController();
    }
}
class Page {
    URL url;
    List<String> content;
    List<String> urls;
    String urlHost;
    static final String urlMatcher = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public Page(URL u) throws IOException {
        url=u;
        content = new ArrayList<>();
        urls= new ArrayList<>();
        printURLInfo();
        parseURL();
        findURL();
        urlHost=url.getHost();
        new PageWriter(this,"test",true,true,true);
    }
    private void parseURL() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.connect();
        System.out.println("RESPONSECODE:   " + connection.getResponseCode() + " ContentType: " + connection.getContentType() + "RequestMethod: " + connection.getRequestMethod());
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
    private void printURLInfo() throws IOException {
        System.out.println("Path: " +  url.getPath() + "Host: " + url.getHost() + "Protocol: " + url.getProtocol());
        url.getContent();
    }
    private void findURL(){
        int counter=0;
        System.out.println("Trying to match " + url.toString() + content.size());
        final Pattern urlPattern = Pattern.compile(
                "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                        + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                        + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        for (String s : content) {
            System.out.println(s.length()  + " " + s + "         ::::::::::::");
            Matcher matcher = urlPattern.matcher(s);
            while (matcher.find()) {
                int matchStart = matcher.start(1);
                int matchEnd = matcher.end();
                urls.add(s.substring(matchStart,matchEnd));
                // now you have the offsets of a URL match
            }
        }
    }
    private void printList(List<String> list){
        for(String s : list){
            System.out.println(s);
        }
    }
}

