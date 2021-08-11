package main.login;

//实现系统退出功能

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UsingExit extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L; //类序列化号，用于跨版本编译

    JLabel JL = new JLabel("退出界面",JLabel.CENTER); //设置标签对象
    JButton JBexit = new JButton("退出");
    JButton JBne = new JButton("取消");

    //构造方法
    public UsingExit()
    {
        this.setTitle("退出界面");
        this.setLayout(null);

        JL.setForeground(Color.black);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,19));
        JL.setBounds(100,40,200,40);
        this.add(JL);

        JBexit.setBounds(80,100,90,20);
        this.add(JBexit);
        JBexit.addActionListener(this);

        JBne.setBounds(190,100,90,20);
        this.add(JBne);
        JBne.addActionListener(this);

        this.setBounds(760,340,400,250);
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //事件响应方法
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == JBexit) //处理重置事件
        {
            System.exit(0);
        }

        if(e.getSource() == JBne)
        {
            setVisible(false); //设置窗口不可见
        }
    }

    public static void main(String[] args) {
        new UsingExit().setVisible(true);
    }
}
