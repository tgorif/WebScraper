import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    public Writer(List<String> list,String path,String filename,String extension){
        write(createFile(path,filename,extension),list);
    }
    private File createFile(String path,String filename,String extension){
        System.out.println("Writer filename" + path + filename + extension);
        try {
            File file = new File( path +filename + extension);
            if (file.createNewFile()) return file;
            else return createFile(path,incrementFileName(filename),extension);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private void write(File file,List<String> list){
        try {
            FileWriter w = new FileWriter(file);
            for (String s : list) {
                w.write(s);
            }
            w.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
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
            endingInteger=fileName.substring(j+1);
            int newEndingInteger = Integer.parseInt(endingInteger)+1;
            result=fileName.substring(0,j+1) + newEndingInteger;
        }
        return result;
    }
}
