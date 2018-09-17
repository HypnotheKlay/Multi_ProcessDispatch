package tool;

import javax.swing.JProgressBar;

//定义程序控制块
public class PCB {

    public int name; // 进程标识符
    public int status; // 进程状态
    public int pri; // 进程优先数
    public int total;// 进程总时间
    public int time; // 剩余运行时间， 以时间片为单位， 当减至 0 时该进程终止
    public int next; // 下一个进程控制块的位置
    public int psw;//状态字
    JProgressBar bar;//进度条

    public PCB(int name, int status, int pri, int total, int time, JProgressBar bar) {
        this.name = name;
        this.status = status;
        this.pri = pri;
        this.total = total;
        this.time = time;
        this.bar = bar;
    }

    public PCB(int name, int status, int pri, int total, int time) {
        this.name = name;
        this.status = status;
        this.pri = pri;
        this.total = total;
        this.time = time;
    }

}
