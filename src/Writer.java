import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    public void saveURLContent(Page p) throws IOException {
        saveURLContent(p,"output.txt");
    }
    public void saveURLContent(Page page,String filename) throws IOException {
        File file = new File("out//output.txt");
        if(file.createNewFile()) {
            writeTo(file,page.content,"Content of " + page.url.toString());
        }
        else{
            saveURLContent(page,incrementFileName(filename));
        }
    }
    public void saveContainedURLS(Page p) throws IOException {
        saveContainedURLS(p,"output.txt");
    }
    public void saveContainedURLS(Page page,String filename) throws IOException {
        File file = new File(filename);
        if(file.createNewFile()) {
            writeTo(file,page.urls,"URLs contained in:  " + page.url.toString());
        }
        else{
            saveURLContent(page,incrementFileName(filename));
        }
    }
    private void writeTo(File file, List<String> list,String head) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(head + "\n");
        for (String s : list) {
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
    }
    private String incrementFileName(String fileName){
        if(fileName==null || fileName.length()==0) return "untitled";
        String result;
        if(!Character.isDigit(fileName.charAt(fileName.length()-1))){
            result=fileName+"1";
        }
        else{
            String endingInteger;
            int j=fileName.length()-1;
            while(Character.isDigit(fileName.charAt(j))){
                j--;
            }
            endingInteger=fileName.substring(j);
            int newEndingInteger = Integer.parseInt(endingInteger);
            result=fileName.substring(0,j) + newEndingInteger;
        }
        return result;
    }
}
