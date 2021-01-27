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
    private JCheckBox ListURLS;

    public  Scrape(){
        JFrame j = new JFrame();
        j.setVisible(true);
        j.add(mainPanel);
        scrape.addActionListener(actionEvent -> {
            try{
                URL url = new URL(URLField.getText());
                Page p = new Page(url);
                if(displayHTML.isSelected()) {
                    for(String s : p.content){
                        textArea.append(s + "\n");
                    }
                }
                else if(ListURLS.isSelected()){
                    for(String s : p.urls){
                        textArea.append(s + "\n");
                    }
                }
            }
            catch (IOException e) {
                textArea.append("InvalidURL");
                e.printStackTrace();
            }
        });
    }
}

