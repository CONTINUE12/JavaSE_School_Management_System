package manager;

//实现后台管理主界面功能

import main.login.UsingExit;
import manager.student.AddStudent;
import manager.student.DeleteStudent;
import student.GetStudent;
import manager.student.SetStudent;
import manager.teacher.AddTeacher;
import manager.teacher.DeleteTeacher;
import teacher.GetTeacher;
import manager.teacher.SetTeacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManagerManage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel JL = new JLabel("欢迎使用管理员系统",JLabel.CENTER); //设置标签对象

    JMenuBar jm = new JMenuBar(); //创建菜单栏对象

    JMenu jm1 = new JMenu("学生管理"); //创建菜单对象
    JMenuItem jm11 = new JMenuItem("增加信息"); //创建菜单项对象
    JMenuItem jm12 = new JMenuItem("删除信息");
    JMenuItem jm13 = new JMenuItem("修改信息");
    JMenuItem jm14 = new JMenuItem("查询信息");

    JMenu jm2 = new JMenu("教师管理"); //创建菜单对象
    JMenuItem jm21 = new JMenuItem("增加信息"); //创建菜单项对象
    JMenuItem jm22 = new JMenuItem("删除信息");
    JMenuItem jm23 = new JMenuItem("修改信息");
    JMenuItem jm24 = new JMenuItem("查询信息");

    JMenu jm3 = new JMenu("其他");
    JMenuItem jm31 = new JMenuItem("退出");

    //构造方法
    public ManagerManage()
    {
        this.setTitle("客户基本信息"); //创建标题信息
        this.setLayout(new CardLayout()); //创建窗口布局管理器
        this.setJMenuBar(jm); //将菜单栏组件添加到容器

        JL.setForeground(Color.red);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,20));
        JL.setBounds(100,30,200,40);
        this.add(JL);

        //添加菜单到菜单栏选项
        jm.add(jm1);
        jm.add(jm2);
        jm.add(jm3);

        //添加菜单选项到菜单
        jm1.add(jm11);
        jm1.add(jm12);
        jm1.add(jm13);
        jm1.add(jm14);
        jm11.addActionListener(this); //给菜单选项添加监听器
        jm12.addActionListener(this);
        jm13.addActionListener(this);
        jm14.addActionListener(this);

        jm2.add(jm21);
        jm2.add(jm22);
        jm2.add(jm23);
        jm2.add(jm24);
        jm21.addActionListener(this);
        jm22.addActionListener(this);
        jm23.addActionListener(this);
        jm24.addActionListener(this);

        jm3.add(jm31);
        jm31.addActionListener(this);

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

        //学生信息管理菜单
        if(e.getSource() == jm11){ //响应"添加“事件
            new AddStudent(); //调取窗口
        }
        if(e.getSource() == jm12){ //响应”删除“事件
            new DeleteStudent(); //调取窗口
        }
        if(e.getSource() == jm13){ //响应”修改“事件
            new SetStudent(); //调取窗口
        }
        if(e.getSource() == jm14){ //响应"查询"事件
            new GetStudent();
        }

        //教师信息管理菜单
        if(e.getSource() == jm21){ //响应"添加“事件
            new AddTeacher(); //调取窗口
        }
        if(e.getSource() == jm22){ //响应”删除“事件
            new DeleteTeacher(); //调取窗口
        }
        if(e.getSource() == jm23){ //响应”修改“事件
            new SetTeacher(); //调取窗口
        }
        if(e.getSource() == jm24){ //响应"查询"事件
            new GetTeacher();
        }


        if(e.getSource() == jm31){ //响应事件
            new UsingExit().setVisible(true); //调取”退出“窗口
        }
    }

    public static void main(String[] args) {
        new ManagerManage();
    }

}
