import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainWindow {
    private JPanel mainPanel;
    private JTextField URLTextField;
    private JButton Scrape;
    private JButton showContentButton;
    private JTextArea DisplayArea;
    private JButton showURLsButton;
    private JButton saveButton;
    private Page page=null;

    public MainWindow() {
        JFrame j = new JFrame();
        j.setVisible(true);
        j.add(mainPanel);

        Scrape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    URL url = new URL(URLTextField.getText());
                    page = new Page(url);
                }
                catch(MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        showContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DisplayArea.removeAll();
                for(String s : page.content){
                    DisplayArea.append(s + "\n");
                }
            }
        });
        showURLsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DisplayArea.removeAll();
                for(String s : page.urls){
                    DisplayArea.append(s+"\n");
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Not Implemented");
            }
        });
    }

}
