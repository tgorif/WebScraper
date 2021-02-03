import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PageWriter{
    private final List<String> list = new ArrayList<>();
    private final String fileName;

    public PageWriter(List<Method> methods, String text) throws InvocationTargetException, IllegalAccessException {
        System.out.println("PageWriter created");
        if(text==null || text.length()==0) fileName="output";
        else fileName=text;
        for(Method m : methods){ m.invoke(this);}
        this.write();
    }

    private void write(){
        System.out.println("Write has been called");
        final String path = "out\\";
        final String extension = ".txt";
        new Writer(list,path,fileName,extension);
    }

    public void writeContent(){
        list.add("PAGE::Content:" + "\n");
        list.addAll(Page.getContent());
        list.add("--------------------------------------------------------------------------");
    }

    public void writeTitle(){
        list.add("PAGE::Name:" + "\n");
        list.add(Page.getPage().url.toString());
        list.add("--------------------------------------------------------------------------");
    }

    public void writeURLs(){
        list.add("PAGE::URL:" + "\n");
        list.addAll(Page.getURLS());
        list.add("--------------------------------------------------------------------------");
    }
}
