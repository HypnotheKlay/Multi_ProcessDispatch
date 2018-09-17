package tool;

import tool.PCB;
import model.MainFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import model.MainFrame;

//短进程优先法
public class ProgressBarThread3 extends Thread {

    public static int runNum = 0;

    public static int getRunNum() {
        return runNum;
    }

    public static void setRunNum(int runNum) {
        ProgressBarThread3.runNum = runNum;
    }

    public ArrayList<PCB> arraylistTemp;
    JTable statusTable;
    public Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int size = 0;
    public boolean runInterupt = false;

    @SuppressWarnings("unchecked")
    public ProgressBarThread3(JTable statusTable, ArrayList<PCB> arraylist) {

        int row = 0;
        this.arraylistTemp = arraylist;
        for (PCB pcb : arraylistTemp) { //遍历pcb的集合，取出每一个元素
            map.put(pcb.name, row);
            row++;
            System.out.println("pcb.time=" + pcb.time);
        }

        this.statusTable = statusTable;
        Collections.sort(arraylistTemp, new Comparator() {  //对进程的长短进行排序
            public int compare(Object o1, Object o2) {
                return ((PCB) o1).time - (((PCB) o2).time);
            }
        });

    }


    public void run() {

        runNum = 0;
        int end = 0;
        int stopCount = 1;
        size = arraylistTemp.size();
        while (end == 0) {

            while (arraylistTemp.get(runNum).time != 0) {

                arraylistTemp.get(runNum).time--;
                // "进程标识符", "进程状态", "进程优先数", "总运行时间","剩余运行时间","进程状态条"
                System.out.println("runNum=" + runNum + "   " + map.get(arraylistTemp.get(runNum).name) + "  map=" + map.size());
                statusTable.setValueAt(arraylistTemp.get(runNum).time, map.get(arraylistTemp.get(runNum).name), 4);
                statusTable.setValueAt("运行态", map.get(arraylistTemp.get(runNum).name), 1);

                statusTable.setValueAt((arraylistTemp.get(runNum).total - arraylistTemp.get(runNum).time) * 100 / arraylistTemp.get(runNum).total, map.get(arraylistTemp.get(runNum).name), 5);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                if (arraylistTemp.get(runNum).time == 0) {
                    statusTable.setValueAt("终止态",
                            map.get(arraylistTemp.get(runNum).name), 1);
                    if (runInterupt) {
                        runInterupt = false;
                        runNum = 0;
                        break;
                    } else {
                        runNum++;
                        runInterupt = true;
                    }
                } else {
                    statusTable.setValueAt("就绪态",
                            map.get(arraylistTemp.get(runNum).name), 1);
                }
                if (runNum >= arraylistTemp.size()) {
                    System.out.println("runNum 000=" + runNum);
                    runNum = 0;
                    System.out.println("number0 =" + arraylistTemp.get(runNum).time);
                }

            }

            stopCount = 0;
            while (arraylistTemp.get(runNum).time == 0) {
                System.out.println(stopCount + "  runNum in while=" + runNum + " time=" + arraylistTemp.get(runNum).time);
                end = 0;
                stopCount++;
                runNum++;
                if (runNum == arraylistTemp.size()) {
                    runNum = 0;
                }
                if (stopCount > size) {
                    System.out.println("stopCount=" + stopCount);
                    end = 1;
                    MainFrame.start_time = false;
                    break;
                }
            }
        }

    }

    public boolean IsRunningOver() {
        while (arraylistTemp.get(runNum).time != 0) {

        }
        return false;

    }
}
