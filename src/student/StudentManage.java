package student;

//实现学生系统主界面

import main.login.UsingExit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentManage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel JL = new JLabel("欢迎使用学生系统",JLabel.CENTER); //设置标签对象

    JMenuBar jm = new JMenuBar(); //创建菜单栏对象

    JMenu jm1 = new JMenu("查询"); //创建菜单对象
    JMenuItem jm11 = new JMenuItem("基本信息查询"); //创建菜单项对象
    JMenuItem jm12 = new JMenuItem("成绩查询");

    JMenu jm2 = new JMenu("其他");
    JMenuItem jm21 = new JMenuItem("退出");

    //构造方法
    public StudentManage()
    {
        this.setTitle("学生基本信息"); //创建标题信息
        this.setLayout(new CardLayout()); //创建窗口布局管理器
        this.setJMenuBar(jm); //将菜单栏组件添加到容器

        JL.setForeground(Color.red);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,20));
        JL.setBounds(100,30,200,40);
        this.add(JL);

        //添加菜单到菜单栏选项
        jm.add(jm1);
        jm.add(jm2);

        //添加菜单选项到菜单
        jm1.add(jm11);
        jm1.add(jm12);
        jm11.addActionListener(this); //给菜单选项添加监听器
        jm12.addActionListener(this);

        jm2.add(jm21);
        jm21.addActionListener(this);

        this.setBounds(710,340,500,400); //设置窗口尺寸
        this.setVisible(true); //设置窗口可见

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //动作事件响应方法
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == jm11){ //响应”查询信息“事件
            new GetStudent(); //调取窗口
        }
        if(e.getSource() == jm12){ //响应”查询成绩“事件
            new GetGrade(); //调取窗口
        }

        if(e.getSource() == jm21){ //响应事件
            new UsingExit().setVisible(true); //调取”退出“窗口
        }
    }

    public static void main(String[] args) {
        new StudentManage();
    }
}
