import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Page {
    URL url;
    private List<String> content;
    private List<String> URLList;
    private List<String> imageList;
    private static Page instance;
    public static Page getPage(){
        return instance;
    }
    public static void setPage(URL u){
        if(instance==null){
            new Page(u);
        }
    }
    public static List<String> getContent(){
        if(getPage()==null) setPage(null);
        if(getPage().content.size()==0) getPage().setURLContent();
        return getPage().content;
    }
    public  static List<String> getURLS(){
        if(getPage().URLList.size()==0) getPage().findLinks();
        return getPage().URLList;
    }
    public  static List<String> getImages(){
        if(getPage().imageList.size()==0) getPage().findLinks();
        return getPage().imageList;
    }
    private Page(URL u){
        url=u;
        instance=this;
        content = new ArrayList<>();
        URLList= new ArrayList<>();
        imageList= new ArrayList<>();
    }
    private void setURLContent(){
        try {
            System.out.println(url.toString());
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
        }
        catch (IOException e){
            System.out.println("IOException");
        }
    }

    private void findLinks(){
        Document document;
        try {
            document = Jsoup.connect(url.toString()).userAgent("Mozilla").get();
            Elements links = document.select("a[href]");
            Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            addLinks(links);
            addImages(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Links found: " + URLList.size());
    }
    private String getAbsolutePath(Element element){
        String result = "";
        try {
            URI tmp = new URI(element.attr("href"));
            if(tmp.isAbsolute()) result=tmp.toURL().toString();
            else{
                URI base = url.toURI();
                String newPath = base.getPath()+ "/" + element.attr("href");
                URI absoluteURL = base.resolve(newPath);
                result=absoluteURL.toString();
            }
        }
        catch (URISyntaxException e){
            System.out.println("URISyntaxException for " + element.text());
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            System.out.println("URI IllegalArgumentException " + element.text() + " " + element.attr("href"));
            e.printStackTrace();
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        return result;
    }
    private void addLinks(Elements links){
        for (Element link : links) {
            URLList.add(getAbsolutePath(link));
        }
    }
    private void addImages(Elements images){
        for(Element image : images){
            imageList.add(image.attr(("src")));
        }
    }
}
