package view;

import model.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Timing implements Runnable {

    JLabel label;
    int i = 0;

    public Timing(JLabel label) {
        this.label = label;
    }

    public void run() {

        while (MainFrame.start_time == true) {
            i++;
            label.setText(Integer.toString(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timing.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

}
