import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
public class JSONConverter {
    private final JSONObject JSON;

    public JSONConverter(String title, List<String> content, List<String> links){
        JSONArray contentJSON = new JSONArray(content);
        JSONArray linksJSON = new JSONArray(links);
        JSONObject result = new JSONObject("Title",title);
        result.put("Body",contentJSON);
        result.put("Links",linksJSON);
        JSON=result;
    }
    public JSONObject getJSON(){
        return JSON;
    }

}
