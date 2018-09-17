package tool;

import tool.PCB;
import model.MainFrame;
import java.util.ArrayList;
import javax.swing.JTable;
import model.MainFrame;

//时间片轮转法
public class ProgressBarThread1 extends Thread {

    ArrayList<PCB> arraylist;
    JTable statusTable;
    public int size = 0;
    public int number = 0;

    public ProgressBarThread1(JTable statusTable, ArrayList<PCB> arraylist) {
        this.arraylist = arraylist;
        this.statusTable = statusTable;
    }

    public void run() {

        number = 0;
        int end = 0;
        int stopCount = 1;
        size = arraylist.size();
        while (end == 0) {

            while (arraylist.get(number).time != 0) {
                arraylist.get(number).time--;
                // "进程标识符", "进程状态", "进程优先数", "总运行时间","剩余运行时间","进程状态条"
                statusTable.setValueAt(arraylist.get(number).time, number, 4);
                statusTable.setValueAt("运行态", number, 1);

                statusTable.setValueAt((arraylist.get(number).total - arraylist.get(number).time) * 100 / arraylist.get(number).total, number, 5);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException in ProgressBarThread");
                }
                if (arraylist.get(number).time != 0) {
                    statusTable.setValueAt("就绪态", number, 1);
                } else {
                    statusTable.setValueAt("终止态", number, 1);
                }
                number++;
                if (number >= arraylist.size()) {
                
                    number = 0;
                }

            }

            stopCount = 0;
            while (arraylist.get(number).time == 0) {
            
                end = 0;
                stopCount++;
                number++;
                if (number == arraylist.size()) {
                    number = 0;
                }
                if (stopCount > size) {
                    System.out.println("stopCount=" + stopCount + "  size=" + size);
                    end = 1;
                    MainFrame.start_time = false;
                    break;
                }
            }
        }

    }
}
