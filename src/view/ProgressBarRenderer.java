package view;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ProgressBarRenderer extends DefaultTableCellRenderer {

    /**
     * 工具条的渲染器
     */
    private static final long serialVersionUID = 1L;
    private JProgressBar jProgressBar;

    public ProgressBarRenderer() {
        super();
        setOpaque(true);
        jProgressBar = new GyrJProgressBar();//new JProgressBar();
        // 是否显示进度字符串
        jProgressBar.setStringPainted(true);

        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);

        jProgressBar.setBorderPainted(true);

        jProgressBar.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Integer i = (Integer) value;
        jProgressBar.setValue(i);
        return jProgressBar;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }
}
