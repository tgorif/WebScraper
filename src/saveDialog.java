import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class saveDialog {
    private JPanel panel1;
    private JCheckBox ContentCheckBox;
    private JCheckBox URLCheckBox;
    private JButton saveButton;
    private JLabel includeLabel;
    private JCheckBox TitleCheckBox;
    private JTextField filenameTextField;
    private JLabel FileNameLabel;
    public saveDialog() {
        JFrame j = new JFrame();
        j.setSize(300,200);
        j.setVisible(true);
        j.add(panel1);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<Method> methods = new ArrayList<>();
                try {
                    System.out.println("ActionListener called");
                    if (TitleCheckBox.isSelected()) methods.add(PageWriter.class.getDeclaredMethod("writeTitle"));
                    if (ContentCheckBox.isSelected()) methods.add(PageWriter.class.getDeclaredMethod("writeContent"));
                    if (URLCheckBox.isSelected()) methods.add(PageWriter.class.getDeclaredMethod("writeURLs"));
                    new PageWriter(methods,filenameTextField.getText());
                    j.dispose();
                }
                catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
                    System.out.println("NoSuch Method");
                }
            }
        });

    }
}
