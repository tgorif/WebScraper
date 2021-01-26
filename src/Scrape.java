import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Scrape {
    private JTextField URLField;
    private JButton scrape;
    private JPanel mainPanel;
    private JCheckBox displayHTML;
    private JTextArea textArea;

    public  Scrape(){
        JFrame j = new JFrame();
        j.setVisible(true);
        j.add(mainPanel);
        scrape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    URL url = new URL(URLField.getText());
                    if(displayHTML.isSelected()){
                        showURlContent(url,textArea);
                    }
                }
                catch (IOException e) {
                    textArea.append("InvalidURL");
                    e.printStackTrace();
                }
            }
        });
    }
    private void showURlContent(URL url,JTextArea area) throws IOException {
        displayURLContent(readURLContent(url),area);
    }
    private <T> void displayURLContent(List<T> list,JTextArea area){
        for(T t : list){
            area.append(t + "\n");
        }
    }
    private List<String> readURLContent(URL url) throws IOException {
        List<String> content = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            content.add(inputLine);
        }
        in.close();
        return content;
    }
}

