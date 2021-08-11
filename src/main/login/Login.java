package main.login;

//实现学生信息管理系统的初始登录界面功能

import main.db.DBConnentor;
import manager.ManagerManage;
import student.StudentManage;
import teacher.TeacherManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L; //类序列化号，用于跨版本编译

    JLabel JL = new JLabel("欢迎使用XX大学教务系统",JLabel.CENTER); //设置标签对象

    JLabel JLname = new JLabel("用户名："); //创建标签及文本框对象
    JTextField JTname = new JTextField();

    JLabel JLpassword = new JLabel("密码：");
    JTextField JTpassword = new JTextField();

    JLabel JLidentity = new JLabel("身份：");
    JComboBox<String> JCidentity = new JComboBox<String>();

    JButton JBlogin = new JButton("登录");
    JButton JBexit = new JButton("退出");

    String sql = "";

    //构造方法
    public Login()
    {
        this.setTitle("XX大学教务系统");
        this.setLayout(null);

        JL.setForeground(Color.red);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,20));
        JL.setBounds(50,30,300,40);
        this.add(JL);

        JLname.setBounds(100,120,100,20); //设置标签的位置
        this.add(JLname);
        JTname.setBounds(200,120,80,20);
        this.add(JTname);

        JLpassword.setBounds(100,160,60,20);
        this.add(JLpassword);
        JTpassword.setBounds(200,160,80,20);
        this.add(JTpassword);

        JLidentity.setBounds(100,200,60,20);
        this.add(JLidentity);
        JCidentity.setBounds(200,200,80,20);
        this.add(JCidentity);
        JCidentity.addItem(new String("学生"));
        JCidentity.addItem(new String("教师"));
        JCidentity.addItem(new String("管理员"));

        JBlogin.setBounds(100,320,90,20); //设置按钮的位置
        this.add(JBlogin);
        JBlogin.addActionListener(this); //给按钮添加监听器

        JBexit.setBounds(200,320,90,20);
        this.add(JBexit);
        JBexit.addActionListener(this);

        this.setBounds(760,340,400,400);
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //事件响应方法
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == JBlogin){

            String iname = JTname.getText();
            String ipassword = JTpassword.getText();
            String box = (String) JCidentity.getSelectedItem();
            String sql = "";

            if(box.equals("学生")){
                sql = "select password from students where name = '" + iname + "'";
                try{
                    Statement stm = DBConnentor.getConn().createStatement();
                    ResultSet rs = stm.executeQuery(sql);  //查询数据库
                    if(rs.next() && rs.getString(1).equals(ipassword))
                    {
                        JOptionPane.showMessageDialog(null,"登录成功!");
                        new StudentManage(); //调取学生信息界面
                    }else{
                        JOptionPane.showMessageDialog(null,"用户名或密码错误!");
                    }
                }catch(Exception ee){
                    ee.printStackTrace();
                }
            }else if(box.equals("教师")){
                sql = "select password from teachers where name = '" + iname + "'";
                try{
                    Statement stm = DBConnentor.getConn().createStatement();
                    ResultSet rs = stm.executeQuery(sql);
                    if(rs.next() && rs.getString(1).equals(ipassword))
                    {
                        JOptionPane.showMessageDialog(null,"登录成功!");
                        new TeacherManage(); //调用教师信息界面
                    }else{
                        JOptionPane.showMessageDialog(null,"用户名或密码错误!");
                    }
                }catch(Exception ee){
                    ee.printStackTrace();
                }
            }else if(box.equals("管理员")){
                sql = "select password from managers where name = '" + iname + "'";
                try{
                    Statement stm = DBConnentor.getConn().createStatement();
                    ResultSet rs = stm.executeQuery(sql);
                    if(rs.next() && rs.getString(1).equals(ipassword))
                    {
                        JOptionPane.showMessageDialog(null,"登录成功!");
                        new ManagerManage(); //调用管理员界面
                    }else{
                        JOptionPane.showMessageDialog(null,"用户名或密码错误!");
                    }
                }catch(Exception ee){
                    ee.printStackTrace();
                }
            }
        }

        if(e.getSource() == JBexit){
            new UsingExit().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
