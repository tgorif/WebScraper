import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PageWriter{
    private final List<String> list = new ArrayList<>();
    private Page page;
    private boolean writeHead;
    private boolean writeContent;
    private boolean writeURLs;
    private String fileName;

    public PageWriter(Page p,String name,boolean head,boolean content,boolean urls){
        page=p;
        fileName=name;
        writeHead=head;
        writeContent=content;
        writeURLs=urls;
        write();
    }

    public PageWriter(Page p,boolean head,boolean content,boolean urls){
         new PageWriter(p,"output",head,content,urls);
    }

    public PageWriter(Page p){
        new PageWriter(p,"output",true,true,true);
    }

    public PageWriter(Page p,List<Method> methods, String text) throws InvocationTargetException, IllegalAccessException {
        System.out.println("PageWriter created");
        page=p;
        if(text==null || text.length()==0) fileName="output";
        else fileName=text;
        for(Method m : methods){ m.invoke(this,null);}
        this.write();
    }

    private void write(){
        System.out.println("Write has been called");
        final String path = "out\\";
        final String extension = ".txt";
        if(writeHead) writeTitle();
        if(writeContent) writeContent();
        if(writeURLs) writeURLs();
        new Writer(list,path,fileName,extension);
    }

    public void writeContent(){
        list.add("PAGE::Content:" + "\n");
        list.addAll(page.content);
        list.add("--------------------------------------------------------------------------");
    }

    private void writeTitle(){
        list.add("PAGE::Name:" + "\n");
        list.add(page.url.toString());
        list.add("--------------------------------------------------------------------------");
    }

    private void writeURLs(){
        list.add("PAGE::URL:" + "\n");
        list.addAll(page.urls);
        list.add("--------------------------------------------------------------------------");
    }
}
