package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

/*
 *设置进度条样式
 */
public class GyrJProgressBar extends JProgressBar {

    private class GyrProgressUI extends BasicProgressBarUI {

        private double greenOverPercent = 100d;

        private JProgressBar jProgressBar;

        private GyrProgressUI(JProgressBar jProgressBar) {
            this.jProgressBar = jProgressBar;
            jProgressBar.setBackground(Color.white);
        }

        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {

            double percent = 100d * this.jProgressBar.getValue() / (this.jProgressBar.getMaximum() - this.jProgressBar.getMinimum());

            if (percent >= this.greenOverPercent) {
                this.jProgressBar.setForeground(Color.green);
            } else {
                this.jProgressBar.setForeground(Color.red);
            }
            super.paintDeterminate(g, c);
        }

    }

    public GyrJProgressBar() {
        init();
    }

    public GyrJProgressBar(int orient) {
        super(orient);
        init();
    }

    public GyrJProgressBar(int min, int max) {
        super(min, max);
        init();
    }

    public GyrJProgressBar(int orient, int min, int max) {
        super(orient, min, max);
        init();
    }

    public GyrJProgressBar(BoundedRangeModel newModel) {
        super(newModel);
        init();
    }

    private void init() {
        this.setBorderPainted(false);
        this.setUI(new GyrProgressUI(this));
    }
}
