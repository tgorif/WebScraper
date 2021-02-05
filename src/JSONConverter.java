import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONConverter {
    private final JSONObject JSON;
    public JSONConverter(String title, List<String> content, List<String> links){
        JSONArray linkArray = toJSONArray(links,"link");
        JSONArray contentArray = toJSONArray(content,"line");
        JSONObject root = new JSONObject();
        root.put("PageName",title);
        root.put("content",contentArray);
        root.put("links",linkArray);
        JSON=root;
    }
    private JSONArray toJSONArray(List<String> list,String key){
        JSONArray result = new JSONArray();
        for(String s : list){
            JSONObject js = new JSONObject();
            js.put(key,s);
            result.put(js);
        }
        return result;
    }
    public  JSONConverter(Page page){
        JSON=new JSONConverter(Page.getPage().url.toString(),Page.getContent(),Page.getURLS()).getJSON();
    }
    public JSONObject getJSON(){
        return JSON;
    }

}
