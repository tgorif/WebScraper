import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScapeController implements ActionListener {
    Scrape scrapeView;
    public  ScapeController(){
        scrapeView = new Scrape();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("dsadadas");

    }
}
